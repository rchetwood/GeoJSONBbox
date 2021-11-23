package com.maxar.codechallenge.chetwood.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/**
 * "A Geometry object represents points, curves, and surfaces in
 * coordinate space.  Every Geometry object is a GeoJSON object no
 * matter where it occurs in a GeoJSON text."
 *
 * @param <E> positions
 */
public abstract class Geometry<E> extends GeoJsonObject {

   protected final List<E> coordinates = new ArrayList<E>();

   public Geometry(
       @JsonProperty("type") String type,
       @JsonProperty("coordinates") List<E> coordinates
   ) {
      super(type);
      this.coordinates.addAll(coordinates);
      this.setBbox(calculateBbox());
   }

   public List<E> getCoordinates() {
      return new ArrayList<>(coordinates);
   }

   /**
    * "GeoJSON processors MAY interpret Geometry objects with
    * empty "coordinates" arrays as null objects"
    *
    * @return whether or not coordinates is empty.
    */
   public boolean isEmpty() {
      return (coordinates == null || coordinates.isEmpty());
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (!(o instanceof Geometry)) {
         return false;
      }
      Geometry<?> geometry = (Geometry<?>) o;
      return coordinates.equals(geometry.coordinates);
   }

   @Override
   public int hashCode() {
      return Objects.hash(coordinates);
   }
}
