package messagedrivenbean;

import java.util.Properties;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;

public class Subscriber {
	public static void main(String[] args) {
		Properties jndiProperties = new Properties();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProperties.put("jboss.naming.client.ejb.context", "true");
		try {
			// 1) Create and start connection
			InitialContext ctx = new InitialContext(jndiProperties);
			TopicConnectionFactory f = (TopicConnectionFactory) ctx.lookup("jms/RemoteConnectionFactory");
			TopicConnection con = f.createTopicConnection();
			con.start();
			// 2) create topic session
			TopicSession ses = con.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			// 3) get the Topic object
			Topic t = (Topic) ctx.lookup("jms/MyFirstTopic");
			// 4)create TopicSubscriber
			TopicSubscriber receiver = ses.createSubscriber(t);
			
			boolean onn=true;
			
				String old = null;
			while(onn){ 
//				// 5) create listener object
//
//				TextMessage message = (TextMessage) receiver.receive();
//			// 6) register the listener object with subscriber
//				 
//				if(!message.getText().equals(old) && !message.getText().equals(null))
//				{
//					old = message.getText();
//			System.out.println("Susbscriber Recive " + message.getText());
//			System.out.println("Subscriber1 is ready, waiting for messages...");
//                 
//			if(message.getText().equals("end")) {
//				onn=false; 
//            } 
				Thread.sleep(1000);
		}
		
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
