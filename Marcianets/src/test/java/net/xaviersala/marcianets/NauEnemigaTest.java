package net.xaviersala.marcianets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import acm.graphics.GImage;
import acm.graphics.GRectangle;

/**
 * Defineix el test de les naus enemigues.
 *
 * @author xavier
 *
 */
public class NauEnemigaTest {

    /**
     * Valor numèric per aleatoritzar.
     */
    private static final int DEUMIL = 10000;
    /**
     * Número per fer passades aleatòries.
     */
    private static final int PASSADES = 100;
    /**
     * Imatge.
     */
    private GImage imatgeNau;
    /**
     * Objecte que provarem.
     */
    private NauEnemiga enemiga;
    /**
     * Generador de números aleatòris.
     */
    private Random r;


    /**
     * Carrega la imatge.
     *
     */
    @Before
    public final void setUp() {


         r = new Random();
        imatgeNau = new GImage("nau.gif");
        enemiga = new NauEnemiga(imatgeNau.getImage(),
                r.nextInt(PASSADES),
                r.nextInt(PASSADES));

    }

    /**
     * Comrpova si gira.
     */
    /**
     * Comprova si dispara en la direcció correcta.
     *
     * Es mogui cap allà on sigui la nau enemiga sempre ha de disparar avall...
     *
     */
    @Test
    public final void testGira() {

        Direccio dreta = Direccio.DRETA;
        Direccio esquerra = Direccio.ESQUERRA;

        assertEquals(dreta , enemiga.getDireccio());

        enemiga.gira();
        assertEquals(esquerra , enemiga.getDireccio());

        enemiga.gira();
        assertEquals(dreta , enemiga.getDireccio());

        enemiga.gira();
        assertEquals(esquerra , enemiga.getDireccio());


    }

    /**
     * Comprovem si la nau gira o no ho fa...
     */
    @Test
    public final void testMou() {

        GRectangle rect = enemiga.getRectanglePosicio();
        int velocitat = NauEnemiga.VELOCITATNAU;
        // Va la dreta
        rect.setLocation(rect.getX() + velocitat, rect.getY());
        enemiga.mou();
        assertEquals(rect, enemiga.getRectanglePosicio());
        rect.setLocation(rect.getX() + velocitat, rect.getY());
        enemiga.mou();
        assertEquals(rect, enemiga.getRectanglePosicio());

        int bucle = r.nextInt(PASSADES);
        rect.setLocation(rect.getX() + (bucle * velocitat), rect.getY());
        mouNVegades(bucle);
        assertEquals(rect, enemiga.getRectanglePosicio());

        // Comprova si va a l'esquerra
        enemiga.gira();

        rect.setLocation(rect.getX() + (bucle * -1 * velocitat), rect.getY());
        mouNVegades(bucle);
        assertEquals(rect, enemiga.getRectanglePosicio());

    }

    /**
     * @param bucle vegades que es mou la nau
     */
    private void mouNVegades(final int bucle) {
        for (int i = 0; i < bucle; i++) {
            enemiga.mou();
        }
    }

    /**
     * Comprova si dispara en la direcció correcta.
     *
     * Es mogui cap allà on sigui la nau enemiga sempre ha de disparar avall...
     *
     */
    @Test
    public final void testDispara() {

        Direccio direccioEsperada = Direccio.AVALL;
        Bala b = enemiga.dispara();
        assertEquals(direccioEsperada , b.getDireccio());

        mouNVegades(r.nextInt(PASSADES));
        b = enemiga.dispara();
        assertEquals(direccioEsperada , b.getDireccio());

        enemiga.gira();
        b = enemiga.dispara();
        assertEquals(direccioEsperada , b.getDireccio());

        mouNVegades(r.nextInt(PASSADES));
        b = enemiga.dispara();
        assertEquals(direccioEsperada , b.getDireccio());

        int suma = 0;
        for (int i = 0; i < DEUMIL; i++) {
            if (enemiga.comprovaSiDispara() != null) {
                suma++;
            }
        }
        assertTrue(suma > 0);


    }

}
