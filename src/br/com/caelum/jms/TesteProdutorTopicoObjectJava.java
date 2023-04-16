package br.com.caelum.jms;

import br.com.caelum.model.Pedido;
import br.com.caelum.model.PedidoFactory;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.xml.bind.JAXB;
import java.io.StringWriter;

public class TesteProdutorTopicoObjectJava {

    public static void main(String[] args) throws Exception {

        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination topico = (Destination) context.lookup("loja");
        MessageProducer producer = session.createProducer(topico);

        Pedido pedido = new PedidoFactory().geraPedidoComValores();
        Message message = session.createObjectMessage(pedido);

        message.setBooleanProperty("ebook", false);
        producer.send(message);

        System.out.println("Mensagen Enviada!");

        session.close();
        connection.close();
        context.close();

    }
}
