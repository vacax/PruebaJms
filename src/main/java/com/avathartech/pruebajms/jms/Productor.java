package com.avathartech.pruebajms.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by vacax on 04/10/15.
 */
public class Productor {

    public Productor(){

    }

    /**
     *
     * @param cola
     * @param mensajeEnviar
     * @throws Exception
     */
    public void enviarMensaje(String cola, String mensajeEnviar) throws JMSException {

        //Creando el connection factory indicando el host y puerto, en la trama el failover indica que reconecta de manera
        // automatica
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        //Crea un nuevo hilo cuando hacemos a conexión, que no se detiene cuando
        // aplicamos el metodo stop(), para eso tenemos que cerrar la JVM o
        // realizar un close().
        Connection connection = factory.createConnection("admin", "admin");
        connection.start();

        // Creando una sesión no transaccional y automatica.
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Creamos o nos connectamos a la una cola, por defecto ActiveMQ permite
        // la creación si no existe. Si la cola es del tipo Queue es acumula los mensajes, si es
        // del tipo topic es en el momento.

        //Queue queue = session.createQueue(cola);
        Topic topic = session.createTopic(cola);


        // Creando el objeto de referencia para enviar los mensajes.
        MessageProducer producer = session.createProducer(topic);


        String mensaje = mensajeEnviar;


        // Creando un simple mensaje de texto y enviando.
        TextMessage message = session.createTextMessage(mensaje);
        producer.send(message);

        //Desconectando la referencia.
        producer.close();
        session.close();
        connection.stop();

        System.exit(0);
    }
}
