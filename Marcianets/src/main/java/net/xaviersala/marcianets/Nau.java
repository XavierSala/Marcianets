package net.xaviersala.marcianets;

import java.awt.Image;

/**
 * Classe pensada per representar els objectes mòbils.
 *
 * @author xavier
 *
 */
public abstract class Nau extends CosaMobil {

    /**
     * Construeix una nau i  la posa a un lloc determinat.
     * @param img imatge
     * @param x posició x
     * @param y posició y
     */
    public Nau(final Image img, final double x, final double y) {
        super(img, x, y);
    }

    /**
     * Indica que han tocat la nau.
     * @return Han tocat la nau o no
     */
    @Override
    public boolean tocat() {
         setVelocitat(0);
        setMort();
        return true;
    }

    /**
     * La nau dispara si té bales disponibles.
     *
     * Per evitar que la bala xoqui amb la nau que l'ha fet moc la
     * bala fins que deixa de tocar.
     * @return retorna la bala creada
     */
    public abstract Bala dispara();


}
