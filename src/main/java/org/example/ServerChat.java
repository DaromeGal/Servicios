package org.example;



import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerChat {

    public static void main(String[] args) throws IOException {

        int puerto = 6000; // Puerto
        Scanner sc = new Scanner(System.in);

        ServerSocket servidor = new ServerSocket(puerto);
        System.out.println("Escuchando en " + servidor.getLocalPort());

        // esperando a un cliente
        Socket cliente1 = servidor.accept();
        System.out.println("Se ha conectado el cliente.");

        // Crear streams para comunicarse con el cliente
        DataInputStream in = new DataInputStream(cliente1.getInputStream());
        DataOutputStream out = new DataOutputStream(cliente1.getOutputStream());

        try {
            while (true) {
                // Recibir mensaje del cliente
                String recibido = in.readUTF();
                System.out.println("Cliente: " + recibido);

                // Enviar respuesta al cliente
                System.out.print("Servidor: ");
                String respuesta = sc.nextLine();
                out.writeUTF(respuesta);
            }
        } catch (IOException e) {
            System.out.println("Finalizando el programa...");
        } finally {
            // Cerrar recursos
            in.close();
            out.close();
            cliente1.close();
            servidor.close();
        }
    }
}