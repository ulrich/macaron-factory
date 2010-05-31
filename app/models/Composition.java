package models;

import java.util.ArrayList;
import java.util.List;

import play.data.validation.Required;
import siena.Id;
import siena.Model;
import siena.Query;

public class Composition extends Model {
   @Id
   public Long id;
   @Required
   public String name;
   @Required
   public String shortDescription;
   @Required
   public String fullDescription;
   @Required
   public float price;
   public String picture;
   public boolean highlighted;
   public List<Macaroon> macaroonList;

   public Composition(String name, String shortDescription, String fullDescription, float price) {
      this.name = name;
      this.shortDescription = shortDescription;
      this.fullDescription = fullDescription;
      this.price = price;
      macaroonList = new ArrayList<Macaroon>();
   }

   public void addMacaroon(Macaroon macaroon) {
      macaroonList.add(macaroon);
      macaroon.composition.add(this);
   }

   // find the highlighted composition (two max)
   public static List<Composition> findHighlighted() {
      return all().filter("highlighted", true).fetch(1, 2);
   }

   public static Query<Composition> all() {
      return Model.all(Composition.class);
   }
}
