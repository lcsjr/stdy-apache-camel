package com.stdy.mensageria;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class AppSendToFila {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws NamingException, JMSException {
    
		InitialContext context = new InitialContext();
		
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory") ;
		Connection connection = factory.createConnection();
		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Destination destination = (Destination) context.lookup("financeiro");

		MessageProducer producer = session.createProducer(destination);
		
		for (int i=0;i<1000;i++) {
		
			Message message = session.createTextMessage("<pedido><numero>"+i+"</numero></pedido>");
			producer.send(message);
		}
		
		
		session.close();
		connection.close();
		context.close();
	}
}
