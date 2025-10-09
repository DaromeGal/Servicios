package org.example.Palindromo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClientePalindromo {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 5051;

        try (Socket socket = new Socket(host, puerto);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             Scanner sc = new Scanner(System.in)) {

            String mensaje, respuesta;

            while (true) {
                System.out.print("CLIENTE: Introduce una frase (o quit): ");
                mensaje = sc.nextLine();
                salida.println(mensaje);

                respuesta = entrada.readLine();
                System.out.println("Servidor: " + respuesta);

                if (mensaje.equalsIgnoreCase("quit")) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
