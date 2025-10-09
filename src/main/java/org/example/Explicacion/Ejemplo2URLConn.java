
package org.example.Explicacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Ejemplo2URLConn {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost/2014/vernombre.php");
            URLConnection conexion = url.openConnection();
            conexion.setDoOutput(true);

            String cadena = "nombre=Maria Jesus&apellidos=Ramos Martin";

            // ESCRIBIR EN LA URL
            PrintWriter output = new PrintWriter(conexion.getOutputStream());
            output.write(cadena);
            output.close(); // cerrar flujo

            // LEER DE LA URL
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conexion.getInputStream()));
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
            reader.close(); // cerrar flujo
        } catch (MalformedURLException me) {
            System.err.println("MalformedURLException: " + me);
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe);
        }
    }
}