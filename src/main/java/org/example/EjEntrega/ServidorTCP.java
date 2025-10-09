package org.example.EjEntrega;

import java.io.*;
import java.net.*;

public class ServidorTCP {
    public static void main(String[] args) {
        int puerto = 5050;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto);

            while (true) {
                Socket socket = servidor.accept();
                System.out.println("Cliente conectado.");

                // Streams de entrada y salida
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

                String mensaje;
                while ((mensaje = entrada.readLine()) != null) {
                    if (mensaje.equalsIgnoreCase("exit")) {
                        salida.println("Conexión cerrada.");
                        break;
                    }

                    try {
                        int numero = Integer.parseInt(mensaje);
                        if (NumeroPerfecto.esPerfecto(numero)) {
                            salida.println("El número " + numero + " es perfecto.");
                        } else {
                            salida.println("El número " + numero + " NO es perfecto.");
                        }
                    } catch (NumberFormatException e) {
                        salida.println("Error: Debes enviar un número entero válido.");
                    }
                }

                socket.close();
                System.out.println("Cliente desconectado.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
