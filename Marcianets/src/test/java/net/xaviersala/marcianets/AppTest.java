package net.xaviersala.marcianets;

import static org.junit.Assert.*;

import java.awt.Component;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import acm.graphics.GImage;

/**
 * Comprovem les funcions més importants de l'objecte principal.
 *
 * @author xavier
 *
 */
public class AppTest {

    /**
     * Número de naus "normals" per cada fila..
     */
    private static final int NUMNAUS = 10;

    /**
     * Número de naus kamikaces.
     */
    private static final int KAMIKACES = 3;
    /**
     * Número total de bales.
     */
    private static final int TOTALBALES = NauAmiga.MAXIMBALES;
    /**
     * Dimensions de la pantalla.
     */
    private static final int AMPLE = 800;
    /**
     * Dimensions de la pantalla.
     */
    private static final int ALT = 600;
    /**
     * Imatge de la nau a carregar.
     */
    private static final String NAU_GIF = "nau.gif";

    private static final int TOTALPROVES = 10;

    /**
     * Objecte principal.
     */
    private App app;
    /**
     * Pantalla de joc.
     */
    private Pantalla pantalla;
    /**
     * Imatge per les proves.
     */
    private GImage imatge;
    /**
     * Iniciem tot plegat.
     *
     *
     */
    @Before
    public final void setUp()  {
        imatge = new GImage(NAU_GIF);
         app = new App();
         app.setBounds(0, 0, AMPLE, ALT);
         pantalla = new Pantalla(app);
         app.setEscriptori(pantalla);
    }

    /**
     * Comprovar que s'inicialitza bé.
     * - S'ha afegit el protagonista
     */
    @Test
    public final void testInicialitzarIDisparar() {
        app.inicialitzarJoc();

        assertNotNull(app.getEscriptori().getProtagonista());
        assertEquals("bales:6", app.getEscriptori().getMarcador());

        NauAmiga nau = app.getEscriptori().getProtagonista();

        for (int i = TOTALBALES; i > 0; i--) {
            assertEquals("bales:" + i, app.getEscriptori().getMarcador());
            app.protagonistaDispara(nau);
        }
    }

    /**
     * Comprovar que les naus enemigues es creen.
     */
    @Test
    public final void testAfegirNausEnemigues() {
        int totalNaus = NUMNAUS * 2 + KAMIKACES;
        app.afegirNausEnemigues();
        assertTrue(app.getEscriptori().addNau(
                TipusNau.NAUENEMIGANORMAL,
               0, 0) == totalNaus);
    }

    /**
     * Comprovar que tenim escriptori.
     */
    @Test
    public final void testGetEscriptori() {
        assertSame(pantalla, app.getEscriptori());
    }

    /**
     * Comprovar que el protagonista fa "coses".
     */
    @Test
    public final void testProtagonistaMoviment() {

        Random r = new Random();

        Pantalla p = app.getEscriptori();
        p.addProtagonista();
        Component jFrame = app.getComponent(0);

        KeyEvent teclaEsquerra = new KeyEvent(jFrame,
                KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_LEFT, 'Z');

        KeyEvent teclaDreta = new KeyEvent(jFrame,
                KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_RIGHT, ' ');

        for (int i = 0; i < TOTALPROVES; i++) {

            int quin = r.nextInt(2);
            if (quin == 0) {
                app.keyPressed(teclaEsquerra);
                assertTrue(p.getProtagonista().getDireccio()
                        == Direccio.ESQUERRA);
            } else {
                app.keyPressed(teclaDreta);
                assertTrue(p.getProtagonista().getDireccio()
                        == Direccio.DRETA);
            }
        }

        assertTrue(p.getProtagonista().getVelocitat() != 0);
        app.keyReleased(teclaDreta);
        assertTrue(p.getProtagonista().getVelocitat() == 0);
    }

    /**
     * Comprova si el protagonista dispara i si al acabar amb el
     * carregador deixa de fer-ho.
     */
    @Test
    public final void testProtagonistaDispara() {

        Pantalla p = app.getEscriptori();
        p.addProtagonista();
        Component jFrame = app.getComponent(0);

       KeyEvent teclaAmunt = new KeyEvent(jFrame,
               KeyEvent.KEY_PRESSED,
               System.currentTimeMillis(), 0,
               KeyEvent.VK_UP, ' ');

       KeyEvent teclaRecarregar = new KeyEvent(jFrame,
               KeyEvent.KEY_PRESSED,
               System.currentTimeMillis(), 0,
               KeyEvent.VK_R, 'R');

        for (int i = 0; i < NauAmiga.MAXIMBALES; i++) {
            int bales = p.getProtagonista().getNumBalesDisponibles();
            app.keyPressed(teclaAmunt);
            assertEquals(bales - 1,
                    p.getProtagonista().getNumBalesDisponibles());
        }

        int bales = p.getProtagonista().getNumBalesDisponibles();
        app.keyPressed(teclaAmunt);
        assertEquals(bales,
                p.getProtagonista().getNumBalesDisponibles());

        app.keyPressed(teclaAmunt);
        assertEquals(bales,
                p.getProtagonista().getNumBalesDisponibles());

        app.keyPressed(teclaRecarregar);
        assertEquals(bales + 1,
                p.getProtagonista().getNumBalesDisponibles());

        bales++;
        app.keyPressed(teclaRecarregar);
        assertEquals(bales + 1,
                p.getProtagonista().getNumBalesDisponibles());
    }

}
