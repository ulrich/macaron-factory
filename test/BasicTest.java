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
   public void createAndFindMacaroon() {
      // creating heavy macaroons
      new Macaroon(Shape.ROUND, new Color(139, 69, 19), 3.5f, 10f, "Chocolate", "chocolate1.gif", "The chocolate macaroon...").save();
      // creating light macaroons
      new Macaroon(Shape.ROUND, Color.WHITE, 3.5f, 5f, "Coconut", "Coconut1.gif", "The coconut macaroon...").save();
      // test the macaroons size list
      assertEquals(2, Macaroon.count());
      // finding heavy macaroon
      List<Macaroon> macaroons = Macaroon.find("byWeight", 10f).fetch();
      assertNotNull(macaroons);
      assertEquals(1, macaroons.size());
      assertEquals(10f, macaroons.get(0).weight, 0f);
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
