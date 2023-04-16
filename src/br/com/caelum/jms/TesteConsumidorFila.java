package br.com.caelum.jms;

import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Scanner;

public class TesteConsumidorFila {

    public static void main(String[] args) throws Exception {

        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();

        Session session = connection.createSession(false ,Session.AUTO_ACKNOWLEDGE);

        Destination fila =  (Destination) context.lookup("financeiro");
        connection.start();

        MessageConsumer consumer = session.createConsumer(fila);

        consumer.setMessageListener(new MessageListJms());


       new Scanner(System.in).nextLine();

        session.close();
        connection.close();
        context.close();

    }
}
