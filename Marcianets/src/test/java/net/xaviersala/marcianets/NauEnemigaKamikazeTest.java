package net.xaviersala.marcianets;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import java.util.Random;

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
     * Per posicionar.
     */
    private static final int CENT = 100;
    /**
     * La nau.
     */
    private NauEnemigaKamikaze kami;
    /**
     * La imatge.
     */
    private GImage imatge;
    /**
     * Aleatòris
     */
    Random r;
    /**
     * @throws java.lang.Exception Problemes!
     */
    @Before
    public final void setUp() throws Exception {
        r = new Random();
        imatge = new GImage("");
        kami = new NauEnemigaKamikaze(imatge.getImage(), CENT, CENT);
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

        Direccio[] dir  = {Direccio.DRETA, Direccio.ESQUERRA};
        int i=0;

        assertTrue(kami.getDireccio() == dir[i]);
        kami.gira();
        i = i + 1;
        assertTrue(kami.getDireccio() == dir[i]);

        while (kami.getDireccio() != Direccio.AVALL) {
            assertTrue(kami.getDireccio() == dir[i]);
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
