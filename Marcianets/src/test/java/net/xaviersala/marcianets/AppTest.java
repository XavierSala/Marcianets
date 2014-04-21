package net.xaviersala.marcianets;

import static org.junit.Assert.*;

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

    private static final int TOTALBALES = 6;
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
        fail("Not yet implemented");
    }

    /**
     * Comprovar que tenim escriptori.
     */
    @Test
    public final void testGetEscriptori() {
        assertSame(pantalla, app.getEscriptori());
    }

}
