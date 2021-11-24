import org.apache.log4j.Logger;

public class Log4jTest {

	private static Logger LOGGER = Logger.getLogger(Log4jTest.class);

	public static void main(String[] args) {
		LOGGER.info("a test message");
	}
}
