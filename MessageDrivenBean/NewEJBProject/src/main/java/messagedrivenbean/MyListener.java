
//package messagedrivenbean;
//
//import javax.jms.*;
//import javax.ejb.ActivationConfigProperty;
//import javax.ejb.MessageDriven;
//
////@MessageDriven(activationConfig = {
////  
////  @ActivationConfigProperty(propertyName = "destinationType", propertyValue =
////  "javax.jms.Topic"),
////  
////  @ActivationConfigProperty(propertyName = "destination", propertyValue =
////  "jms/MyFirstTopic") }
////  // @ActivationConfigProperty(propertyName ="destination", propertyValue = "jms/MySecondTopic"),
////  // @ActivationConfigProperty(propertyName = "destinationType", propertyValue= "javax.jms.Topic"), }
////  , mappedName = "jms/MyFirstTopic") 
//  public class MyListener implements MessageListener {
//  
//	public void onMessage(Message m) {
//		try {
//			TextMessage msg = (TextMessage) m;
//
//			System.out.println("following message is received:" + msg.getText());
//		} catch (JMSException e) {
//			System.out.println(e);
//		}
//	} }
