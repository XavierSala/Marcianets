package net.xaviersala.marcianets;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import acm.graphics.GImage;

/**
 * Comprovar el funcionament de la classe genèrica Pantalla.
 *
 * @author xavier
 *
 */
public class PantallaTest {

    /**
     * Imatge de la nau a carregar.
     */
    private static final String NAU_GIF = "nau.gif";

    /**
     * Altura de la pantalla.
     */
    private static final int ALT = 600;
    /**
     * Amplada de la pantalla.
     */
    private static final int AMPLE = 800;
    /**
     * Pantalla.
     */
    private Pantalla pantalla;
    /**
     * Classe principal.
     */
    private App app;
    /**
     * Imatge per les proves.
     */
    private GImage imatge;
    /**
     * Provar la pantalla.
     *
     * @throws Exception fallada...
     */
    @Before
    public final void setUp() throws Exception {
        imatge = new GImage(NAU_GIF);
        app = new App();
        app.setBounds(0, 0, AMPLE, ALT);
        pantalla = new Pantalla(app);
        app.setEscriptori(pantalla);
    }

    /**
     * Comprova les dimensions de la pantalla.
     */
    @Test
    public final void testPantalla() {
        assertTrue(pantalla.getPantallaWidth() == AMPLE);
        assertTrue(pantalla.getPantallaHeight() == ALT);

        assertEquals(pantalla.getPantalla(), app);

    }

    /**
     * Comprova el funcionament de partida acabada.
     *
     * En general:
     *  - Si no hi ha naus la partida s'ha acabat
     *  - Si només hi ha el protagonista la partida s'ha acabat
     *  - Si només hi ha naus enemigues la partida s'ha acabat
     *  - Si hi ha naus enemigues i protagonista la partida NO s'ha acabat
     */
    @Test
    public final void testPartidaAcabada() {

        assertTrue(!pantalla.noPartidaAcabada());
        pantalla.addProtagonista();
        assertTrue(!pantalla.noPartidaAcabada());
        pantalla.addNau(TipusNau.NAUENEMIGANORMAL,
                0, 0);
        assertTrue(pantalla.noPartidaAcabada());

        // Mato el protagonista
        pantalla.getProtagonista().tocat();
        assertTrue(!pantalla.noPartidaAcabada());

        // Per moltes naus que hi hagi si no hi ha protagonista...
        app.afegirNausEnemigues();
        assertTrue(!pantalla.noPartidaAcabada());

        // Partida no acabada si tenim "prota"
        pantalla.addProtagonista();
        assertTrue(pantalla.noPartidaAcabada());

    }

    /**
     * Comprova el funcionament del Marcador.
     *
     **/
    @Test
    public final void testMarcador() {
        pantalla.creaMarcador();
        assertEquals(pantalla.getMarcador(), "bales: 0");
        pantalla.canviaMarcador();
        assertEquals(pantalla.getMarcador(), "bales: 0");
        pantalla.addProtagonista();
        pantalla.canviaMarcador();
        assertEquals("bales:"
                + pantalla.getProtagonista().getBalesDisponibles(),
                pantalla.getMarcador());

    }

    /**
     * Comprovar la gestió de les naus. Que es van afegint per ordre...
     */
    @Test
    public final void testNaus() {
        assertTrue(pantalla.addNau(TipusNau.NAUENEMIGANORMAL,
                0, 0) == 0);
        assertTrue(pantalla.addNau(TipusNau.NAUENEMIGAFORTA, 0, 0) == 1);
        pantalla.addProtagonista();
        assertTrue(pantalla.addNau(TipusNau.NAUENEMIGAFORTA, 0, 0) == 3);



    }

    /**
     * Comprovar la gestió de les bales.
     */
    @Test
    public final void testBales() {
        pantalla.addBala(new BalaAmiga(imatge.getImage(), 50, 50));

    }

}
