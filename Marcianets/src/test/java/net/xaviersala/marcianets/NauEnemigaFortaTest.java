package net.xaviersala.marcianets;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import acm.graphics.GImage;

/**
 * Nau enemiga que necessita tres tocs per morir.
 *
 * @author xavier
 *
 */
public class NauEnemigaFortaTest {

    /**
     * Número per posicionar la nau.
     */
    private static final int CENT = 100;

    /**
     * Comprovar que mor als tres tocs.
     */
    @Test
    public final void testTocat() {
        GImage imatge = new GImage("");
        NauEnemigaForta nau = new NauEnemigaForta(imatge.getImage(),
                CENT, CENT);

        assertTrue(!nau.isMort());

        assertTrue(!nau.tocat());
        assertTrue(!nau.isMort());

        assertTrue(!nau.tocat());
        assertTrue(!nau.isMort());

        assertTrue(nau.tocat());
        assertTrue(nau.isMort());
    }

}
