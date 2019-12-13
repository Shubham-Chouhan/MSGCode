//
//package com.aartek;
//
//import javax.ejb.ActivationConfigProperty;
//import javax.ejb.MessageDriven;
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.MessageListener;
//import javax.jms.TextMessage;
//
//@MessageDriven(activationConfig = {
//
//		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
//
//		@ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/MyFirstTopic") }, mappedName = "jms/MyFirstTopic")
//
//public class TopicListner implements MessageListener {
//
//	public void onMessage(Message message) {
//		TextMessage msg = (TextMessage) message;
//		try {
//			System.out.println(
//					"MyMessageDrivenBean on message method is called and received the message : " + msg.getText());
//		} catch (JMSException e) {
//			e.printStackTrace();
//		}
//	}
//}
