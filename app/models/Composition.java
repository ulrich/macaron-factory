package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Composition extends Model {
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
   @OneToMany
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
}
