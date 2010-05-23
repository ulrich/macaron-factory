package models;

import java.util.Date;

import javax.persistence.Entity;

import play.data.validation.MaxSize;
import play.db.jpa.Model;

@Entity
public class Event extends Model {
   public Date date;
   @MaxSize(value = 100, message = "La description de l'événement ne doit pas dépasser 100 caractères")
   public String description;

   public Event(Date date, String description) {
      this.date = date;
      this.description = description;
   }
}
