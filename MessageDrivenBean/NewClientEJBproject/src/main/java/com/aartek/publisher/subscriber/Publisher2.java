package com.aartek.publisher.subscriber;     
 
import javax.naming.Context;
import javax.naming.InitialContext;                                                                           
import javax.jms.Topic;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.TopicPublisher;

import java.util.Properties;

import javax.jms.DeliveryMode;
import javax.jms.TopicSession;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
                                                                            
public class Publisher2
{
    public static void main(String[] args) throws Exception
    {
    	Properties jndiProperties = new Properties();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");																			
		jndiProperties.put("jboss.naming.client.ejb.context", "true");
		
       // get the initial context
       InitialContext ctx = new InitialContext(jndiProperties);
                                                                           
       // lookup the topic object
       Topic topic = (Topic) ctx.lookup("jms/MySecondTopic");
                                                                           
       // lookup the topic connection factory
       TopicConnectionFactory connFactory = (TopicConnectionFactory) ctx.
           lookup("jms/RemoteConnectionFactory");
                                                                           
       // create a topic connection
       TopicConnection topicConn = connFactory.createTopicConnection();
                                                                           
       // create a topic session
       TopicSession topicSession = topicConn.createTopicSession(false, 
           Session.AUTO_ACKNOWLEDGE);
                                                                           
       // create a topic publisher
       TopicPublisher topicPublisher = topicSession.createPublisher(topic);
       topicPublisher.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
                                                                           
       // create the "Hello World" message
       TextMessage message = topicSession.createTextMessage();
       message.setText("Hello World from MySecondTopic");
                                                                           
       // publish the messages
       topicPublisher.publish(message);
                                                                           
       // print what we did
       System.out.println("Message published from MySecondTopic : " + message.getText());
                                                                           
       // close the topic connection
       topicConn.close();
    }
}