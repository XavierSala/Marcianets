package net.xaviersala.marcianets;


import java.awt.Image;

/**
 * Cosa estàtica servirà per definir objectes que no es mouen de la pantalla.
 *
 * @author xavier
 */
public class CosaEstatica extends Cosa {

    /**
     * Construeix una cosa estàtica a partir del nom del fitxer que conté la
     * imatge que farà servir.
     *
     * @param fitxer fitxer JPG o GIF que conté la imatge
     */
    public CosaEstatica(final String fitxer) {
        super(fitxer);
    }

    /**
     * Construeix un personatge a partir de la imatge.
     *
     * @param img nom del fitxer
     */
    public CosaEstatica(final Image img) {
        super(img);
    }

    /**
     * Construeix una cosa estàtica a partir d'una imatge
     * i la posiciona en les coordenades que se li
     * especifiquen.
     *
     * @param img imatge a posar
     * @param x   Coordenada x
     * @param y   Coordenada y
     */
    public CosaEstatica(final Image img, final double x, final double y) {
        super(img, x, y);
    }

    @Override
    public final boolean tocat() {
        return false;
    }
}
