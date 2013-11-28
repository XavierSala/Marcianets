package net.xaviersala.marcianets;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


import org.junit.Before;
import org.junit.Test;

import acm.graphics.GImage;

/**
 * Defineix el test de les naus enemigues.
 *
 * @author xavier
 *
 */
public class NauEnemigaTest {

    GImage imatgeNau;
    // Armari armari = Armari.getInstance();

    /**
     * Carrega la imatge.
     * @throws Exception La imatge no hi és
     */
    @Before
    public final void setUp() throws Exception {

//        Armari armari = mock(Armari.class);
//        when(armari.getPantallaWidth()).thenReturn(800d);

        imatgeNau = new GImage("nau.gif");
        // armari.setPantalla(ref);
    }

    /**
     * Comprovem si la nau gira o no ho fa...
     */
    @Test
    public final void testMou() {

    }

    /**
     * Comprova si dispara en la direcció correcta.
     */
    @Test
    public void testDispara() {

        fail("Not implemented");
    }

}
