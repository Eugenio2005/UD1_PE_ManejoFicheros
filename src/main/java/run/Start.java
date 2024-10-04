/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

