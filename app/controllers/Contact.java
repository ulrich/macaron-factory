package controllers;

import java.io.IOException;
import java.util.Date;

import org.antlr.stringtemplate.StringTemplate;
import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

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
      String forgedEmail = forgeEmail(contact);

      Email email = new SimpleEmail();
      try {
         email.addTo("contact@macaron-factory.com");
         email.setSubject("Contact par formulaire");
         email.setFrom("website@macaron-factory.com");
         email.setMsg(forgedEmail);
      } catch (EmailException e) {
         Logger.error("Unable to forge email", e);
         return;
      }
      Mail.send(email);
   }

   private static String forgeEmail(models.Contact contact) {
      StringTemplate template = null;

      try {
         template = new StringTemplate(FileUtils.readFileToString(Play.getFile(EMAIL_TEMPLATE_FILENAME)));
      } catch (IOException e) {
         Logger.error("Unable to load email template file", e);
         return null;
      }
      template.setAttribute("date", new Date());
      template.setAttribute("from", contact.email);
      template.setAttribute("fullname", contact.fullname);
      template.setAttribute("shortname", contact.shortname);
      template.setAttribute("message", contact.message);

      return template.toString();
   }
}
