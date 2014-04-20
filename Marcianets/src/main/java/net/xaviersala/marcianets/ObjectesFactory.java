package net.xaviersala.marcianets;

import java.awt.Image;
import java.util.HashMap;
import java.util.Hashtable;

import acm.graphics.GImage;

/**
 * Crea bales.
 *
 * @author xavier
 */
public final class ObjectesFactory {


    /**
     * Lloc on es guarden les imatges.
     */
    private static HashMap<String, GImage> armari
//    private static Hashtable<String, GImage> armari
            = new HashMap<String, GImage>();

    /**
     * Evitar la creació d'objectes d'aquest tipus.
     */
    private ObjectesFactory() {
    }

    /**
     * Carregar totes les imatges.
     */
    public static void carregarImatges() {
        for (TipusNau t : TipusNau.values()) {
            armari.put(t.getFitxer(), new GImage(t.getFitxer()));
        }
    }

    /**
     * Retorna la imatge associada a l'objecte específic.
     *
     * @param nom nom del fitxer
     * @return imatge imatge associada
     */
    public static Image getImatge(final String nom) {
        if (armari.isEmpty()) { // size() == 0) {
            carregarImatges();
        }
        return armari.get(nom).getImage();
    }

    /**
     * Construeix l'objecte demanat a partir del tipus...
     *
     * @param tipus tipus de nau
     * @param x     Coordenada x
     * @param y     Coordenada y
     * @return L'objecte creat
     */
    public static Cosa build(final TipusNau tipus, final double x,
                             final double y) {

        if (armari.isEmpty()) {
            carregarImatges();
        }
        Cosa c = null;
        switch (tipus) {
            case BALAAMIGA:
                c = new BalaAmiga(armari.get(tipus.getFitxer()).getImage(),
                        x, y);
                break;
            case BALAENEMIGA:
                c = new BalaEnemiga(armari.get(tipus.getFitxer()).getImage(),
                        x, y);
                break;
            case NAUAMIGA:
                c = new NauAmiga(armari.get(tipus.getFitxer()).getImage(),
                        x, y);
                break;
            case NAUENEMIGANORMAL:
                c = new NauEnemiga(armari.get(tipus.getFitxer()).getImage(),
                        x, y);
                break;
            case NAUENEMIGAFORTA:
                c = new NauEnemigaForta(
                        armari.get(tipus.getFitxer()).getImage(),
                        x, y);
                break;
            case NAUENEMIGAKAMIKAZE:
                c = new NauEnemigaKamikaze(
                        armari.get(tipus.getFitxer()).getImage(),
                        x, y);
                break;
            default:
                break;

        }
        return c;
    }
}
