package controllers;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import models.Composition;
import models.Event;
import play.mvc.Controller;

public class Application extends Controller {

   public static void index() {
      new Event(new Date(), "Macaron-Factory prépare une grosse commande en vue d'un mariage pour juin 2010 !").insert();

      // finding last three events
      List<Event> events = Event.all().fetch();
      // finding compositions discounted (max 2)
      List<Composition> compositions = Composition.findHighlighted();

      //Fixtures.load("data-prod.yml");

      render(events, compositions);
   }

   public static void older() {
      render("main2.html");
   }
}
