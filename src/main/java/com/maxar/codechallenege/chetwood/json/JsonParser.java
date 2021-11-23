package com.maxar.codechallenege.chetwood.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxar.codechallenge.chetwood.entity.GeoJsonObject;
import com.maxar.codechallenge.chetwood.entity.Polygon;
import java.io.FileInputStream;
import java.io.IOException;

public class JsonParser {

   private static ObjectMapper objectMapper = getDefaultObjectMapper();

   private static ObjectMapper getDefaultObjectMapper() {
      ObjectMapper defaultObjectMapper = new ObjectMapper();
      // ...
      return defaultObjectMapper;
   }

   public static GeoJsonObject getGeoJsonObject(FileInputStream file, Class aClass)
       throws IOException {
      return (GeoJsonObject) objectMapper.readValue(file, aClass);
   }
}
