package org.example.EjEntregaEx5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class NarcCliente {


    private static boolean esNumeroDeTresCifras(String entrada) {
        // Comprueba si la entrada es un número y si tiene 3 dígitos.
        return entrada != null && entrada.matches("\\d{3}");
    }

    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int puerto = 11223; // El mismo puerto que el servidor

        DatagramSocket socket = new DatagramSocket();
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        byte[] bufferEnvio;
        byte[] bufferRecepcion = new byte[1024];
        boolean salir = false;

        System.out.println("Cliente UDP iniciado. Introduce un número de 3 cifras.");
        System.out.println("Escribe 'quit' para salir.");

        while (!salir) {
            System.out.print("> ");
            String linea = lector.readLine();

            // 1. Filtro de entrada
            if (linea.equalsIgnoreCase("quit")) {
                salir = true;
            } else if (!esNumeroDeTresCifras(linea)) {
                System.out.println("Error: La entrada debe ser un número de 3 cifras.");
                continue; // Vuelve a pedir la entrada
            }

            // 2. Preparar y enviar el paquete
            bufferEnvio = linea.getBytes();
            InetAddress direccionServidor = InetAddress.getByName(host);
            DatagramPacket paqueteEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length, direccionServidor, puerto);
            socket.send(paqueteEnvio);

            if (salir) {
                System.out.println("Enviando señal de cierre al servidor...");
                continue; // No espera respuesta si se va a cerrar
            }

            // 3. Recibir la respuesta del servidor
            DatagramPacket paqueteRecepcion = new DatagramPacket(bufferRecepcion, bufferRecepcion.length);
            socket.receive(paqueteRecepcion);

            String respuesta = new String(paqueteRecepcion.getData(), 0, paqueteRecepcion.getLength());
            System.out.println("Respuesta del servidor: " + respuesta);
        }

        System.out.println("Cliente cerrado.");
        socket.close();
    }
}
