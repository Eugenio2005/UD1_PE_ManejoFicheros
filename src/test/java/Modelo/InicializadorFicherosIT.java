/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Modelo;

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
public class InicializadorFicherosIT {
    
    public InicializadorFicherosIT() {
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
     * Test of inicializarFicheros method, of class InicializadorFicheros.
     */
    @org.junit.Test
    public void testInicializarFicheros() {
        System.out.println("inicializarFicheros");
        InicializadorFicheros.inicializarFicheros();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRuta_Guardados method, of class InicializadorFicheros.
     */
    @org.junit.Test
    public void testGetRuta_Guardados() {
        System.out.println("getRuta_Guardados");
        InicializadorFicheros instance = new InicializadorFicheros();
        String expResult = "";
        String result = instance.getRuta_Guardados();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
