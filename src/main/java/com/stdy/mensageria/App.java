package com.stdy.mensageria;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class App {

	public static void main(String[] args) throws NamingException, JMSException {
    
		InitialContext context = new InitialContext();
		
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory") ;
		Connection connection = factory.createConnection();
		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Destination destination = (Destination) context.lookup("financeiro");
		MessageConsumer consumer = session.createConsumer(destination);
		
		Message message = consumer.receive();
		
		System.out.println("Recebendo mensagem: "+ message);
		
		
		session.close();
		connection.close();
		context.close();
	}
}
