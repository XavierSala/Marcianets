/**
 *
 */
package net.xaviersala.marcianets;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import acm.graphics.GImage;

/**
 * @author xavier
 *
 */
public class NauEnemigaKamikazeTest {

    /**
     * Un valor gran per fer coses que depenen de l'aleatorietat.
     */
    private static final int NUMEROGRAN = 20000;
    /**
     * La nau.
     */
    private NauEnemigaKamikaze kami;
    /**
     * La imatge.
     */
    private GImage imatge;
    /**
     * @throws java.lang.Exception
     */
    @Before
    public final void setUp() throws Exception {
        imatge = new GImage("");
        kami = new NauEnemigaKamikaze(imatge.getImage(), 100, 100);
        kami.setLiniaBase(0);
    }

    /**
     * Test method gira.
     *
     * Com que la nau no controla les dimensions de la pantalla no puc
     * fer més que comprovar que de tant en tant decideix baixar
     */
    @Test(timeout = NUMEROGRAN)
    public final void testGira() {
        Direccio dir  = kami.getDireccio();

        while (kami.getDireccio() != Direccio.AVALL) {
            assertTrue(kami.getDireccio() == dir);
            kami.mou();
        }

        kami.gira();

        assertTrue(kami.getDireccio() == Direccio.AMUNT);

        while (kami.getDireccio() == Direccio.AMUNT) {
            kami.mou();
            if (kami.getLiniaBase() <= 0) {
                kami.gira();
            }
        }

        assertTrue(kami.getDireccio() == Direccio.ESQUERRA);
    }

    /**
     * Mai ha de disparar.
     */
    @Test
    public final void testDispara() {

        for (int i = 0; i < NUMEROGRAN; i++) {
           assertNull(kami.comprovaSiDispara());
        }
    }
}
