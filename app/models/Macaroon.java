package models;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import play.db.jpa.Model;

@Entity
public class Macaroon extends Model {
   public Shape shape;
   public Color color;
   public float diameter;
   public float weight;
   public String name;
   public String picture;
   public String description;
   @ManyToMany
   public List<Composition> composition = new ArrayList<Composition>();

   public Macaroon(Shape shape, Color color, float diameter, float weight, String name, String picture, String description) {
      this.shape = shape;
      this.color = color;
      this.diameter = diameter;
      this.weight = weight;
      this.name = name;
      this.picture = picture;
      this.description = description;
   }
}
