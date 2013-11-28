package net.xaviersala.marcianets;

import java.awt.Image;

/**
 * Definició de la nau que control·len els "bons". O sigui que normalment serà
 * controlada per un usuari humà.
 *
 * @author xavier
 *
 */
public class NauAmiga extends Nau {

    /**
     * número màxim de bales del carregador.
     */
    protected static final int MAXIMBALES = 6;
    /**
     * Bales que queden en el carregador.
     */
    private int balesDisponibles;

    /**
     * @return the balesDisponibles
     */
    public final String getBalesDisponibles() {
        return Integer.toString(balesDisponibles);
    }

    /**
     * Crea una nau enemiga.
     * @param img Imatge
     * @param x coordenades x
     * @param y coordenada y
     */
    public NauAmiga(final Image img, final double x, final double y) {
        super(img, x, y);
        balesDisponibles = MAXIMBALES;
    }

    /**
     * Dispara si li queden bales.
     * En cas de no tenir bales es retorna un objecte 'null'
     * @return bala disparada
     */
    @Override
    public final Bala dispara() {
        Bala b = null;
        if (balesDisponibles > 0) {
            b = super.dispara();
            balesDisponibles--;
        }
        return b;
    }
    /**
     * Recarrega una bala.
     */
    public final void recarrega() {
        if (balesDisponibles < MAXIMBALES) {
                balesDisponibles++;
        }
    }
}
