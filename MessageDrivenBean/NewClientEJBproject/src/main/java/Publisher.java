
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.naming.*;
import javax.jms.*;

public class Publisher {
	public static void main(String[] args) {
		try {
			// Create and start connection
			Properties jndiProperties = new Properties();
			jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
			jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			jndiProperties.put("jboss.naming.client.ejb.context", "true");
			InitialContext ctx = new InitialContext(jndiProperties);
			TopicConnectionFactory f = (TopicConnectionFactory) ctx.lookup("jms/RemoteConnectionFactory");
			TopicConnection con = f.createTopicConnection();
			con.start();
			// 2) create queue Session
			TopicSession ses = con.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			// 3) get the Topic object
			Topic t = (Topic) ctx.lookup("jms/MyFirstTopic");
			// 4)create TopicPublisher object
			TopicPublisher publisher = ses.createPublisher(t);
			// 5) create TextMessage object
			TextMessage msg = ses.createTextMessage();

		//	 6) write message
			BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				System.out.println("Enter Msg, end to terminate:");
				String s = b.readLine();
				if (s.equals("end"))
					break;
				msg.setText(s);
				// 7) send message
				publisher.publish(msg);
				System.out.println("Message successfully sent.");
			}
//			msg.setText("Shuabhm message to Subscriber");
//			// 7) send message
//			publisher.publish(msg);
			// 8) connection close
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
