package br.com.caelum.jms;

import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Scanner;

public class TesteConsumidorTopicoEstoqueSelector {

    public static void main(String[] args) throws Exception {

        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();
        connection.setClientID("estoqueSelector");
        connection.start();

        Session session = connection.createSession(false ,Session.AUTO_ACKNOWLEDGE);

        Topic topico =  (Topic) context.lookup("loja");
        MessageConsumer consumer = session.createDurableSubscriber(topico, "assinatura-selector", "ebook is null OR ebook=false", false);

        consumer.setMessageListener(new MessageListJms());


       new Scanner(System.in).nextLine();

        session.close();
        connection.close();
        context.close();

    }
}
