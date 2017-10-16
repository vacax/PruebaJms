package com.avathartech.pruebajms.main;

import com.avathartech.pruebajms.jms.Consumidor;
import com.avathartech.pruebajms.jms.Productor;
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

        if(args.length == 0){
            mensajesParametros();
            return;
        }

        if(Integer.parseInt(args[0]) == 1){
            if(args.length > 1){
                new Productor().enviarMensaje(cola, args[1]);
            }else{
                System.out.println("Si aplicacion == 1, debe enviar segundo parametro para el mesanje");
                return;
            }
        } else if(Integer.parseInt(args[0]) == 2){
            Consumidor consumidor=new Consumidor(cola);
            consumidor.conectar();

            System.out.println("Presiona Enter para salir del programa:");
            Scanner s = new Scanner(System.in);
            String dato = s.nextLine();
            consumidor.cerrarConexion();
            System.exit(0);

        }else if(Integer.parseInt(args[0]) == 3){
            System.out.println("Inicializando Servidor JMS");
            try {
                //Subiendo la versión embedded de ActiveMQ.
                //http://activemq.apache.org/how-do-i-embed-a-broker-inside-a-connection.html
                BrokerService broker = new BrokerService();
                //configurando el broker.
                broker.addConnector("tcp://localhost:61616");
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
     *
     */
    private static void mensajesParametros(){
        System.out.println("Deben enviar los parametros: aplicacion [mensaje]");
        System.out.println("Si aplicacion == 1, debe enviar segundo parametro para el mesanje");
        System.out.println("Si aplicacion == 2, sube en modo consumidor");
        System.out.println("Si aplicacion == 3, Inicializa el modo Embedded");
    }
}
