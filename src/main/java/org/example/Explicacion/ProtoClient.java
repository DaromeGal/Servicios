package org.example.Explicacion;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;


    public class ProtoClient {
        public static void main(String[] args) {
            String host = "localhost"; // pepe ==> 10.101.3.86


            int puerto = 6000; // puerto remoto
            // El server esta en ip 10.101.21.152
            // ABRIR SOCKET
            try {
                Socket cliente = new Socket(host, puerto); // conecta
                InetAddress inet = cliente.getInetAddress();
                System.out.println("IP local usada por el cliente: " +inet.toString());

                System.out.println("Puerto local: " + cliente.getLocalPort());
                System.out.println("Datos de conexion externa");
                System.out.println("Puerto remoto: " + cliente.getPort());
                System.out.println("Host remoto: " + inet.getHostName());
                System.out.println("IP Host remoto: " + inet.getHostAddress());

                // VOY A ESCRIBIR ALGO
                DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
                out.writeUTF("Hola me llamo cancer");

                cliente.close(); // cierra el socket
            } catch (IOException e) {
                System.err.println("Error de conexion: " + e.getMessage());
            }
        }

    }
