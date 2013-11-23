package net.xaviersala.marcianets;

import java.awt.Image;

/**
 * Nau enemiga base.
 *
 * @author xavier
 *
 */
public class NauEnemiga extends Nau {
    /**
     * Armari d'imatges.
     */
    private Armari armari = Armari.getInstance();
    /**
     * Cap on es mou si arriba al racó.
     */
    private double moviment;
    /**
     * Direcció de les bales.
     */
    private static final int DIRECCIOBALES = 270;
    /**
     * Volta sencera.
     */
    private static final int VOLTA  = 360;
    /**
     * Mitja volta.
     */
    private static final int MITJAVOLTA = 180;
    /**
     * Velocitat de la nau.
     */
    private static final int VELOCITATNAU = 4;
    /**
     * Crea una nau enemiga.
     * @param img imatge
     * @param x coordenada
     * @param y coordenada
     */
    public NauEnemiga(final Image img, final double x, final double y) {
        super(img, x, y);
        setDireccio(0);
        setVelocitat(VELOCITATNAU);
        moviment = 1;
    }

    /**
     * La nau dispara cap avall.
     */
    public final void dispara() {
        Bala b = armari.addBala("bala.jpg",  getEsquerra(), getDalt(),
                DIRECCIOBALES);
        treuBalaDeLaNau((Cosa) this, b);
    }

    /**
     * Moure la nau cap els costats.
     */
    public void mou() {
        super.mou();

        if (isForaDePantalla()) {
            gira();
        }
     }

    /**
     * Gira cap a l'altre costat.
     */
    private void gira() {
        setDireccio((getDireccio() + MITJAVOLTA) % VOLTA);
        mouA(0, getAltura() * moviment);
        moviment *= -1;
    }

    /**
     * @return Si ha sortit de la pantalla.
     */
    private boolean isForaDePantalla() {
        return getImatge().getX() < 0
                || getDreta() > armari.getPantalla().getWidth();
    }
}
