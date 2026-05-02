package ChatAsincrono;

import java.io.*;
import java.net.*;

/**
 * Hilo encargado de escuchar el socket y mostrar mensajes por pantalla.
 * 
 * @author Adrián Dondarza Martín
 * @version 1.0
 */
public class HiloRecibir extends Thread {
    private Socket socket;
    private BufferedReader br;

    // Consructor
    public HiloRecibir(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // Inicializa el flujo de entrada de datos del socket
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String mensaje;

            // Bucle infinito que lee mensajes hasta que la conexión se cierra
            while ((mensaje = br.readLine()) != null) {
                System.out.println("Mensaje recibido: " + mensaje);
                
                // Finaliza el hilo si se recibe el comando 'salir'
                if (mensaje.equalsIgnoreCase("salir")) break;
            }
        } catch (IOException e) {
            // Se ejecuta si el otro extremo cierra la conexión de forma abrupta
            System.err.println("Conexión cerrada por el otro extremo.");
        } finally {
            try {
                // Cierre preventivo del flujo de entrada
                if (br != null) br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}