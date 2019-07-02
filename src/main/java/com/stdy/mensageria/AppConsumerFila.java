package com.stdy.mensageria;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class AppConsumerFila {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws NamingException, JMSException {
    
		InitialContext context = new InitialContext();
		
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory") ;
		Connection connection = factory.createConnection();
		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Destination destination = (Destination) context.lookup("financeiro");
		MessageConsumer consumer = session.createConsumer(destination);
		
//		Message message = consumer.receive();
//		System.out.println("Recebendo apenas uma mensagem: "+ message);
		
		consumer.setMessageListener( new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
				
				TextMessage textMessage = (TextMessage) message; 
				
				try {
					System.out.println(textMessage.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		
		

		new Scanner(System.in).nextLine();
		
		session.close();
		connection.close();
		context.close();
	}
}
