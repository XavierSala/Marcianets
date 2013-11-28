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
    public boolean tocat() {
         setVelocitat(0);
         setMort(true);
         return true;
    }

    /**
     * Mou l'altre objecte fins que no toca a aquest.
     * @param b bala
     */
    protected final void separaObjecteFinsQueNoXoqui(final CosaMobil b) {
        while (b.getRectanglePosicio().intersects(getRectanglePosicio())) {
                b.mou();
        }
    }

    /**
     * La nau dispara si té bales disponibles.
     *
     * Per evitar que la bala xoqui amb la nau que l'ha fet moc la
     * bala fins que deixa de tocar.
     * @return retorna la bala creada
     */
    public Bala dispara() {

        Bala b = (Bala) ObjectesFactory.build(TipusNau.BALA, getEsquerra(),
                getDalt(), Direccio.AMUNT);
        separaObjecteFinsQueNoXoqui((CosaMobil) b);

        return b;
    }

    /**
     * La nau es mou.
     */
    public void mou() {
        super.mou();
    }

}
