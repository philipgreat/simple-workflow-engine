import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;

public class JMXTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MBeanServer server = MBeanServerFactory.createMBeanServer();
		server.getDomains();
		server.getDefaultDomain();
		server.getMBeanCount();
		//server.registerMBean(server, name)
	}

}
