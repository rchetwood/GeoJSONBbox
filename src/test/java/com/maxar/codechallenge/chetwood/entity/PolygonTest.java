package com.maxar.codechallenge.chetwood.entity;

import com.maxar.codechallenege.chetwood.json.JsonParser;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Test;

public class PolygonTest extends TestCase {

   String filePath = "C:\\Users\\riley\\Desktop\\MaxarCodingChallenge\\coding-challenge-chetwood\\src\\main\\resources\\";
   String fileName = "testGeoJSON.json";

   @Test
   public void testDeepClone_UsingEqual() throws IOException {
      Polygon polygon1 =
          (Polygon) JsonParser.getGeoJsonObject(new FileInputStream(filePath + fileName),
          Polygon.class);

      Polygon polygon2 =
          (Polygon) JsonParser.getGeoJsonObject(new FileInputStream(filePath + fileName),
              Polygon.class);

      assertTrue(polygon1.equals(polygon2));
      assertFalse(polygon1.getType() == polygon2.getType());
      assertFalse(polygon1.getBbox()== polygon2.getBbox());
      assertFalse(polygon1.getCoordinates() == polygon2.getCoordinates());
   }

   @Test
   public void testDeepClone_AttemptChangingReferencesValue() throws IOException {
      Polygon polygon1 =
          (Polygon) JsonParser.getGeoJsonObject(new FileInputStream(filePath + fileName),
              Polygon.class);

      List<List<Double>> coordinates = polygon1.getCoordinates().get(0);
      List<Double> newValue = new ArrayList<Double>();
      newValue.add(3.14159);
      newValue.add(42.0);
      coordinates.add(newValue);

      assertFalse(polygon1.getCoordinates().get(0).contains(3.14159));
   }

   @Test
   public void testDeepClone_AttemptChangingPrimitiveValue() throws IOException {
      Polygon polygon1 =
          (Polygon) JsonParser.getGeoJsonObject(new FileInputStream(filePath + fileName),
              Polygon.class);

      double[] bbox = polygon1.getBbox();
      bbox[0] = 3.14159;

      assertFalse(polygon1.getBbox()[0] == 3.14159);
   }

   @Test
   public void testCalculateBbox() throws IOException {
      Polygon polygon1 =
          (Polygon) JsonParser.getGeoJsonObject(new FileInputStream(filePath + fileName),
              Polygon.class);

      double minY =  39.67717782;
      double maxY =  39.82991355;
      double minX =  -105.12923684;
      double maxX =  -104.8316255;
      double[] expectedBbox = {minX, minY, maxX, maxY};
      assertTrue(Arrays.equals(expectedBbox, polygon1.getBbox()));
   }
}