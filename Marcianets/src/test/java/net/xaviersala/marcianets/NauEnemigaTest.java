package net.xaviersala.marcianets;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


import org.junit.Before;
import org.junit.Test;

import acm.graphics.GImage;

public class NauEnemigaTest {

    GImage imatgeNau;
    // Armari armari = Armari.getInstance();

    /**
     * Carrega la imatge.
     * @throws Exception La imatge no hi és
     */
    @Before
    public final void setUp() throws Exception {

        Armari armari = mock(Armari.class);
        when(armari.getPantallaWidth()).thenReturn(800d);

        imatgeNau = new GImage("nau.gif");
        // armari.setPantalla(ref);
    }

    /**
     * Comprovem si la nau gira o no ho fa...
     */
    @Test
    public final void testMou() {
        NauEnemiga n = new NauEnemiga(imatgeNau.getImage(), 180, 0);
        assertTrue(n.getDireccio() == 0);
        n.mou();
        assertTrue(n.getDireccio() == 180);

    }

    /**
     * Comprova si dispara en la direcció correcta
     */
    @Test
    public void testDispara() {

        fail("Not implemented");
    }

}
