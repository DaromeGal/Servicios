package org.example.MultiCast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ClienteHora {
    public static void main(String[] args) throws IOException {
        int port = 12345;

        MulticastSocket socket = new MulticastSocket(port);
        InetAddress group = InetAddress.getByName("225.0.0.1");
        socket.joinGroup(group);
        String cadena="";

        String msg="";
        boolean corte=false;
        System.out.println("Cliente unido al grupo multicast recibiendo ahora");
        while (!corte)
        {
            byte[] buf = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            msg = new String(packet.getData(), 0, packet.getLength(), StandardCharsets.UTF_8);
            System.out.println("Mensaje recibido: " + msg);
            System.out.println("seguir si/no");
            Scanner sc = new Scanner(System.in);
            cadena = sc.nextLine();
            if (cadena.equalsIgnoreCase("no")) {
                corte = true;
                socket.leaveGroup(group);
                socket.close();
                System.out.println("Cliente salido del grupo multicast y cerrado.");
            }
        }
    }
}
