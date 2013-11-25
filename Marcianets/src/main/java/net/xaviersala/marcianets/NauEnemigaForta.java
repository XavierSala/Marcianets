package net.xaviersala.marcianets;

import java.awt.Image;
/**
 * Defineix una nau que necessita dos tocs per ser destruïda.
 * @author xavier
 *
 */
public class NauEnemigaForta extends NauEnemiga {
    /**
     * Número de tocs.
     */
    private static final int NUMTOCS = 3;

    /**
     * Tocs que queden per ser destruït.
     */
    private int tocs;

    /**
     * Construeix una nau d'aquest tipus.
     * @param img Imatge
     * @param x posició x
     * @param y posició y
     */
    public NauEnemigaForta(final Image img, final double x, final double y) {
        super(img, x, y);
        tocs = NUMTOCS;
    }

    /**
     * Fa que calguin dos tocs per matar aquesta nau.
     * @return Si s'ha de destruïr o no
     */
       @Override
        public final boolean tocat() {
           tocs--;
           if (tocs > 0) {
               setImatge(armari.getImatge("enemic2b.gif"));
               return false;
           }
         return super.tocat();
        }

}
