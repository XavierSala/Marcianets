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
     * Construeix una nau a partir de la seva imatge.
     * @param img imatge.
     */
    public Nau(final Image img) {
        super(img);
        inicialitza();
    }

    /**
     * Construeix una nau i  la posa a un lloc determinat.
     * @param img imatge
     * @param x posició x
     * @param y posició y
     */
    public Nau(final Image img, final double x, final double y) {
        super(img, x, y);
        inicialitza();
    }

    /**
     * Inicialitza el tipus d'objecte (crec que no serveix per res).
     */
    private void inicialitza() {
        setTipus("Nau");

    }

    /**
     * Indica que han tocat la nau.
     * @return Han tocat la nau o no
     */
    @Override
    public final boolean tocat() {
         setVelocitat(0);
         getImatge().setVisible(false);
         setMort(true);
         return true;
    }

    /**
     * Les naus normalment han de disparar.
     */
    public abstract void dispara();

    /**
     * Mou la bala fins que no mata al que ha disparat.
     * @param c cosa que dispara
     * @param b bala
     */
    protected final void treuBalaDeLaNau(final Cosa c, final Bala b) {
        while (b.getRectanglePosicio().intersects(c.getRectanglePosicio())) {
                b.mou();
        }
    }

    /**
     * La nau es mou.
     */
    public void mou() {
        super.mou();
    }

}
