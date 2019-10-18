# Aplicación Prueba JMS - ActiveMQ
Software para probar las colas de publicación y subcripción 
con el servidor Apache ActiveMQ.

## Ejecutar:
* ./gradlew task shadowjar
* java -jar prueba-jms.jar 1 //**Inicio del servidor**
* java -jar prueba-jms.jar 2 //**Cliente para publicar mensaje - Cola Topic** 
* java -jar prueba-jms.jar 3 //**Inicio de cliente subcriptor - Cola Topic** 
* java -jar prueba-jms.jar 4 //**Cliente para publicar mensaje - Cola Queue** 
* java -jar prueba-jms.jar 5 //**Inicio de cliente subcriptor - Cola Queue**

En caso de utilizar ActiveMQ de forma independiente pueden utilizar la siguiente imagen de docker:

docker pull rmohr/activemq

La documentación de la imagen [https://hub.docker.com/r/rmohr/activemq] 
