import java.util.Properties;

import org.apache.commons.lang.StringUtils;

import play.Play;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

@OnApplicationStart
public class Bootstrap extends Job {

   public void doJob() throws Exception {
      // load production data
      Fixtures.load("data-prod.yml");
      // check email configuration
      Properties properties = Play.configuration;
      String applicationMode = (String) properties.get("application.mode");
      if ("prod".equals(applicationMode)) {
         if (StringUtils.isBlank((String) properties.get("mail.smtp.user")) || StringUtils.isBlank((String) properties.get("mail.smtp.pass"))) {
            throw new RuntimeException("Unable to run application, email settings are blanks");
         }
      }
   }
}
