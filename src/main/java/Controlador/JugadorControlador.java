/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import static Modelo.InicializadorFicheros.inicializarFicheros;

import Modelo.*;
import Vista.JugadorVista;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JugadorControlador {

    private Scanner sc = new Scanner(System.in);
    private List<JugadorModelo> jugadores;
    private String tipoAlmacenamiento = "texto"; // Valor por defecto
    private final JugadorVista view;
    private final LeerFicheros leeFicheros = new LeerFicheros();
//    private final GuardarJugadores GUARDADO = new GuardarJugadores();
    private final GuardarJugadores2 GUARDADO2 = new GuardarJugadores2();
    
    public JugadorControlador(JugadorVista view) {
        this.view = view;
        this.jugadores = new ArrayList<>();
    }

    public void iniciar() {
        int opcion;
        inicializarFicheros();
        do {
            view.mostrarMenuCargaJugadores();
            opcion = obtenerOpcion();

            switch (opcion) {
                case 1:
                    tipoAlmacenamiento = "texto";
                    break;
                case 2:
                    tipoAlmacenamiento = "binario";
                    break;
                case 3:
                    tipoAlmacenamiento = "objetos";
                    break;
                case 4:
                    tipoAlmacenamiento = "accesoAleatorio";
                    break;
                case 5:
                    tipoAlmacenamiento = "xml";
                    break;
                default:
                    view.mostrarMensaje("Opción no válida.");
            }
            view.mostrarMensaje("Tipo de guardado: " + tipoAlmacenamiento);

            leeFicheros.cargarJugadores(tipoAlmacenamiento);
            jugadores = leeFicheros.getJugadores();

        } while (opcion < 0 && opcion > 6);
        MenuInicial();
    }

    public void MenuInicial() {
        int opcion;
        do {
            view.mostrarMenuPrincipal();
            opcion = obtenerOpcion();

            switch (opcion) {
                case 1:
                    agregarJugador();
                    break;
                case 2:
                    eliminarJugador();
                    break;
                case 3:
                    modificarJugador();
                    break;
                case 4:
                    listarJugadores();
                    break;
                case 5:
                    listarJugadoresID();
                    break;
                case 6:
                    subMenuConfiguracion();
                    break;
                case 7:
                    view.mostrarMensaje("Saliendo del programa...");
                    break;
                default:
                    view.mostrarMensaje("Opción no válida.");
            }
        } while (opcion != 7);
    }

    private void subMenuConfiguracion() {
        view.mostrarSubMenuAlmacenamiento();
        int opcion = obtenerOpcion();

        switch (opcion) {
            case 1:
                tipoAlmacenamiento = "texto";
                break;
            case 2:
                tipoAlmacenamiento = "binario";
                break;
            case 3:
                tipoAlmacenamiento = "objetos";
                break;
            case 4:
                tipoAlmacenamiento = "accesoAleatorio";
                break;
            case 5:
                tipoAlmacenamiento = "xml";
                break;
            default:
                view.mostrarMensaje("Opción no válida.");
                return;
        }
        jugadores.clear();
        view.mostrarMensaje("Tipo de almacenamiento cambiado a: " + tipoAlmacenamiento);
        leeFicheros.cargarJugadores(tipoAlmacenamiento);
        jugadores = leeFicheros.getJugadores();
    }

    public void agregarJugador() {
        JugadorModelo jugador = obtenerDatosJugador();
        jugadores.add(jugador);
//        GUARDADO.guardarJugador(jugador, tipoAlmacenamiento);
        guardarJugadores();
//        GUARDADO2.guardarJugador(tipoAlmacenamiento);
//        view.mostrarMensaje(GUARDADO2.getMensajeGuardado());
    }

    private void listarJugadores() {
        StringBuilder listado = new StringBuilder();
        for (JugadorModelo jugador : jugadores) {
            listado.append(jugador.toString()).append("\n");
        }
        view.mostrarJugadores(listado.toString());
    }

    private void listarJugadoresID() {
        view.mostrarMensaje("Ingrese el ID del jugador:");
        int id = obtenerOpcion();
        JugadorModelo jugador = buscarJugadorPorId(id);

        if (jugador != null) {
            view.mostrarMensaje("Jugador encontrado: " + jugador);
        } else {
            view.mostrarMensaje("Jugador no encontrado.");
        }
    }

    private void modificarJugador() {
        view.mostrarMensaje("Ingrese el ID del jugador a modificar:");
        int id = obtenerOpcion();
        JugadorModelo jugador = buscarJugadorPorId(id);

        if (jugador != null) {
            view.mostrarMensaje("Jugador encontrado: " + jugador);
            JugadorModelo datosModificados = obtenerDatosJugador();

            // Actualizar los campos
            jugador.setNick_name(datosModificados.getNick_name());
            jugador.setExperience(datosModificados.getExperience());
            jugador.setLife_level(datosModificados.getLife_level());
            jugador.setCoins(datosModificados.getCoins());
            view.mostrarMensaje("Jugador modificado con éxito.");

            guardarJugadores(); // Guarda todos los jugadores después de modificar
        } else {
            view.mostrarMensaje("Jugador no encontrado.");
        }
    }

    private void eliminarJugador() {
        view.mostrarMensaje("Ingrese el ID del jugador a eliminar:");
        int id = obtenerOpcion();
        JugadorModelo jugador = buscarJugadorPorId(id);

        if (jugador != null) {
            jugadores.remove(jugador);
            view.mostrarMensaje("Jugador eliminado con éxito.");
            guardarJugadores(); // Guarda todos los jugadores después de eliminar
        } else {
            view.mostrarMensaje("Jugador no encontrado.");
        }
    }

    private JugadorModelo buscarJugadorPorId(int id) {
        for (JugadorModelo jugador : jugadores) {
            if (jugador.getId() == id) {
//                System.out.println("Ha Eliminado" + "\n" + jugador.toString());
                return jugador;
            }
        }
        return null;
    }

    private void guardarJugadores() {
//        for (JugadorModelo jugador : jugadores) {
//            GUARDADO.guardarJugador(jugador, tipoAlmacenamiento);
            GUARDADO2.guardarJugador(jugadores,tipoAlmacenamiento);
//        }
        view.mostrarMensaje(GUARDADO2.getMensajeGuardado());
    }

    public JugadorModelo obtenerDatosJugador() {
        JugadorModelo jugador = new JugadorModelo();

        if (!jugadores.isEmpty()) {
            int ultimoId = jugadores.get(jugadores.size() - 1).getId();
            jugador.setId(ultimoId + 1);
        } else {
            jugador.setId(0);
        }
        sc.nextLine();  // Limpiar buffer
        view.mostrarMensaje("Ingrese nick_name: ");
        jugador.setNick_name(sc.nextLine());

        view.mostrarMensaje("Ingrese experiencia: ");
        jugador.setExperience(validarExperienciaVida(sc));
        sc.nextLine();

        view.mostrarMensaje("Ingrese nivel de vida: ");
        jugador.setLife_level(validarExperienciaVida(sc));
        sc.nextLine();

        view.mostrarMensaje("Ingrese monedas: ");
        jugador.setCoins(validarMonedas(sc));
        sc.nextLine();

        return jugador;
    }

    private int validarExperienciaVida(Scanner scanner) {
        int n;
        do {
            System.out.print("Introduce un número válido (de 0 a 100): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, introduce un número válido.");
                scanner.next();
            }
            n = scanner.nextInt();
        } while (n < 0 || n > 100);
        return n;
    }

    private int validarMonedas(Scanner scanner) {
        int n;
        do {
            System.out.print("Introduce un número de monedas válido (de 0 a 100.000): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, introduce un número válido.");
                scanner.next();
            }
            n = scanner.nextInt();
        } while (n < 0 || n > 100000);
        return n;
    }

    public int obtenerOpcion() {
        //return sc.nextInt();
        int n;

        while (!sc.hasNextInt()) {
            System.out.println("Por favor, introduce un número válido.");
            sc.next();
        }
        return n = sc.nextInt();
    }
}
