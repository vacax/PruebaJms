package com.avathartech.pruebajms.main;

import com.avathartech.pruebajms.jms.Consumidor;
import com.avathartech.pruebajms.jms.Productor;
import com.avathartech.pruebajms.jms.TipoCola;
import org.apache.activemq.broker.BrokerService;

import javax.jms.JMSException;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Ejemplo para probar el uso de JMS, utilizando servidor de mensajeria ActiveMQ, ver en
 * http://activemq.apache.org/
 * Created by vacax on 03/10/15.
 */
public class Main {

    public static void main(String[] args) throws IOException, JMSException  {
        System.out.println("Prueba de Mensajeria Asincrona");
        String cola = "pruebajms.cola";
        TipoCola tipoCola;

        if(args.length == 0){
            mensajesParametros();
            return;
        }

        int opcion = Integer.parseInt(args[0]);
        
        if(opcion == 2 || opcion == 4){
            if(args.length > 1){
                cola = args[1];
                new Productor().enviarMensaje(cola, args[2], opcion == 2 ? TipoCola.TOPIC : TipoCola.QUEUE);
            }else{
                System.out.println("Si aplicacion == 1, debe enviar segundo parametro para el mesanje");
                return;
            }
        } else if(opcion == 3 || opcion == 5){

            if(args.length > 1){
                System.out.println("Cambiando la cola");
                cola = args[1];
            }

            //indicando el consumidor con la cola.
            Consumidor consumidor= new Consumidor(cola, opcion == 3 ? TipoCola.TOPIC : TipoCola.QUEUE);
            consumidor.conectar();

            System.out.println("Presiona Enter para salir del programa:");
            Scanner s = new Scanner(System.in);
            String dato = s.nextLine();
            consumidor.cerrarConexion();
            System.exit(0);

        }else if(opcion == 1){
            System.out.println("Inicializando Servidor JMS");
            try {
                //Subiendo la versión embedded de ActiveMQ.
                //http://activemq.apache.org/how-do-i-embed-a-broker-inside-a-connection.html
                BrokerService broker = new BrokerService();
                //configurando el broker.
                broker.addConnector("tcp://0.0.0.0:61616");
                //Inicializando
                broker.start();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        else{
            mensajesParametros();
        }

    }

    /**
     * Mostrando opciones disponible
     */
    private static void mensajesParametros(){
        System.out.println("Deben enviar los parametros: modo-aplicacion tipo-cola [mensaje]");
        System.out.println("Si modo-aplicacion == 1, Inicializa el modo Embedded");
        System.out.println("Si modo-aplicacion == 2, debe enviar 2do parametro nombre cola, 3er parametro para el mensaje - tipo Cola Topic");
        System.out.println("Si modo-aplicacion == 3, sube en modo consumidor cola Topic");
        System.out.println("Si modo-aplicacion == 4, debe enviar 2do parametro nombre cola, 3er parametro para el mensaje - tipo Cola Queue");
        System.out.println("Si modo-aplicacion == 5, sube en modo consumidor cola Queue");
    }
}
