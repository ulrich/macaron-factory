package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Macaroon extends Model {
   @Required
   public String name;
   @Required
   public float weight;
   @Required
   public float diameter;
   @Required
   public String description;
   public String picture;
   @ManyToMany
   public List<Composition> composition = new ArrayList<Composition>();

   public Macaroon(String name, float weight, float diameter, String description) {
      this.name = name;
      this.diameter = diameter;
      this.weight = weight;
      this.description = description;
   }
}
