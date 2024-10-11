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
    private final GuardarJugadores GUARDADO = new GuardarJugadores();

    /**
     * Constructor de la clase JugadorControlador.
     *
     * @param view La vista que mostrará la información al usuario.
     */
    public JugadorControlador(JugadorVista view) {
        this.view = view;
        this.jugadores = new ArrayList<>();
    }

    /**
     * Método que inicia el proceso de interacción del usuario con el sistema.
     * Carga los jugadores desde el tipo de almacenamiento seleccionado.
     */
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

    /**
     * Método que muestra el menú principal y permite la interacción del usuario
     * con las diferentes opciones de CRUD sobre los jugadores.
     */
    public void MenuInicial() {
        int opcion;
        do {
            view.mostrarMenuPrincipal();
            opcion = obtenerOpcion();
            view.limpiarConsola();
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

    /**
     * Muestra el submenú de configuración que permite al usuario cambiar el
     * tipo de almacenamiento.
     */
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

    /**
     * Método que permite agregar un nuevo jugador al sistema.
     */
    public void agregarJugador() {
        JugadorModelo jugador = obtenerDatosJugador();
        jugadores.add(jugador);
        guardarJugadores();
    }

    /**
     * Muestra una lista de todos los jugadores.
     */
    private void listarJugadores() {
        StringBuilder listado = new StringBuilder();
        for (JugadorModelo jugador : jugadores) {
            listado.append(jugador.toString()).append("\n");
        }
        view.mostrarJugadores(listado.toString());
    }

    /**
     * Busca y muestra un jugador por su ID.
     */
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

    /**
     * Modifica los datos de un jugador existente.
     */
    private void modificarJugador() {
        view.mostrarMensaje("Ingrese el ID del jugador a modificar:");
        int id = obtenerOpcion();
        JugadorModelo jugador = buscarJugadorPorId(id);

        if (jugador != null) {
            view.mostrarMensaje("Jugador encontrado: " + jugador);
            JugadorModelo datosModificados = obtenerDatosJugador();

            // Actualizar los campos
            jugador.setNick_name(datosModificados.getNick_name());
            jugador.setExperiencia(datosModificados.getExperiencia());
            jugador.setNivel_vida(datosModificados.getNivel_vida());
            jugador.setMonedas(datosModificados.getMonedas());
            view.mostrarMensaje("Jugador modificado con éxito.");

            guardarJugadores(); // Guarda todos los jugadores después de modificar
        } else {
            view.mostrarMensaje("Jugador no encontrado.");
        }
    }

    /**
     * Elimina un jugador del sistema.
     */
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

    /**
     * Busca un jugador por su ID.
     *
     * @param id El ID del jugador.
     * @return El jugador encontrado o null si no existe.
     */
    private JugadorModelo buscarJugadorPorId(int id) {
        for (JugadorModelo jugador : jugadores) {
            if (jugador.getId() == id) {
//                System.out.println("Ha Eliminado" + "\n" + jugador.toString());
                return jugador;
            }
        }
        return null;
    }

    /**
     * Guarda todos los jugadores en el tipo de almacenamiento seleccionado.
     */
    private void guardarJugadores() {
//        for (JugadorModelo jugador : jugadores) {
//            GUARDADO.guardarJugador(jugador, tipoAlmacenamiento);
        GUARDADO.guardarJugador(jugadores, tipoAlmacenamiento);
//        }
        view.mostrarMensaje(GUARDADO.getMensajeGuardado());
    }

    /**
     * Obtiene los datos de un nuevo jugador desde la entrada del usuario.
     *
     * @return Un nuevo objeto JugadorModelo con los datos proporcionados.
     */
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
        jugador.setExperiencia(validarExperienciaVida(sc));
        sc.nextLine();

        view.mostrarMensaje("Ingrese nivel de vida: ");
        jugador.setNivel_vida(validarExperienciaVida(sc));
        sc.nextLine();

        view.mostrarMensaje("Ingrese monedas: ");
        jugador.setMonedas(validarMonedas(sc));
        sc.nextLine();

        return jugador;
    }

    /**
     * Valida la experiencia o el nivel de vida ingresado.
     *
     * @param scanner El objeto Scanner para la entrada del usuario.
     * @return Un valor entero entre 0 y 100.
     */
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

    /**
     * Valida la cantidad de monedas ingresada.
     *
     * @param scanner El objeto Scanner para la entrada del usuario.
     * @return Un valor entero entre 0 y 100.000.
     */
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

    /**
     * Obtiene la opción ingresada por el usuario.
     *
     * @return Un entero que representa la opción seleccionada por el usuario.
     */
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
