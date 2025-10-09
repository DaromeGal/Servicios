package org.example.Palindromo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerPalindromo {

    public static void main(String[] args) throws IOException {
        int puerto = 5051;

        ServerSocket serverSocket = new ServerSocket(puerto);
            System.out.println("Servidor escuchando en el puerto " + puerto);

            Socket socket = serverSocket.accept();
            System.out.println("Tas conectado");

            DataInputStream dataInputStream= new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream= new DataOutputStream(socket.getOutputStream());

        boolean follow = true;
        while (follow) {
            String mensaje = dataInputStream.readUTF();
            System.out.println("Cliente: " + mensaje);

            if (mensaje.equalsIgnoreCase("quit")) {
                follow = false;
                dataOutputStream.writeUTF("Servidor: Conexión terminada.");
            } else {
                String respuesta= esPalo(mensaje);
                dataOutputStream.writeUTF("Servidor: " + respuesta);
            }
        }

        socket.close();
        serverSocket.close();



    }
    public static String esPalo(String str) {
        String cleaned = str.replaceAll("[\\W]", "").toLowerCase();
        String reversed = new StringBuilder(cleaned).reverse().toString();
        return cleaned.equals(reversed) ? "Es un palíndromo." : "No es un palíndromo.";
    }
}
