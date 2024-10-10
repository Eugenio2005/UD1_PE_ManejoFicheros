/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Controlador;

import Modelo.JugadorModelo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vespertino
 */
public class JugadorControladorIT {
    
    public JugadorControladorIT() {
    }

    @org.junit.BeforeClass
    public static void setUpClass() throws Exception {
    }

    @org.junit.AfterClass
    public static void tearDownClass() throws Exception {
    }

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }
    
    /**
     * Test of iniciar method, of class JugadorControlador.
     */
    @org.junit.Test
    public void testIniciar() {
        System.out.println("iniciar");
        JugadorControlador instance = null;
        instance.iniciar();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of MenuInicial method, of class JugadorControlador.
     */
    @org.junit.Test
    public void testMenuInicial() {
        System.out.println("MenuInicial");
        JugadorControlador instance = null;
        instance.MenuInicial();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of agregarJugador method, of class JugadorControlador.
     */
    @org.junit.Test
    public void testAgregarJugador() {
        System.out.println("agregarJugador");
        JugadorControlador instance = null;
        instance.agregarJugador();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerDatosJugador method, of class JugadorControlador.
     */
    @org.junit.Test
    public void testObtenerDatosJugador() {
        System.out.println("obtenerDatosJugador");
        JugadorControlador instance = null;
        JugadorModelo expResult = null;
        JugadorModelo result = instance.obtenerDatosJugador();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerOpcion method, of class JugadorControlador.
     */
    @org.junit.Test
    public void testObtenerOpcion() {
        System.out.println("obtenerOpcion");
        JugadorControlador instance = null;
        int expResult = 0;
        int result = instance.obtenerOpcion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
