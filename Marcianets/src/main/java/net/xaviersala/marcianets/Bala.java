package net.xaviersala.marcianets;

import java.awt.Image;

/**
 * Representa les bales que dispararan els CowBoys...
 *
 * @author xavier
 *
 */
public class Bala extends CosaMobil {

    /**
     * Velocitat a la que es mouen les bales.
     */
    public static final double VELOCITATBALA = 10;

    /**
     * Defineix la velocitat i la direcció de la bala.
     * @param capOn direcció en graus
     */
    private void inicialitza(final Direccio capOn) {
        setVelocitat(VELOCITATBALA);
        setDireccio(capOn);
    }


    /**
     * Construeix una bala a partir d'una imatge
     * i la posiciona en les coordenades que se li
     * especifiquen.
     *
     * @param img imatge a posar
     * @param x Coordenada x
     * @param y Coordenada y
     * @param capOn Direcció de la bala
     */
    public Bala(final Image img, final double x, final double y,
            final Direccio capOn) {
        super(img, x, y);
        inicialitza(capOn);
    }


    @Override
    public final boolean tocat() {
        return false;
    }
}
