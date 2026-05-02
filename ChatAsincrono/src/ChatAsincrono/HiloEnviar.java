package ChatAsincrono;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * Hilo encargado de leer la entrada del usuario y enviarla usando BufferedWriter.
 * 
 * @author Adrián Dondarza Martín
 * @version 1.0
 */
public class HiloEnviar extends Thread {
    private Socket socket;
    private BufferedWriter out;

    //Constructor
    public HiloEnviar(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // Inicializamos BufferedWriter sobre el flujo de salida del socket
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            Scanner teclado = new Scanner(System.in);
            String mensaje;

            while (true) {
                mensaje = teclado.nextLine();
                
                // Escribimos el mensaje, añadimos línea nueva y forzamos el envío
                out.write(mensaje);
                out.newLine();
                out.flush(); 
                
                // Finaliza el hilo si se recibe el comando 'salir'
                if (mensaje.equalsIgnoreCase("salir")) break;
            }
        }  catch (IOException e) {
            if (socket.isClosed()) {
                System.out.println("Cerrando conexión de forma segura");
            } else {
                System.err.println("Error de comunicación: " + e.getMessage());
            }
        }
    }
}