/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
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
public class LeerFicherosIT {
    
    public LeerFicherosIT() {
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
     * Test of cargarJugadores method, of class LeerFicheros.
     */
    @org.junit.Test
    public void testCargarJugadores() {
        System.out.println("cargarJugadores");
        String tipoAlmacenamiento = "";
        LeerFicheros instance = new LeerFicheros();
        instance.cargarJugadores(tipoAlmacenamiento);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getJugadores method, of class LeerFicheros.
     */
    @org.junit.Test
    public void testGetJugadores() {
        System.out.println("getJugadores");
        LeerFicheros instance = new LeerFicheros();
        ArrayList<JugadorModelo> expResult = null;
        ArrayList<JugadorModelo> result = instance.getJugadores();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
