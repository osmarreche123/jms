package br.com.caelum.jms;

import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Scanner;

public class TesteConsumidorFila {

    public static void main(String[] args) throws Exception {

        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();

        // cliente tem que confirmar a transação

        Session session = connection.createSession(true ,Session.SESSION_TRANSACTED);

        Destination fila =  (Destination) context.lookup("financeiro");
        connection.start();

        MessageConsumer consumer = session.createConsumer(fila);

        consumer.setMessageListener(new MessageListener() {

            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    // transacao sendo confirmada
//                    message.acknowledge();
                    System.out.println(textMessage.getText());
                    session.commit();

                } catch (JMSException e){
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
