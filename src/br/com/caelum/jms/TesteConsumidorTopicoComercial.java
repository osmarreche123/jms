package br.com.caelum.jms;

import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Scanner;

public class TesteConsumidorTopicoComercial {

    public static void main(String[] args) throws Exception {

        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();
        connection.setClientID("comercial");
        connection.start();

        Session session = connection.createSession(false ,Session.AUTO_ACKNOWLEDGE);

        Topic topico =  (Topic) context.lookup("loja");
        MessageConsumer consumer = session.createDurableSubscriber(topico, "assinatura");

        consumer.setMessageListener(new MessageListJms());


       new Scanner(System.in).nextLine();

        session.close();
        connection.close();
        context.close();

    }
}
