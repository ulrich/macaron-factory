package controllers;

import java.io.IOException;
import java.util.Date;

import org.antlr.stringtemplate.StringTemplate;
import org.apache.commons.io.FileUtils;

import play.Logger;
import play.Play;
import play.libs.Mail;
import play.mvc.Controller;

public class Contact extends Controller {
   public static final String EMAIL_TEMPLATE_FILENAME = "conf/email.st";

   public static void index() {
      render();
   }

   public static void sendMessage(models.Contact contact) {
      validation.valid(contact);
      if (validation.hasErrors()) {
         render("@index", contact);
      }
      contact.save();
      sendMail(contact);
      flash.success("success");
      Contact.index();
   }

   private static void sendMail(models.Contact contact) {
      String emailTemplate = null;
      try {
         emailTemplate = FileUtils.readFileToString(Play.getFile(EMAIL_TEMPLATE_FILENAME));
      } catch (IOException e) {
         Logger.warn("Unable to load email template file", e);
         return;
      }
      StringTemplate template = new StringTemplate(emailTemplate);
      template.setAttribute("date", new Date());
      template.setAttribute("from", contact.email);
      template.setAttribute("fullname", contact.fullname);
      template.setAttribute("shortname", contact.shortname);
      template.setAttribute("message", contact.message);

      Mail.send("website@macaron-factory.com", "contact@macaron-factory.com", "Contact par formulaire", template.toString());
   }
}
