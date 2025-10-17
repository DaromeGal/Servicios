package org.example.EjEntregaEx5;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class NarcServer {
    public static void main(String[] args) throws IOException {
        int port=11223;
        DatagramSocket socket= new DatagramSocket(port);

        byte[]recibir = new byte[1024];
        byte[]enviar;
        boolean corte=false;

        while(!corte){
            //1 Recibir
            DatagramPacket recibirDatos= new DatagramPacket(recibir, recibir.length);
            socket.receive(recibirDatos);
            String frase= new String(recibirDatos.getData(),0,recibirDatos.getLength());
            System.out.println("recibido..."+frase);

            if (!frase.equals("quit")){
                String devolver= comprobarNarc(frase);
                enviar=devolver.getBytes();
                DatagramPacket enviarDatos= new DatagramPacket(enviar,enviar.length,
                        recibirDatos.getAddress(),recibirDatos.getPort());
                socket.send(enviarDatos);
            }
        else{
                corte=!corte;
                System.out.println("cerrando servicio...");
            }
        }
        socket.close();

    }
    public static String comprobarNarc(String frase){
        int num= Integer.parseInt(frase);
        int suma=0;
        StringBuilder sb= new StringBuilder();
        for (int i = 0; i < frase.length(); i++) {
            int digito= Character.getNumericValue(frase.charAt(i));
            suma+=Math.pow(digito,frase.length());
        }
        if (suma==num){
            sb.append(num).append(" es un número narcisista");
        } else{
            sb.append(num).append(" no es un número narcisista");
        }
        return sb.toString();
    }
}
