
package Modelo;

import java.io.*;

public class InicializadorFicheros {

    private static final String CARPETA_FICHEROS = "ficheros_jugadores"; // Nombre de la carpeta

    public static void inicializarFicheros() {
        // Crea la carpeta "ficheros_jugadores" en el directorio raíz del proyecto si no existe
        File carpeta = new File(CARPETA_FICHEROS);
        if (!carpeta.exists()) {
            carpeta.mkdir(); // Crear la carpeta
            System.out.println("Carpeta creada: " + CARPETA_FICHEROS);
        } else {
            System.out.println("La carpeta ya existe.");
        }

        // Inicializa cada tipo de fichero
        try {
            // Fichero secuencial de texto
            File ficheroTexto = new File(CARPETA_FICHEROS + "/jugadores.txt");
            if (ficheroTexto.createNewFile()) {
                System.out.println("Fichero de texto creado.");
            }

            // Fichero secuencial binario
            File ficheroBinario = new File(CARPETA_FICHEROS + "/jugadores.bin");
            if (ficheroBinario.createNewFile()) {
                System.out.println("Fichero binario creado.");
            }

            // Fichero de objetos binario
            File ficheroObjetos = new File(CARPETA_FICHEROS + "/jugadores_objetos.bin");
            if (ficheroObjetos.createNewFile()) {
                System.out.println("Fichero de objetos binario creado.");
            }

            // Fichero de acceso aleatorio binario
            File ficheroAccesoAleatorio = new File(CARPETA_FICHEROS + "/jugadores_acceso_aleatorio.bin");
            if (ficheroAccesoAleatorio.createNewFile()) {
                System.out.println("Fichero de acceso aleatorio binario creado.");
            }

            // Fichero de texto XML
            File ficheroXML = new File(CARPETA_FICHEROS + "/jugadores.xml");
            if (ficheroXML.createNewFile()) {
                System.out.println("Fichero de texto XML creado.");
            }

        } catch (IOException e) {
            System.err.println("Error al crear los ficheros: " + e.getMessage());
        }
    }
    
    public String getRuta_Guardados() {
        return CARPETA_FICHEROS;
    }
}
