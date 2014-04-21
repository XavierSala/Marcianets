package net.xaviersala.marcianets;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import acm.graphics.GImage;

/**
 * Comprovar el funcionament de les coses mòbils.
 *
 * @author xavier
 *
 */
public class NauAmigaTest {
    /**
     * Valor màxim usat en alguns tests.
     */
    private static final int MAX = 100;
    /**
     * Per definir la posició.
     */
    private static final int POSICIOX = 10;
    /**
     * Per definir la posició.
     */
    private static final int POSICIOY = 10;
    /**
     * Per fer un número de tests iguals.
     */
    private static final int NUMTESTS = 10;
    /**
     * Números aleatòris.
     */
    private Random r;
    /**
     * Imatge de prova.
     */
    private GImage imatge;

    /**
     * Crear una imatge i iniciar el random.
     *
     *
     */
    @Before
    public final void setUp()  {
        r = new Random();
        imatge = new GImage("nau.gif");
    }

    /**
     * Comprovar el moviment.
     *
     */
    @Test
    public final void testMoviment() {
        double posx = POSICIOX;
        double posy = POSICIOY;
        int velocitat = 2;

        NauAmiga c = new NauAmiga(imatge.getImage(), posx, posy);

        // Per defecte va a la dreta
        c.setDireccioIVelocitat(Direccio.DRETA, velocitat);
        c.mou();
        assertTrue(c.getDalt() == posy);
        assertTrue(c.getEsquerra() == posx + velocitat);
        assertTrue(c.getDreta() == c.getEsquerra() + c.getAmplada());

        posx = posx + velocitat;
        c.mou();
        assertTrue(c.getDalt() == posy);
        assertTrue(c.getEsquerra() == posx + velocitat);
        assertTrue(c.getDreta() == c.getEsquerra() + c.getAmplada());
        posx = posx + velocitat;

        // Per defecte va a la dreta
        c.setDireccioIVelocitat(Direccio.ESQUERRA, velocitat);
        c.mou();
        assertTrue(c.getDalt() == posy);
        assertTrue(c.getEsquerra() == posx - velocitat);
        assertTrue(c.getDreta() == c.getEsquerra() + c.getAmplada());
        posx = posx - velocitat;

        c.mou();
        assertTrue(c.getDalt() == posy);
        assertTrue(c.getEsquerra() == posx - velocitat);
        assertTrue(c.getDreta() == c.getEsquerra() + c.getAmplada());
        posx = posx - velocitat;

        c.mouUndo();
        assertTrue(c.getDalt() == posy);
        assertTrue(c.getEsquerra() == posx + velocitat);
        posx = posx + velocitat;
    }

    /**
     * Comprovar que realment va en la direcció correcta.
     */
    @Test
    public final void testDireccio() {
        NauAmiga c = new NauAmiga(imatge.getImage(), POSICIOX, POSICIOY);
        c.setDireccio(Direccio.DRETA);

        assertTrue(c.getDireccio() == Direccio.DRETA);

        c.setDireccio(Direccio.ESQUERRA);
        assertTrue(c.getDireccio() == Direccio.ESQUERRA);
    }

    /**
     * Comprova si la velocitat s'assigna bé.
     */
    @Test
    public final void testSetVelocitat() {
        NauAmiga c = new NauAmiga(imatge.getImage(), POSICIOX, POSICIOY);
        for (int i = 0; i < NUMTESTS; i++) {
            int valor = r.nextInt(MAX);
            c.setVelocitat(valor);
            assertTrue(c.getVelocitat() == valor);
        }

        for (int i = 0; i < NUMTESTS; i++) {
            int valor = r.nextInt(MAX);
            c.setDireccioIVelocitat(Direccio.DRETA, valor);
            assertTrue(c.getVelocitat() == valor);
        }
    }

    /**
     * Comprovar les bales.
     */
    @Test
    public final void testDispara() {
        int bales = NauAmiga.MAXIMBALES;
        NauAmiga c = new NauAmiga(imatge.getImage(), POSICIOX, POSICIOY);

        assertTrue(c.getNumBalesDisponibles() == bales);

        // No ha de canviar si el carregador està ple
        c.recarrega();
        assertTrue(c.getNumBalesDisponibles() == bales);

        // Dispara
        assertNotNull(c.dispara());
        bales--;
        assertTrue(c.getNumBalesDisponibles() == bales);

        c.recarrega();
        assertTrue(c.getNumBalesDisponibles() == bales + 1);
        bales++;

        while (c.dispara() != null) {
            bales--;
        }

        assertEquals("0", c.getBalesDisponibles());
    }
}
