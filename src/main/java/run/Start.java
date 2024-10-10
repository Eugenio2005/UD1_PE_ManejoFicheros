
package run;

import Controlador.JugadorControlador;
import Vista.JugadorVista;

public class Start {
    public static void main(String[] args) {
        JugadorVista vista = new JugadorVista();
        JugadorControlador controlador = new JugadorControlador(vista);
        controlador.iniciar();
    }
}