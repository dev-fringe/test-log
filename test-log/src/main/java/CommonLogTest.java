import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CommonLogTest {

	  private static  Log log = LogFactory.getLog(CommonLogTest.class);

	public static void main(String[] args) {
		log.info("Test info");
        log.debug("Test info");

	}
}
