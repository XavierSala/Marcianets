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
    private static final double PROVABILITATATACAR = 0.002;
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
        liniaBase = y + VELOCITATNAU;
    }

    /**
     * El moviment de la nau és l'habitual a menys que toqui que es llanci
     * cap avall.
     */
    public final void mou() {

        comprovaSiAtaca();
        super.mou();
     }

    /**
     * Comprova si la nau surt de la pantalla i ho evita.
     */
    public final void gira() {

        switch (getDireccio()) {

        case AVALL:
            setDireccio(Direccio.AMUNT);
            break;
        case AMUNT:
            if (getDalt() <= liniaBase) {
                giraALesquerra();
            }
            break;
         default:
             setY(liniaBase);
             super.gira();
             break;
        }
    }

    /**
     * Si ha tornat a dalt de tot ha de girar cap a l'esquerra.
     */
    private void giraALesquerra() {
        setY(liniaBase);
        setDireccioIVelocitat(Direccio.ESQUERRA, VELOCITATNAU);
    }

    /**
     * Comprova si la nau ha d'atacar o no.
     */
    private void comprovaSiAtaca() {
        if (Math.random() < PROVABILITATATACAR) {
            setDireccio(Direccio.AVALL);
            setVelocitat(VELOCITATNAU * 2);
        }
    }

    /**
     * Les naus d'aquest tipus mai no disparen.
     * @return sempre torna null
     */
    @Override
    public final Bala comprovaSiDispara() {
        return null;
    }

    /**
     * @return the liniaBase
     */
    public final double getLiniaBase() {
        return liniaBase;
    }

    /**
     * @param base the liniaBase to set
     */
    public final void setLiniaBase(final double base) {
        this.liniaBase = base;
    }


}
