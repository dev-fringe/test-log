
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Log4j2Test {

    private static final Logger logger = LogManager.getLogger(Log4j2Test.class);
    
    public static void main(final String... args) {
        logger.debug("Entering application.");
        logger.error("Didn't do it.");
        logger.debug("Exiting application.");
    }
}
