/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

public class JugadorVista {

    public void limpiarConsola() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                // Comando para Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Comando para Unix/Linux/Mac
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Error al limpiar la consola: " + e.getMessage());
        }
    }

    public void mostrarMenuCargaJugadores() {
        limpiarConsola();
        System.out.println("Seleccione el fichero que vamos a cargar:");
        System.out.println("1. Fichero secuencial de texto");
        System.out.println("2. Fichero secuencial binario");
        System.out.println("3. Fichero de objetos binario");
        System.out.println("4. Fichero de acceso aleatorio binario");
        System.out.println("5. Fichero de texto XML");
    }

    public void mostrarMenuPrincipal() {
        limpiarConsola();
        System.out.println("1. Agregar Jugador");
        System.out.println("2. Listar Jugadores");
        System.out.println("3. Modificar Jugador");
        System.out.println("4. Eliminar Jugador");
        System.out.println("5. Configuración");
        System.out.println("6. Salir");
    }

    public void mostrarSubMenuAlmacenamiento() {
        limpiarConsola();
        System.out.println("Seleccione el tipo de almacenamiento:");
        System.out.println("1. Fichero secuencial de texto");
        System.out.println("2. Fichero secuencial binario");
        System.out.println("3. Fichero de objetos binario");
        System.out.println("4. Fichero de acceso aleatorio binario");
        System.out.println("5. Fichero de texto XML");
    }

    public void mostrarJugadores(String jugadores) {
        System.out.println(jugadores);
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
