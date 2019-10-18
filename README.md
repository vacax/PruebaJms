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
