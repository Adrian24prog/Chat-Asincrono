package ChatAsincrono;

import java.net.*;

/**
 * Clase Cliente que inicia la conexión con el servidor de chat.
 * 
 * @author Adrián Dondarza Martín
 * @version 1.0
 * @see HiloEnviar
 * @see HiloRecibir
 */
public class Cliente {
    public static void main(String[] args) {
        // Dirección IP
        String host = "localhost";
        
        // El puerto debe coincidir exactamente con el que ha abierto el servidor
        int puerto = 5000;
        
        try {
            // Se intenta establecer la conexión creando un nuevo Socket
            // Esta línea bloquea la ejecución hasta que el servidor acepta la conexión
            Socket socket = new Socket(host, puerto);
            
            // Si llegamos aquí, la conexión ha sido exitosa
            System.out.println("Conectado al servidor " + host);

            
              //Creamos e iniciamos los hilos de ejecución.
              //Al usar .start(), el método run() de cada clase se ejecuta en paralelo,
              //permitiendo que el cliente pueda escribir y leer al mismo tiempo.
             
            
            // Hilo encargado de leer del teclado y mandar al servidor
            new HiloEnviar(socket).start();
            
            // Hilo encargado de escuchar lo que el servidor mande y mostrarlo en consola
            new HiloRecibir(socket).start();

        } catch (Exception e) {
            // Capturación de errores
            System.err.println("Error al intentar conectar con el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}