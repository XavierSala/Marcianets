package net.xaviersala.marcianets;


import java.awt.Image;

import acm.graphics.GImage;

/**
 * Defineix un objecte que es mou per pantalla.
 *
 * @author xavier
 *
 */
public abstract class CosaMobil extends Cosa {

    /**
     * Direcció del moviment en graus.
     */
    private Direccio direccio;
    /**
     * Velocitat del moviment.
     */
    private double velocitat;
    /**
     * Construeix un personatge a partir del fitxer que conté la imatge.
     * @param fitxer nom del fitxer
     */
    public CosaMobil(final String fitxer) {
        super(fitxer);
        velocitat = 0;
        direccio = Direccio.DRETA;
    }

    /**
     * Construeix un personatge a partir de la imatge.
     * @param img nom del fitxer
     */
    public CosaMobil(final Image img) {
        super(img);
        velocitat = 0;
        direccio = Direccio.DRETA;
    }

    /**
     * Construeix una cosa a partir d'una imatge
     * i la posiciona en les coordenades que se li
     * especifiquen.
     *
     * @param img imatge a posar
     * @param x Coordenada x
     * @param y Coordenada y
     */
    public CosaMobil(final Image img, final double x, final double y) {
        super(img, x, y);
        velocitat = 0;
        direccio = Direccio.DRETA;
    }

    /**
     * Els personatges es poden moure.
     */
    public void mou() {
        GImage dibuix = getImatge();
        dibuix.movePolar(velocitat, direccio.getValor());
    }

    /**
     * Els personatges poden tornar enrere.
     */
    public final void mouUndo() {
        GImage dibuix = getImatge();
        dibuix.movePolar(velocitat * -1, direccio.getValor());
    }

    /**
     * Defineix una nova direcció.
     * @param dreta Direcció en que es vol moure
     */
    public final void setDireccio(final Direccio dreta) {
        direccio = dreta;
    }

    /**
     * Retorna la direcció en la que s'està movent.
     * @return la direcció en graus
     */
    public final Direccio getDireccio() {
        return direccio;
    }

    /**
     * Defineix una nova velocitat per l'objecte.
     * @param mida velocitat en píxels
     */
    public final void setVelocitat(final double mida) {
        velocitat = mida;
    }

    /**
     * @return Obtenir la velocitat
     */
    public final double getVelocitat() {
        return velocitat;
    }
}
