package org.example.EjEntrega;

public class NumeroPerfecto {

    // Método que comprueba si un número es perfecto
    public static boolean esPerfecto(int numero) {
        if (numero <= 0) return false;

        int suma = 0;
        for (int i = 1; i <= numero / 2; i++) {
            if (numero % i == 0) {
                suma += i;
            }
        }
        return suma == numero;
    }
}
