import org.junit.Test;

import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class ApplicationTest extends FunctionalTest {
   private static final String INTRODUCTION_QUOTE = "Imaginez une farandole de macarons";
   private static final String EVENT_DIV = "id=\"event\"";
   private static final String COMPOSITION_DIV = "id=\"composition\"";
   private static final String CURRENT_COMPOSITION_STYLE = "compositionShortDescriptionTitle";
   private static final String COMPOSITION_ENUM_QUOTE = "Nos compositions :";

   @Test
   public void testThatIndexPageWorks() {
      Response mainPage = GET("/");
      assertPageStatus(200, mainPage);

      // building index page string
      String indexPage = getContent(mainPage);
      assertNotNull(indexPage);
      // finding the introduction quote
      assertTrue(indexPage.indexOf(INTRODUCTION_QUOTE) > 1);
      // finding events div
      assertTrue(indexPage.indexOf(EVENT_DIV) > 1);
      // finding compositions div
      assertTrue(indexPage.indexOf(COMPOSITION_DIV) > 1);
   }

   private void assertPageStatus(int status, Response response) {
      assertStatus(status, response);
      assertContentType("text/html", response);
      assertCharset("utf-8", response);
   }
}
