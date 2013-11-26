package net.xaviersala.marcianets;

import java.awt.Image;

/**
 * La nau enemiga kamikaze es llança cap avall per destruir la nau principal.
 *
 * @author xavier
 *
 */
public class NauEnemigaKamikaze extends NauEnemiga {
    /**
     * % de provabilitat de caure.
     */
    private static final double PROVABILITATATACAR = 5;
    /**
     * Mil.
     */
    private static final double MIL = 1000;

    /**
     * Posició on està la nau abans d'atacar.
     */
    private double liniaBase;

    /**
     * Crea una nau kamikaze.
     * @param img Imatge de la nau
     * @param x posició X
     * @param y posició Y
     */
    public NauEnemigaKamikaze(final Image img,
            final double x, final double y) {
        super(img, x, y);
        liniaBase = y;
    }

    /**
     * El moviment de la nau és l'habitual a menys que toqui que es llanci
     * cap avall.
     */
    public final void mou() {
        super.mou();

        comprovaSiAtaca();

        comprovaSiSurtDePantalla();

     }

    /**
     * Comprova si la nau surt de la pantalla i ho evita.
     */
    private void comprovaSiSurtDePantalla() {
        if (getBaix() > armari.getPantallaHeight()) {
            setDireccio(Direccio.AMUNT);
        }

        if (getDalt() <= liniaBase
                && getDireccio() == Direccio.AMUNT.getValor()) {
            setDireccio(Direccio.ESQUERRA);
            setVelocitat(VELOCITATNAU);
        }
    }

    /**
     * Comprova si la nau ha d'atacar o no.
     */
    private void comprovaSiAtaca() {
        if ((Math.random() * MIL) < PROVABILITATATACAR) {
            setDireccio(Direccio.AVALL);
            setVelocitat(VELOCITATNAU * 2);
        }
    }

}
