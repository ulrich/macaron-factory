import java.io.ByteArrayOutputStream;

import org.junit.Test;

import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class ApplicationTest extends FunctionalTest {
   private static final String INTRODUCTION_QUOTE = "Imaginez une farandole de macarons";
   private static final String EVENT_DIV = "id=\"event\"";
   private static final String COMPOSITION_DIV = "id=\"composition\"";

   @Test
   public void testThatIndexPageWorks() {
      Response response = GET("/");
      assertIsOk(response);
      assertContentType("text/html", response);
      assertCharset("utf-8", response);

      // building index page string
      ByteArrayOutputStream baos = response.out;
      String indexPage = new String(baos.toByteArray());
      assertNotNull(indexPage);
      // finding the introduction quote

      assertTrue(indexPage.indexOf(INTRODUCTION_QUOTE) > 1);
      // finding events div
      assertTrue(indexPage.indexOf(EVENT_DIV) > 1);
      // finding compositions div
      assertTrue(indexPage.indexOf(COMPOSITION_DIV) > 1);
   }
}
