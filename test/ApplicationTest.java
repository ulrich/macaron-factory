import java.io.ByteArrayOutputStream;

import org.junit.Test;

import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class ApplicationTest extends FunctionalTest {
   private static final String QUOTE = "Imaginez une farandole de macarons";

   @Test
   public void testThatIndexPageWorks() {
      Response response = GET("/");
      assertIsOk(response);
      assertContentType("text/html", response);
      assertCharset("utf-8", response);

      ByteArrayOutputStream baos = response.out;
      String indexPage = new String(baos.toByteArray());
      assertNotNull(indexPage);
      assertTrue(indexPage.indexOf(QUOTE) > 1);
   }
}
