package com.app.urlshortener.bdd;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.stereotype.Service;

import com.app.urlshortener.webRest.Config;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@Service
public class BddService {
   private  BddRepository bddRepository;
   private final Config config;
   public BddService(BddRepository bddRepository) {
      this.bddRepository = bddRepository;
   }
   @JsonComponent
   public static class Serializer extends JsonSerializer<BddEntity> {

      @Override
      public void serialize(BddEntity value, JsonGenerator jgen, SerializerProvider serializers) throws IOException {
         jgen.writeStartObject();

         jgen.writeStringField("id", value.getId());
         jgen.writeStringField("short-id", value.getShortUrl());
         jgen.writeStringField("real-url", value.getRealUrl());
         jgen.writeStringField("removal-Token", value.getToken());
         jgen.writeNumberField("access-date", value.getDate());
         jgen.writeEndObject();
      }

   }

   public static class Deserializer extends JsonDeserializer<BddEntity> {

      @Override
      public BddEntity deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
         ObjectCodec codec = jsonParser.getCodec();
         JsonNode tree = codec.readTree(jsonParser);
         String id = tree.get("id").textValue();
         String shortId = tree.get("short-id").textValue();
         String realUrl = tree.get("real-url").textValue();
         String token = tree.get("removal-Token").textValue();
         Long date = tree.get("access-date").longValue();
         return new BddEntity(id, shortId, realUrl, token, date);
      }

   }

   // JSONArray jsonBdd = (JSONArray) parser.parse(new
   // FileReader(config.getBddPath()));

   // for (Object el : jsonBdd)
   // {
   // JSONObject person = (JSONObject) el;

   // String name = (String) person.get("name");
   // System.out.println(name);

   // String city = (String) person.get("city");
   // System.out.println(city);

   // String job = (String) person.get("job");
   // System.out.println(job);

   // JSONArray cars = (JSONArray) person.get("cars");

   // for (Object c : cars)
   // {
   // System.out.println(c+"");
   // }
   // }



   public boolean exist(String url) {

   }

}
