package com.maxar.codechallenge.chetwood.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * Base class for all GeoJsonObjects as defined in RFC 7946
 *
 * @see <a href="https://datatracker.ietf.org/doc/html/rfc7946">
 * https://datatracker.ietf.org/doc/html/rfc7946</a>
 * <p>
 * NOTE: All comments in quotes ("") within project reference the aforementioned white paper.
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type",
    visible = true)
@JsonSubTypes(@JsonSubTypes.Type(value = Polygon.class, name = "Polygon"))
public abstract class GeoJsonObject implements Serializable {

   private final String type;
   private double[] bbox;

   public GeoJsonObject(@JsonProperty("type") String type) {
      this.type = type;
   }

   protected abstract double[] calculateBbox();

   public String getType() {
      return type;
   }

   public double[] getBbox() {
      return bbox.clone();
   }

   protected void setBbox(double[] bbox) {
      this.bbox = bbox;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (!(o instanceof GeoJsonObject)) {
         return false;
      }
      GeoJsonObject that = (GeoJsonObject) o;
      return type.equals(that.type) && Arrays.equals(bbox, that.bbox);
   }

   @Override
   public int hashCode() {
      int result = Objects.hash(type);
      result = 31 * result + Arrays.hashCode(bbox);
      return result;
   }
}
