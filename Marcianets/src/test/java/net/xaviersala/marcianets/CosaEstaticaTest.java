package net.xaviersala.marcianets;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import acm.graphics.GImage;
import acm.graphics.GRectangle;

/**
 * Comprovem la classe de generació d'objectes estàtics.
 *
 * @author xavier
 *
 */
public class CosaEstaticaTest {

    /**
     * Valor numèric per definir la pantalla.
     */
    private static final int MAX = 800;
    /**
     * Número de repeticions del test.
     */
    private static final int REPETICIONS = 10;


    /**
     * Objecte sota test.
     */
    private CosaEstatica cosa;
    /**
     * Imatge a carregar per generar l'objecte.
     */
    private GImage imatge;
    /**
     * Generador de números aleatòris.
     */
    private Random r;

    /**
     * Crear la imatge inicial i inicia el generador de números aleatòris.
     * @throws Exception Alguna cosa ha fallat
     */
    @Before
    public final void setUp() throws Exception {
        r = new Random();
        imatge = new GImage("nau.gif");
    }

    /**
     * Comprovo que el tipus que torna és el correcte.
     */
    @Test
    public final void testTipus() {
        cosa = new CosaEstatica(imatge.getImage());
        assertEquals("Estatica", cosa.getTipus());
    }


    /**
     * Comprovo les funcions que fan referència al posicionament dels
     * objectes estàtics.
     */
  @Test
    public final void testPosicio() {
       GRectangle rectangle = new GRectangle(0, 0,
               imatge.getWidth(), imatge.getHeight());
        cosa = new CosaEstatica(imatge.getImage());
        assertTrue(cosa.getEsquerra() == 0);
        assertTrue(cosa.getDalt() == 0);
        assertTrue(cosa.getDreta() == imatge.getWidth());
        assertTrue(cosa.getBaix() == imatge.getHeight());
        assertEquals(rectangle, cosa.getRectanglePosicio());

        for (int i = 0; i < REPETICIONS; i++) {
            double pos1 = r.nextInt(MAX);
            double pos2 = r.nextInt(MAX);
            rectangle = new GRectangle(pos1, pos2,
                    imatge.getWidth(), imatge.getHeight());

            cosa = new CosaEstatica(imatge.getImage(), pos1, pos2);
            assertTrue(cosa.getEsquerra() == pos1);
            assertTrue(cosa.getDalt() == pos2);
            assertEquals(rectangle, cosa.getRectanglePosicio());
        }
    }

}
