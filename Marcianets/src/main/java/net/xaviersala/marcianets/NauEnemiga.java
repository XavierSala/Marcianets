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
     * Cap on es mou si arriba al racó.
     */
    private double moviment;

    /**
     * Velocitat de la nau.
     */
    public static final int VELOCITATNAU = 4;
    /**
     * Crea una nau enemiga.
     * @param img imatge
     * @param x coordenada
     * @param y coordenada
     */
    public NauEnemiga(final Image img, final double x, final double y) {
        super(img, x, y);
        setDireccio(Direccio.DRETA);
        setVelocitat(VELOCITATNAU);
        moviment = 1;
    }

    /**
     * La nau dispara cap avall.
     */
    public final void dispara() {
        Bala b = armari.addBala("bala.jpg",  getEsquerra(), getDalt(),
                Direccio.AVALL);
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
        Direccio novaDireccio;
        if (getDireccio() == Direccio.ESQUERRA.getValor()) {
            novaDireccio = Direccio.DRETA;
        } else {
            novaDireccio = Direccio.ESQUERRA;
        }
        setDireccio(novaDireccio);
       // setDireccio((getDireccio() + Direccions.ESQUERRA) % VOLTA);
        mouA(0, getAltura() * moviment);
        moviment *= -1;
    }

    /**
     * @return Si ha sortit de la pantalla.
     */
    private boolean isForaDePantalla() {
        return getImatge().getX() < 0
                || getDreta() > armari.getPantallaWidth();
    }
}
