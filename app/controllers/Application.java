package controllers;

import play.mvc.Controller;

public class Application extends Controller {

   public static void index() {
      render();
   }

   public static void older() {
      render("main2.html");
   }

}
