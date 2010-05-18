import java.awt.Color;
import java.util.List;

import models.Composition;
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
   public void crudMacaroon() {
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
   }

   @Test
   public void createAndFindCompostion() {
      new Composition("Angelina", "Angelina composition description").save();
      // finding Angelina composition with bad name
      Composition savedComposition = Composition.find("byName", "_Angelina").first();
      assertNull(savedComposition);
      // finding Angelina composition with good name
      savedComposition = Composition.find("byName", "Angelina").first();
      assertNotNull(savedComposition);
   }

   @Test
   public void addMacaroonToComposition() {
      // creating strawberry and lemon macaroons
      Macaroon strawberry = new Macaroon(Shape.ROUND, Color.RED, 3.5f, 15f, "Strawberry", "strawberry1.gif", "The strawberry macarron...").save();
      Macaroon lemon = new Macaroon(Shape.ROUND, Color.YELLOW, 3.5f, 10f, "Lemon", "lemon1.gif", "The lemon macarron...").save();
      // creating dummy composition
      Composition astrid = new Composition("Astrid", "Astrid composition description").save();
      astrid.addMacaroon(strawberry);
      astrid.addMacaroon(lemon);
      // finding Astrid composition
      Composition savedComposition = Composition.find("byName", "Astrid").first();
      // testing macaroons size list
      assertEquals(2, savedComposition.macaroonList.size());
   }

   public void deleteMacaroonFromComposition() {
      // creating dummy composition
      Composition angele = new Composition("Angele", "Angele composition description").save();

   }
}
