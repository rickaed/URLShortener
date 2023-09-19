package com.app.urlshortener.bdd;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.app.urlshortener.webRest.Config;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BddService {
   private BddRepository bddRepository;
   private Config config;
   private static ArrayList<BddEntity> myBdd;

   public BddService(BddRepository bddRepository, Config config) {
      this.bddRepository = bddRepository;
      this.config = config;
   }

   // ecrire en bdd ??
   public void jsonWriter(String longUrl) {
      File myBdd = new File(config.getBddPath());
      BddEntity newEntity = new BddEntity();
      newEntity.setId(UUID.randomUUID().toString());
      newEntity.setShortUrl(urlShorter());
      newEntity.setRealUrl(longUrl);
      newEntity.setToken("token a creer par methode");
      newEntity.setDate(new Date());

      ObjectMapper mapper = new ObjectMapper();

      try {

         // Writing to a file
         mapper.writeValue(myBdd, newEntity);

      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   // lire en bdd ???
   public void jsonReader() {
      ObjectMapper objectMapper = new ObjectMapper();
      File myBdd = new File(config.getBddPath());

      try {
         BddEntity urlBdd = objectMapper.readValue(myBdd, BddEntity.class);

      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   public static String urlShorter() {
      int leftLimit = 48; // numeral '0'
      int rightLimit = 122; // letter 'z'
      int targetStringLength = 8;
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

   public boolean exist(String longUrl) {
      boolean exist = false;
      File myBdd = new File(config.getBddPath());
      ObjectMapper mapper = new ObjectMapper();
      try {
         Map<String, Object> BddEntity = mapper.readValue(myBdd, new TypeReference<>() {
         });
         if (BddEntity.get("realUrl").equals(longUrl)) {
            exist = true;
         }
      } catch (IOException e) {
         e.printStackTrace();
      }

      return exist;
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
// jgen.writeStringField("short-id", value.getShortUrl());
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