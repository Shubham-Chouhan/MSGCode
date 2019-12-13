import java.util.Date;
import java.util.Properties;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class Main {
	public static void main(String[] args) {
		Properties jndiProperties = new Properties();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProperties.put("jboss.naming.client.ejb.context", "true");

		try {
			Context context = new InitialContext(jndiProperties);
			Queue queue = (Queue) context.lookup("jms/MYFirstQueue");
			ConnectionFactory factory = (ConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
			Connection con = factory.createConnection();
			Session sess = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer producer = sess.createProducer(queue);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			TextMessage message = sess.createTextMessage("Hello world. The current time is : " + new Date());

			long end = System.currentTimeMillis() + 1000;
			while (System.currentTimeMillis() < end) {
				producer.send(message);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}