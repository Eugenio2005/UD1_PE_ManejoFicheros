/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Modelo.JugadorModelo;

import java.util.Scanner;

public class JugadorVista {

    private Scanner scanner = new Scanner(System.in);

    public void limpiarConsola() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
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

    public JugadorModelo obtenerDatosJugador() {
        limpiarConsola();
        System.out.print("Ingrese ID del jugador: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Limpiar buffer
        System.out.print("Ingrese nick_name: ");
        String nick_name = scanner.nextLine();
        System.out.print("Ingrese experiencia: ");
        int experience = scanner.nextInt();
        System.out.print("Ingrese nivel de vida: ");
        int life_level = scanner.nextInt();
        System.out.print("Ingrese monedas: ");
        int coins = scanner.nextInt();
        return new JugadorModelo(id, nick_name, experience, life_level, coins);
    }

    public void mostrarJugadores(String jugadores) {
        System.out.println(jugadores);
    }

    public int obtenerOpcion() {
        return scanner.nextInt();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
