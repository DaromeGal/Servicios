package org.example.MultiCast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ServidorHora {
    public static void main(String[] args) throws IOException {
        int port=12345;
        String host="225.0.0.1";

        MulticastSocket ms= new MulticastSocket(port);
        InetAddress group=InetAddress.getByName(host);
        ms.joinGroup(group);

        String cadena="";
        while(true) {
            try{
                Thread.sleep(5000);
                cadena= obtenerHoraActualyFechaActual();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            byte[] buf = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length, group, port);
            ms.send(datagramPacket);
        }//while



    }//main
    public static String obtenerHoraActualyFechaActual(){
        LocalDate fecha= LocalDate.now();

        LocalTime hora= LocalTime.now();

        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter dtf2=DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaFormateada= hora.format(dtf);
        String fechaFormateada= fecha.format(dtf2);

        return "Fecha: "+fechaFormateada+" Hora: "+horaFormateada;

    }
}
