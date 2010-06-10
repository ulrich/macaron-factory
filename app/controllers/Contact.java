package controllers;

import play.mvc.Controller;

public class Contact extends Controller {

   public static void index() {
      render();
   }

   public static void sendMessage(models.Contact contact) {
      validation.valid(contact);
      if (validation.hasErrors()) {
         render("@index", contact);
      }
      contact.save();
      flash.success("Votre message a bien été pris en compte");
      Contact.index();
   }
}
