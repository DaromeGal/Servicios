package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class Ejemplo3URLConn {
    public static void main(String[] args) throws Exception {
        String cadena;
        URL url = new URL("http://localhost/2014/vernombre.html");
        URLConnection conexion = url.openConnection();

        System.out.println("Dirección [getURL()]: " + conexion.getURL());
        Date fecha = new Date(conexion.getLastModified());
        System.out.println("Fecha última modificación [getLastModified()]: " + fecha);
        System.out.println("Tipo de Contenido (getContentType()): " + conexion.getContentType());
        System.out.println("=====");
        System.out.println("TODOS LOS CAMPOS DE CABECERA CON getHeaderFields():");

        Map<String, java.util.List<String>> camposcabecera = conexion.getHeaderFields();
        Iterator<Map.Entry<String, java.util.List<String>>> it = camposcabecera.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, java.util.List<String>> map = it.next();
            System.out.println(map.getKey() + ": " + map.getValue());
        }

        System.out.println("CAMPOS 1 Y 4 DE CABECERA:");
        System.out.println("getHeaderField(1): " + conexion.getHeaderField(1));
        System.out.println("getHeaderField(4): " + conexion.getHeaderField(4));
        System.out.println("Contenido de [url.getFile()]: " + url.getFile());

        BufferedReader pagina = new BufferedReader(new InputStreamReader(url.openStream()));
        while ((cadena = pagina.readLine()) != null) {
            System.out.println(cadena);
        }
    }
}


