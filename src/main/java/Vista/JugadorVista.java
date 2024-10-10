/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class JugadorVista {

    public void limpiarConsola(){
//        try {
//            Robot limpiar = new Robot();
//            limpiar.keyPress(KeyEvent.VK_CONTROL);
//            limpiar.keyPress(KeyEvent.VK_L);
//            limpiar.keyRelease(KeyEvent.VK_CONTROL);
//            limpiar.keyRelease(KeyEvent.VK_L);
//
//        } catch (Exception e) {
//            System.out.println("Error al limpiar la pantalla" + e.getMessage());
//        }
    }

    public void mostrarMenuCargaJugadores() {
        System.out.println("Seleccione el fichero que vamos a cargar:");
        System.out.println("1. Fichero secuencial de texto");
        System.out.println("2. Fichero secuencial binario");
        System.out.println("3. Fichero de objetos binario");
        System.out.println("4. Fichero de acceso aleatorio binario");
        System.out.println("5. Fichero de texto XML");
    }

    public void mostrarMenuPrincipal(){
        limpiarConsola();
        System.out.println("1. Agregar Jugador");
        System.out.println("2. Eliminar Jugador");
        System.out.println("3. Modificar Jugador");
        System.out.println("4. Listar Jugadores");
        System.out.println("5. Listar Jugadores por ID");
        System.out.println("6. Configuración");
        System.out.println("7. Salir");
    }

    public void mostrarSubMenuAlmacenamiento(){
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
