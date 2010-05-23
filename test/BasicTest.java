import java.awt.Color;
import java.util.Date;
import java.util.List;

import models.Composition;
import models.Event;
import models.Macaroon;
import models.Shape;

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
      new Macaroon(Shape.ROUND, new Color(139, 69, 19), 3.5f, 10f, "Chocolate", "chocolate1.gif", "The chocolate macaroon...").save();
      new Macaroon(Shape.ROUND, Color.WHITE, 3.5f, 5f, "Coconut", "Coconut1.gif", "The coconut macaroon...").save();

      // test the macaroons list size
      assertEquals(2, Macaroon.count());

      // finding Chocolate macaroon
      Macaroon chocolateMacaroon = Macaroon.find("byWeight", 10f).first();
      assertNotNull(chocolateMacaroon);
      assertEquals(10f, chocolateMacaroon.weight, 0f);

      // updating Chocolate macaroon profile
      chocolateMacaroon.weight = 2f;
      chocolateMacaroon.save();
      Macaroon updatedChocolateMacaroon = Macaroon.find("byWeight", 2f).first();
      assertNotNull(updatedChocolateMacaroon);

      // deleting Chocolate macaroon
      updatedChocolateMacaroon.delete();
      assertEquals(1, Macaroon.count());
      List<Macaroon> availableMacaroon = Macaroon.findAll();
      assertEquals(1, availableMacaroon.size());
      assertEquals("Coconut", availableMacaroon.get(0).name);
   }

   @Test
   public void crudOnCompostion() {
      // creating Angelina composition
      new Composition("Angelina", "Angelina composition short description...", "Angelina composition full description...", 15f).save();

      // test the composition list
      assertEquals(1, Composition.count());

      // finding Angelina composition
      Composition angelinaComposition = Composition.find("byName", "Angelina").first();
      assertNotNull(angelinaComposition);
      assertEquals("Angelina composition short description...", angelinaComposition.shortDescription);

      // updating Angelina composition
      angelinaComposition.shortDescription = "The updated Angelina composition short description";
      angelinaComposition.save();
      Composition updatedAngelinaComposition = Composition.find("byName", "Angelina").first();
      assertNotNull(updatedAngelinaComposition);
      assertEquals("The updated Angelina composition short description", updatedAngelinaComposition.shortDescription);

      // deleting Angelina composition
      updatedAngelinaComposition.delete();
      assertEquals(0, Composition.count());
   }

   @Test
   public void addMacaroonToComposition() {
      Fixtures.load("data-test.yml");

      // finding a composition
      Composition smallMacComposition = Composition.find("name", "Angelina").first();
      assertNotNull(smallMacComposition);

      // finding and adding small mac in this composition
      List<Macaroon> smallMacaroonList = Macaroon.find("from Macaroon where weight = :weight").query.setParameter("weight", 20f).getResultList();
      assertEquals(3, smallMacaroonList.size());
      for (Macaroon macaroon : smallMacaroonList) {
         smallMacComposition.addMacaroon(macaroon);
      }
      //smallMacComposition.save();
   }

   //@Test
   public void searchMacarronByCompositionType() {
      Fixtures.load("data-test.yml");

      // finding composition by type
      List<Composition> composition = Composition.find("select c.id from composition c "
            + "inner join composition_macaroon m on c.id = m.id where count(macaroonList) = :size").query.setParameter("size", 3).getResultList();
      assertNotNull(composition);
   }

   public void crudEvent() {
      Date currentDate = new Date();

      // creating event
      new Event(currentDate, "Macaron-Factory prépare une grosse commande en vue d'un mariage pour juin 2010 !").save();

      // testing the event list size
      assertEquals(1, Event.count());

      // finding the event
      Event juneEvent = Event.find("byDate", currentDate).first();
      assertNotNull(juneEvent);
   }
}
