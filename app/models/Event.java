package models;

import java.util.Date;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import siena.Id;
import siena.Model;
import siena.Query;
import siena.Table;

@Table("event")
public class Event extends Model {
   @Id
   public Long id;
   @Required
   public Date date;
   @Required
   @MaxSize(value = 100, message = "La description de l'événement ne doit pas dépasser 100 caractères")
   public String description;

   public Event(Date date, String description) {
      this.date = date;
      this.description = description;
   }

   public static Query<Event> all() {
      return Model.all(Event.class);
   }
}
