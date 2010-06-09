package models;

import java.util.Date;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Contact extends Model {
   public String email;
   public String fullname;
   public String shortname;
   public String message;
   public Date date;

   public Contact(String email, String fullname, String shortname) {
      this.email = email;
      this.fullname = fullname;
      this.shortname = shortname;
      this.date = new Date();
   }
}
