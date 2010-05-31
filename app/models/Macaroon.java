package models;

import java.util.ArrayList;
import java.util.List;

import play.data.validation.Required;
import siena.Id;
import siena.Model;
import siena.Query;

public class Macaroon extends Model {
   @Id
   public Long id;
   @Required
   public String name;
   @Required
   public float weight;
   @Required
   public float diameter;
   @Required
   public String description;
   @Required
   public String perfume;
   public String picture;
   public List<Composition> composition = new ArrayList<Composition>();

   public Macaroon(String name, float weight, float diameter, String description, String perfume) {
      this.name = name;
      this.diameter = diameter;
      this.weight = weight;
      this.description = description;
      this.perfume = perfume;
   }

   public static Query<Macaroon> all() {
      return Model.all(Macaroon.class);
   }
}
