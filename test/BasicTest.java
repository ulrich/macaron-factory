import java.util.Date;
import java.util.List;

import models.Composition;
import models.Event;
import models.Macaroon;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class BasicTest extends UnitTest {

   @Before
   public void setup() {
      Fixtures.deleteAll();
   }

   @Test
   public void crudOnMacaroon() {
      // creating light and heavy macaroons
      Macaroon first = new Macaroon("Chocolate", 10f, 3.5f, "The chocolate macaroon...", "Chocolate");
      Macaroon second = new Macaroon("Coconut", 5f, 3.5f, "The coconut macaroon...", "Coconut");
      first.insert();
      second.insert();

      // test the macaroons list size
      assertEquals(2, Macaroon.all().count());

      // finding Chocolate macaroon
      Macaroon chocolateMacaroon = Macaroon.all().filter("weight", 10f).get();
      assertNotNull(chocolateMacaroon);
      assertEquals(10f, chocolateMacaroon.weight, 0f);

      // updating Chocolate macaroon profile
      chocolateMacaroon.weight = 2f;
      chocolateMacaroon.update();
      Macaroon updatedChocolateMacaroon = Macaroon.all().filter("weight", 2f).get();
      assertNotNull(updatedChocolateMacaroon);

      // deleting Chocolate macaroon
      updatedChocolateMacaroon.delete();
      assertEquals(1, Macaroon.all().count());
      List<Macaroon> availableMacaroon = Macaroon.all().fetch();
      assertEquals(1, availableMacaroon.size());
      assertEquals("Coconut", availableMacaroon.get(0).name);

      // quick fix, because the "Fixtures.deleteAll()" doesn't works
      first.delete();
      second.delete();
   }

   @Test
   public void crudOnCompostion() {
      // creating Angelina composition
      new Composition("Angelina", "Angelina composition short description...", "Angelina composition full description...", 15f).insert();
      // test the composition list
      assertEquals(1, Composition.all().count());

      // finding Angelina composition
      Composition angelinaComposition = Composition.all().filter("name", "Angelina").get();
      assertNotNull(angelinaComposition);
      assertEquals("Angelina composition short description...", angelinaComposition.shortDescription);

      // updating Angelina composition
      angelinaComposition.shortDescription = "The updated Angelina composition short description";
      angelinaComposition.update();
      Composition updatedAngelinaComposition = Composition.all().filter("name", "Angelina").get();
      assertNotNull(updatedAngelinaComposition);
      assertEquals("The updated Angelina composition short description", updatedAngelinaComposition.shortDescription);

      // deleting Angelina composition
      updatedAngelinaComposition.delete();
      assertEquals(0, Composition.all().count());
   }

   //   @Test
   //   public void addMacaroonToComposition() {
   //      Fixtures.load("data-test.yml");
   //
   //      // finding a composition
   //      Composition smallMacComposition = Composition.find("name", "Angelina").first();
   //      assertNotNull(smallMacComposition);
   //
   //      // finding and adding small mac in this composition
   //      List<Macaroon> smallMacaroonList = Macaroon.find("from Macaroon where weight = :weight").query.setParameter("weight", 20f).getResultList();
   //      assertEquals(3, smallMacaroonList.size());
   //      for (Macaroon macaroon : smallMacaroonList) {
   //         smallMacComposition.addMacaroon(macaroon);
   //      }
   //      //smallMacComposition.insert();
   //   }
   //
   //   //@Test
   //   public void searchMacarronByCompositionType() {
   //      Fixtures.load("data-test.yml");
   //
   //      // finding composition by type
   //      List<Composition> composition = Composition.find("select c.id from composition c "
   //            + "inner join composition_macaroon m on c.id = m.id where count(macaroonList) = :size").query.setParameter("size", 3).getResultList();
   //      assertNotNull(composition);
   //   }
   //
   @Test
   public void crudEvent() {
      Date currentDate = new Date();
      // creating event
      new Event(currentDate, "Macaron-Factory prépare une grosse commande en vue d'un mariage pour juin 2010 !").insert();

      // testing the event list size
      assertEquals(1, Event.all().count());

      // finding the event
      Event juneEvent = Event.all().filter("date", currentDate).get();
      assertNotNull(juneEvent);

      // updating description
      juneEvent.description = "Macaron-Factory retenu pour un mariage en juin 2010 !";
      juneEvent.update();
      Event updatedJuneEvent = Event.all().filter("date", currentDate).get();
      assertEquals("Macaron-Factory retenu pour un mariage en juin 2010 !", updatedJuneEvent.description);

      // deleting the event
      updatedJuneEvent.delete();
      assertEquals(0, Event.all().count());
   }

   //@Test
   public void findHighlightedComposition() {
      Fixtures.load("data-test.yml");

      // finding highlighted composition (max 2)
      List<Composition> highlightedComposition = Composition.findHighlighted();
      assertEquals(2, highlightedComposition.size());
   }
}
