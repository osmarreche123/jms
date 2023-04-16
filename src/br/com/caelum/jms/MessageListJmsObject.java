package br.com.caelum.jms;

import br.com.caelum.model.Pedido;

import javax.jms.*;

public class MessageListJmsObject implements MessageListener{


    @Override
    public void onMessage(Message message) {

        ObjectMessage objectMessage = (ObjectMessage) message;
        try {
            Pedido pedido = ((Pedido) objectMessage.getObject());
            System.out.println(pedido.getItens());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
