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
     * Velocitat de la nau.
     */
    public static final int VELOCITATNAU = 4;

    /**
     * Provabilitat d'atacar.
     */
    private static final double PROVABILITATATACAR = 0.002;
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
        // moviment = 1;
    }

    /**
     * Comprova si ha de disparar o no.
     * @return Bala disparada
     */
    public Bala comprovaSiDispara() {
        Bala bala = null;
        if ((Math.random()) < PROVABILITATATACAR) {
            bala = dispara();
        }
        return  bala;
    }
    /**
     * La nau dispara cap avall.
     * @return retona la bala;
     */
    public final Bala dispara() {
        return (Bala) ObjectesFactory.build(TipusNau.BALAENEMIGA,
                getEsquerra(), getDalt());
        // separaObjecteFinsQueNoXoqui((CosaMobil) b);
        // return b;
    }

    /**
     * Gira cap a l'altre costat.
     */
    public void gira() {
        Direccio novaDireccio;
        if (getDireccio() == Direccio.ESQUERRA) {
            novaDireccio = Direccio.DRETA;
        } else {
            novaDireccio = Direccio.ESQUERRA;
        }
        setDireccio(novaDireccio);

        // He hagut de comprovar que no surt de la pantalla per culpa
        // dels objectes kami...
//        double desplasament = getAltura() * -1;
//        if ((getDalt() + desplasament) < 0) {
//            moviment *= -1;
//            desplasament *= -1;
//        }
//        mouA(0, moviment);
//        moviment *= -1;
    }


}
