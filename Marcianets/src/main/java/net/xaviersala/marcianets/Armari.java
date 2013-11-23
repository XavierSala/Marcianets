/**
 *
 */
package net.xaviersala.marcianets;

import java.awt.Image;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import acm.graphics.GImage;
import acm.graphics.GRectangle;

/**
 * La classe armari és una classe en la que s'hi posen totes les coses que la
 * majoria dels objectes necessiten.
 *
 * @author xavier
 *
 */
public final class Armari {

    /**
     * Singleton.
     */
    private static Armari instance;

    /**
     * Lloc on es pinten les imatges.
     */
    private App pantalla;
    /**
     * Llista de objectes del joc.
     */
     private List<Cosa>coses;

    /**
     * Lloc on es guarden les imatges.
     */
    private Hashtable<String, GImage> armari;
    /**
     * Crea l'armari d'imatge.
     */
    private Armari() {
        armari = new Hashtable<String, GImage>();
        coses = new  CopyOnWriteArrayList<Cosa>();
        pantalla = null;
    }

    /**
     * Obtenir la instancia de l'armari.
     * @return retorna una instància a l'armari
     */
    public static Armari getInstance() {
        if (instance == null) {
            instance = new Armari();
        }
        return instance;
    }

    /**
     * Referència a l'escriptori.
     * @param ref Pantalla on s'escriurà
     */
    public void setPantalla(final App ref) {
        pantalla = ref;
    }

    /**
     * @return the pantalla
     */
    public App getPantalla() {
        return pantalla;
    }

    /**
     * Afegir un objecte estàtic al joc.
     * @param quina per definir quina imatge del cowboy afegim
     * @param x posició x
     * @param y posició y
     */
    public void addEstatica(final String quina,
            final double x, final double y) {
        CosaEstatica c = new CosaEstatica(getImatge(quina), x , y);
        coses.add(c);
        pantalla.add(c.getImatge());
    }

    /**
     * Afegir un Cowboy.
     * @param quina per definir quina imatge del cowboy afegim
     * @param tipus Tipus de nau a crear (amic: 0, enemic: 1)
     * @param x posició x
     * @param y posició y
     * @return posició on s'afegeix
     */
    public int addNau(final String quina, final int tipus,
            final double x, final double y) {
        Cosa c = null;
        switch (tipus) {
        case 0:
            c = new NauAmiga(getImatge(quina), x, y);
            break;
        case 1:
            c = new NauEnemiga(getImatge(quina), x, y);
        default:
            break;
        }
        coses.add(c);
        pantalla.add(c.getImatge());
        return coses.size() - 1;
    }

    /**
     * Afegeix una bala a l'escenari.
     * @param quin Imatge de la bala
     * @param x posició x
     * @param y posició y
     * @param d direcció
     * @return Retorna la bala generada
     */
    public Bala addBala(final String quin, final double x, final double y,
            final double d) {
        Bala b = new Bala(getImatge(quin), x, y, d);
        coses.add(b);
        pantalla.add(b.getImatge());
        return b;
    }

    /**
     * Treure bala.
     * @param b bala a treure
     */
    public void removeBala(final Bala b) {
        pantalla.remove(b.getImatge());
    }

    /**
     * Obtenir el CosaMobil (x).
     * @param i quin volem obtenir
     * @return Retorna l'objecte especificat
     */
    public Cosa getElement(final int i) {
        return coses.get(i);
    }

    /**
     * Treure imatge de la pantalla.
     * @param g Imatge a treure
     */
    public void removeElement(final Cosa g) {
        pantalla.remove(g.getImatge());
    }

    /**
     * Comprova si la bala xoca amb un 'personatge'.
     * @param bala La bala que comprovem
     * @return si ha xocat
     */
    public boolean balaXoca(final Bala bala) {
        GRectangle rectBala = bala.getRectanglePosicio();

        for (Cosa personatge: coses) {
            if (!(personatge instanceof Bala)) {
                if (personatge.getRectanglePosicio().intersects(rectBala)) {
                    personatge.tocat();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Afegeix una imatge a l'armari.
     * @param nom nom de la imatge
     */
    public void setImatge(final String nom) {
        armari.put(nom, new GImage(nom));
    }

    /**
     * Retorna la imatge.
     * @param nom imatge que es vol recuperar
     * @return Imatge a recuperar
     */
    public Image getImatge(final String nom) {
        return armari.get(nom).getImage();
    }

    /**
     * Comprova si està dins de la pantalla.
     * @param b Objecte a comprovar
     * @return Torna si està dins o no
     */
    public boolean dinsPantalla(final Cosa b) {
        GRectangle pant  = new GRectangle(0, 0,
                pantalla.getWidth(), pantalla.getHeight());
        return pant.intersects(b.getRectanglePosicio());
    }


    /**
     * Moure tots els personatges.
     */
    public void mou() {
        for (Iterator< Cosa > it = coses.iterator(); it.hasNext();) {

            Cosa p = it.next();

            if (p.isMort()) {
                coses.remove(p);
                // it.remove();
            } else if (p instanceof CosaMobil) {
                ((CosaMobil) p).mou();

                if (p instanceof Bala) {
                     if (!dinsPantalla(p) || balaXoca((Bala) p)) {
                         removeBala((Bala) p);
                         coses.remove(p);
                         // TODO He de restaurar el carregador del protagonista
                         // Com?
                         // protagonista.recarrega();
                   }
                }
            }
        }
    }

}
