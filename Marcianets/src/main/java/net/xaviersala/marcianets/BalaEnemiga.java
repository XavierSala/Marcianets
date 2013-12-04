package net.xaviersala.marcianets;

import java.awt.Image;
/**
 * Representa una bala enemiga. No mata els seus
 * @author xavier
 *
 */
public class BalaEnemiga extends Bala {

    /**
     * Construeix la bala enemiga.
     * @param img imatge
     * @param x posició x
     * @param y posició y
     */
    public BalaEnemiga(final Image img, final double x, final double y) {
        super(img, x, y, Direccio.AVALL);
        // TODO Auto-generated constructor stub
    }

}
