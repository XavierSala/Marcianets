package net.xaviersala.marcianets;

import java.awt.Image;

/**
 * Construeix una bala amiga.
 * @author xavier
 *
 */
public class BalaAmiga extends Bala {

    /**
     *  Crea una Bala amiga (va cap a dalt).
     * @param img Imatge
     * @param x posició x
     * @param y posició y
     */
    public BalaAmiga(final Image img, final double x, final double y) {
        super(img, x, y, Direccio.AMUNT);
    }

}
