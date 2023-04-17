package br.com.caelum.jms;

import br.com.caelum.model.Pedido;
import br.com.caelum.model.PedidoFactory;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.xml.bind.JAXB;
import java.io.StringWriter;
import java.io.Writer;

public class TesteProdutorTopico {

    public static void main(String[] args) throws Exception {

        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination topico = (Destination) context.lookup("loja");
        MessageProducer producer = session.createProducer(topico);

        // cria o pedido
        Pedido pedido = new PedidoFactory().geraPedidoComValores();

        // cria o xml atraves do pedido
        StringWriter writer = new StringWriter();
        JAXB.marshal(pedido, writer);
        String xml = writer.toString();
        System.out.println(xml);

        Message message = session.createTextMessage(xml);


        message.setBooleanProperty("ebook", false);
        producer.send(message);

        System.out.println("Mensagen Enviada!");

        session.close();
        connection.close();
        context.close();

    }
}
