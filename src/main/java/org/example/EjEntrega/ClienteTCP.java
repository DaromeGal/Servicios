package org.example.EjEntrega;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteTCP {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 5050;

        try (Socket socket = new Socket(host, puerto)) {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            Scanner sc = new Scanner(System.in);
            String mensaje;

            System.out.println("Conectado al servidor. Escribe un número (o 'exit' para salir):");

            while (true) {
                mensaje = sc.nextLine();
                salida.println(mensaje);

                if (mensaje.equalsIgnoreCase("exit")) {
                    System.out.println("Cerrando cliente...");
                    break;
                }

                String respuesta = entrada.readLine();
                System.out.println("Servidor: " + respuesta);
            }

            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
