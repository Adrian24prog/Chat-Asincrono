package ChatAsincrono;

import java.net.*;

/**
 * Clase Servidor que gestiona la escucha de conexiones entrantes.
 * 
 * @author Adrián Dondarza Martín
 * @version 1.0
 * @see HiloEnviar
 * @see HiloRecibir
 */
public class Servidor {
    public static void main(String[] args) {
        // Puerto en el que el servidor estará esperando conexiones
        int puerto = 5000;

        /* 
         * Uso de try-catch para crear el ServerSocket. 
         * Esto garantiza que el socket se cierre automáticamente al terminar el programa,
         * liberando el puerto del sistema operativo.
         */
        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor a la escucha en el puerto " + puerto);

            /* 
             * El método accept() detiene la ejecución hasta que 
             * un cliente intenta conectarse al puerto definido.
             */
            Socket socket = servidor.accept();
            
            // Informamos sobre la IP del cliente que se ha conectado
            System.out.println("Cliente conectado desde: " + socket.getInetAddress());

             
            //Lanzamos los hilos para gestionar la comunicación asíncrona.

            // Instancia e inicio del hilo para mandar mensajes al cliente
            new HiloEnviar(socket).start();
            
            // Instancia e inicio del hilo para leer mensajes provenientes del cliente
            new HiloRecibir(socket).start();

        } catch (Exception e) {
        	// Capturación de errores
            System.err.println("Error en el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}