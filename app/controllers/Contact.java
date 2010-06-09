package controllers;

import play.mvc.Controller;

public class Contact extends Controller {

   public static void index() {
      render();
   }

   public static void sendMessage(models.Contact contact) {
      System.out.println(contact.email);
      System.out.println(contact.fullname);
      System.out.println(contact.shortname);
      System.out.println(contact.message);
      Contact.index();
   }
}
