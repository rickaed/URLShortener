package com.app.urlshortener.bdd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.app.urlshortener.webRest.Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Service
public class BddService {
   private BddRepository bddRepository;
   private Config config;
   private static ArrayList<BddEntity> myBdd;

   public BddService(BddRepository bddRepository, Config config) {
      this.bddRepository = bddRepository;
      this.config = config;
   }

   // ecrire en bdd
   public void addUrlToBdd(String id, String shortId, String realUrl, String token, Long date) {
      BddEntity newUrl = new BddEntity(id, shortId, realUrl, token, date);
      try (FileWriter fWriter = new FileWriter(config.getBddPath());
            ObjectWriter oos = new ObjectWriter(fWriter)) {
         oos.writeObject(newUrl);
      } catch (FileNotFoundException e) {
         // creer le fichier
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   // lire en bdd
   public void readinBdd(String id, String shortId, String realUrl, String token, Long date) {
      BddEntity newUrl = new BddEntity(id, shortId, realUrl, token, date);
      try (FileInputStream fis = new FileInputStream(config.getBddPath());
            ObjectInputStream ois = new ObjectInputStream(fis)) {
         ois.writeObject(newUrl);
      } catch (FileNotFoundException e) {
         // creer le fichier
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   public boolean exist(String longUrl) {
      /**
       * ouvrir bdd
       * bddrepo.findByLongUrl(url)
       */
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