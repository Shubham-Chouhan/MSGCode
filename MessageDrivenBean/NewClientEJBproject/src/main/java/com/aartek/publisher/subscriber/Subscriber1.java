package com.aartek.publisher.subscriber;

import java.util.Properties;

import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;

public class Subscriber1 {
	public static void main(String[] args) throws Exception {
		Properties jndiProperties = new Properties();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProperties.put("jboss.naming.client.ejb.context", "true");

		// get the initial context
		InitialContext ctx = new InitialContext(jndiProperties);

		// lookup the topic object
		Topic topic = (Topic) ctx.lookup("jms/MyFirstTopic");

		// lookup the topic connection factory
		TopicConnectionFactory connFactory = (TopicConnectionFactory) ctx.lookup("jms/RemoteConnectionFactory");

		// create a topic connection
		TopicConnection topicConn = connFactory.createTopicConnection();

		// create a topic session
		TopicSession topicSession = topicConn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

		// create a topic subscriber
		TopicSubscriber topicSubscriber = topicSession.createSubscriber(topic);

		// start the connection
		topicConn.start();
		// receive the message
		TextMessage message = (TextMessage) topicSubscriber.receive();

		// print the message
		System.out.println("Message received from MyFirstTopic : " + message.getText());

		// close the topic connection
		topicConn.close();
	}
}
