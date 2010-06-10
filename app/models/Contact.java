package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.data.validation.Valid;
import play.db.jpa.Model;

@Entity
public class Contact extends Model {
   @Temporal(TemporalType.DATE)
   public Date date;
   public String shortname;
   public String fullname;
   @Required
   public String email;
   @Lob
   @Required
   @MaxSize(240)
   public String message;

   public Contact(String shortname, String fullname, @Valid String email, @Valid String message) {
      this.date = new Date();
      this.shortname = shortname;
      this.fullname = fullname;
      this.email = email;
      this.message = message;
   }
}
