package com.app.urlshortener.bdd;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.app.urlshortener.webRest.Config;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BddService {
   private BddRepository bddRepository;
   private Config config;

  

   public BddService(BddRepository bddRepository, Config config) {
      this.bddRepository = bddRepository;
      this.config = config;
   }

   public HashMap<String, String> createUrlEntity(String longUrl)
         throws StreamWriteException, DatabindException, IOException {

      UrlEntity newEntity = new UrlEntity();
      newEntity.setId(UUID.randomUUID().toString());
      newEntity.setShortId(genString(8));
      newEntity.setRealUrl(longUrl);
      newEntity.setToken(genString(40));
      newEntity.setDate(new Date());

      bddRepository.saveUrl(newEntity);

      HashMap<String, String> map = new HashMap<String, String>();
      map.put("id", newEntity.getId());
      map.put("short-url", newEntity.getShortId());
      map.put("real-url", newEntity.getRealUrl());

      return map;
   }

   // lire en bdd ???
   public void jsonReader() throws StreamReadException, DatabindException, IOException {
      ObjectMapper objectMapper = new ObjectMapper();
      File myBdd = new File(config.getBddPath());

      UrlEntity urlBdd = objectMapper.readValue(myBdd, UrlEntity.class);

   }

   // generation d'une suite de caractere alpohanumerique aléatoire
   // d'une longueur donnée
   public static String genString(int size) {
      int leftLimit = 48; // numeral '0'
      int rightLimit = 122; // letter 'z'
      int targetStringLength = size;
      Random random = new Random();

      String generatedString = random.ints(leftLimit, rightLimit + 1)
            .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
            .limit(targetStringLength)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();

      return generatedString;
   }

   // // ecrire en bdd
   // public void addUrlToBdd(String id, String shortId, String realUrl, String
   // token, Long date) {
   // BddEntity newUrl = new BddEntity(id, shortId, realUrl, token, date);
   // try (FileWriter fWriter = new FileWriter(config.getBddPath());
   // ObjectWriter oos = new ObjectWriter(fWriter)) {
   // oos.writeObject(newUrl);
   // } catch (FileNotFoundException e) {
   // // creer le fichier
   // } catch (IOException e) {
   // // TODO Auto-generated catch block
   // e.printStackTrace();
   // }
   // }

   // // lire en bdd
   // public void readInBdd(String id, String shortId, String realUrl, String
   // token, Long date) {
   // BddEntity newUrl = new BddEntity(id, shortId, realUrl, token, date);
   // try (FileInputStream fis = new FileInputStream(config.getBddPath());
   // ObjectInputStream ois = new ObjectInputStream(fis)) {
   // ois.writeObject(newUrl);
   // } catch (FileNotFoundException e) {
   // // creer le fichier
   // } catch (IOException e) {
   // // TODO Auto-generated catch block
   // e.printStackTrace();
   // }
   // }

   public void exist(String longUrl) throws StreamReadException, DatabindException, IOException {
      System.out.println("@@@@@@@@@@@@@@@@@ exist? : " + longUrl);
      ObjectMapper mapper = new ObjectMapper();
      /**
       * lire la BDD
       * parcourir la bdd
       * filtrer longUrl de la bdd
       * si -1 -> false
       * sinon true
       */
      List<UrlEntity> urlEntities = bddRepository.readAllUrlEntities();
      
      UrlEntity urlEntity = mapper.readValue(new File(config.getBddPath()), UrlEntity.class);

      // File myBdd = new File("../../../../bdd.json");
      // InputStream is = BddEntity.class.getResourceAsStream(myBdd);
      // Map<String, Object> BddEntity = mapper.readValue(myBdd, new TypeReference<>()
      // {
      // });
      // System.out.println("@@@@@@@@@@@@@@@@@ " + BddEntity);

      // if (BddEntity.get("realUrl").equals(longUrl)) {
      // return true;
      // }
      // return false;

   }

}

// public void initBdd() {
// File myBdd = new File(config.getBddPath());
// if (myBdd.exists()) {
// try {
// // FileReader fileReader = new FileReader(myBdd);
// // create ObjectMapper instance
// ObjectMapper objectMapper = new ObjectMapper();
// // read JSON file and convert to a bddEntity object
// BddEntity bddEntity = objectMapper.readValue(myBdd, BddEntity.class);
// // print customer details for control
// System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(bddEntity));
// } catch (Exception err) {
// err.printStackTrace();
// }
// } else {
// try {
// FileWriter fileWriter = new FileWriter(myBdd);
// fileWriter.close();
// } catch (Exception err) {
// }
// }
// }

// @JsonComponent
// public static class Serializer extends JsonSerializer<BddEntity> {:
//
// @Override
// // lecture de
// public void serialize(BddEntity value, JsonGenerator jgen, SerializerProvider
// serializers) throws IOException {
// jgen.writeStartObject();
// jgen.writeStringField("id", value.getId());
// jgen.writeStringField("short-id", value.getShortId());
// jgen.writeStringField("real-url", value.getRealUrl());
// jgen.writeStringField("removal-Token", value.getToken());
// jgen.writeNumberField("access-date", value.getDate());
// jgen.writeEndObject();
// }
// }

// public static class Deserializer extends JsonDeserializer<BddEntity> {
//
// @Override
// public BddEntity deserialize(JsonParser jsonParser, DeserializationContext
// ctxt) throws IOException {
// ObjectCodec codec = jsonParser.getCodec();
// JsonNode tree = codec.readTree(jsonParser);
// String id = tree.get("id").textValue();
// String shortId = tree.get("short-id").textValue();
// String realUrl = tree.get("real-url").textValue();
// String token = tree.get("removal-Token").textValue();
// Long date = tree.get("access-date").longValue();
// return new BddEntity(id, shortId, realUrl, token, date);
// }
// }