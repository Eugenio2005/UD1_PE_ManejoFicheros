/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Modelo;

import java.util.List;
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
public class GuardarJugadores2IT {
    
    public GuardarJugadores2IT() {
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
     * Test of setMensajeGuardado method, of class GuardarJugadores2.
     */
    @org.junit.Test
    public void testSetMensajeGuardado() {
        System.out.println("setMensajeGuardado");
        String mensajeGuardado = "";
        GuardarJugadores2 instance = new GuardarJugadores2();
        instance.setMensajeGuardado(mensajeGuardado);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMensajeGuardado method, of class GuardarJugadores2.
     */
    @org.junit.Test
    public void testGetMensajeGuardado() {
        System.out.println("getMensajeGuardado");
        GuardarJugadores2 instance = new GuardarJugadores2();
        String expResult = "";
        String result = instance.getMensajeGuardado();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of guardarJugador method, of class GuardarJugadores2.
     */
    @org.junit.Test
    public void testGuardarJugador() {
        System.out.println("guardarJugador");
        List<JugadorModelo> jugadores = null;
        String tipoAlmacenamiento = "";
        GuardarJugadores2 instance = new GuardarJugadores2();
        instance.guardarJugador(jugadores, tipoAlmacenamiento);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
