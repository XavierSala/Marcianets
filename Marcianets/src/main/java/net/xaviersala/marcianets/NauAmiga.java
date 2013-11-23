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
     * Armari d'imatges.
     */
    private Armari armari = Armari.getInstance();
    /**
     * número màxim de bales del carregador.
     */
    protected static final int MAXIMBALES = 6;
    /**
     * Bales que queden en el carregador.
     */
    private int balesDisponibles;
    /**
     * Direcció de les bales.
     */
    private static final int DIRECCIOBALES = 90;
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
     * La nau dispara si té bales disponibles.
     *
     * Per evitar que la bala xoqui amb la nau que l'ha fet moc la
     * bala fins que deixa de tocar.
     */
    public final void dispara() {

        if (balesDisponibles > 0) {
            Bala b = armari.addBala("bala.jpg",  getEsquerra(), getDalt(),
                    DIRECCIOBALES);
            balesDisponibles--;
            treuBalaDeLaNau((Cosa) this, b);
        }
    }

    /**
     * Recarrega una bala.
     */
    public final void recarrega() {
        balesDisponibles++;
    }
}
