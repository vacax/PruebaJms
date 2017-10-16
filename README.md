#Aplicación Prueba JMS - ActiveMQ
Software para probar las colas de publicación y subcripción 
con el servidor Apache ActiveMQ.

##Ejecutar:
* ./gradlew task full_jar
* java -jar PruebaJms-full-1.0.SNAPSHOT.jar 3 //**Inicio del servidor**
* java -jar PruebaJms-full-1.0.SNAPSHOT.jar 2 //**Inicio de cliente subcriptor** 
* java -jar PruebaJms-full-1.0.SNAPSHOT.jar 1 //**Cliente para publicar mensaje** 