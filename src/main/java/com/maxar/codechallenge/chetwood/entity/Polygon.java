package com.maxar.codechallenge.chetwood.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public final class Polygon extends Geometry<List<List<Double>>> {

   public Polygon(
       @JsonProperty("type") String type,
       @JsonProperty("coordinates") List coordinates
   ) {
      super(type, coordinates);
   }

   @Override
   protected double[] calculateBbox() {
      double xMin = Double.MAX_VALUE;
      double xMax = -Double.MAX_VALUE;
      double yMin = Double.MAX_VALUE;
      double yMax = -Double.MAX_VALUE;

      for (int i = 0; i < this.coordinates.size(); i++) {
         List<List<Double>> coordinates = this.getCoordinates().get(i);
         for (int j = 0; j < coordinates.size(); j++) {
            List<Double> position = coordinates.get(j);
            double x = position.get(0);
            double y = position.get(1);
            if (x < xMin) {
               xMin = x;
            }
            if (x > xMax) {
               xMax = x;
            }
            if (y < yMin) {
               yMin = y;
            }
            if (y > yMax) {
               yMax = y;
            }
         }
      }
      double[] bbox = {xMin, yMin, xMax, yMax};
      return bbox;
   }
}
