import java.util.Date;
import java.util.List;

import models.Composition;
import models.Contact;
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
      new Macaroon("Chocolate", 10f, 3.5f, "The chocolate macaroon...", "Chocolate").save();
      new Macaroon("Coconut", 5f, 3.5f, "The coconut macaroon...", "Coconut").save();

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
      Composition smallMacComposition = Composition.find("name", "L'Angélina").first();
      assertNotNull(smallMacComposition);

      // finding and adding small mac in this composition
      List<Macaroon> smallMacaroonList = Macaroon.find("from Macaroon where weight = :weight").query.setParameter("weight", 20f).getResultList();
      assertEquals(23, smallMacaroonList.size());
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

   @Test
   public void crudEvent() {
      Date currentDate = new Date();
      // creating event
      new Event(currentDate, "Macaron-Factory prépare une grosse commande en vue d'un mariage pour juin 2010 !").save();

      // testing the event list size
      assertEquals(1, Event.count());

      // finding the event
      Event juneEvent = Event.find("byDate", currentDate).first();
      assertNotNull(juneEvent);

      // updating description
      juneEvent.description = "Macaron-Factory retenu pour un mariage en juin 2010 !";
      juneEvent.save();
      Event updatedJuneEvent = Event.find("byDate", currentDate).first();
      assertEquals("Macaron-Factory retenu pour un mariage en juin 2010 !", updatedJuneEvent.description);

      // deleting the event
      updatedJuneEvent.delete();
      assertEquals(0, Event.count());
   }

   @Test
   public void findHighlightedComposition() {
      Fixtures.load("data-test.yml");

      // finding highlighted composition (max 2)
      List<Composition> highlightedComposition = Composition.findHighlighted();
      assertEquals(2, highlightedComposition.size());
   }

   @Test
   public void crudOnContact() {
      // creating contact entries
      new Contact("John", "Doe", "john@doe.com", "Hi macaron factory!").save();
      new Contact("Woopr", "Machine", "woopr@machine.com", "Do you prefer a good chess party?!").save();

      // testing the persisted contact list size
      assertEquals(2, Contact.count());

      // finding saved John Doe entry
      Contact johnDoe = Contact.find("byEmail", "john@doe.com").first();
      assertNotNull(johnDoe);
      assertEquals("John", johnDoe.shortname);

      // updating john doe entry
      johnDoe.fullname = "Forman";
      johnDoe.save();

      // finding new updated John entry
      johnDoe = Contact.find("byFullName", "Forman").first();
      assertNotNull(johnDoe);
      assertEquals("Forman", johnDoe.fullname);

      // deleting contact woopr entry
      Contact woopr = Contact.find("byEmail", "woopr@machine.com").first();
      woopr.delete();

      // testing the truncated contact list size
      assertEquals(1, Contact.count());
   }
}
