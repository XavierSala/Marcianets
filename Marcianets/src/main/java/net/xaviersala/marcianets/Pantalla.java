package net.xaviersala.marcianets;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GRectangle;

/**
 * Pantalla del joc.
 *
 * @author xavier
 *
 */
public class Pantalla {

    /**
     * Llista de objectes del joc.
     */
     private List<Cosa>coses;
    /**
     * Posició inicial (una mica triada a l'atzar).
     */
    private static final int POSICIOCINCUANTA = 50;
    /**
     * Pantalla on escriure.
     */
    private App escriptori;
    /**
     * Protagonista del joc que es controla per l'usuari.
     */
    private NauAmiga protagonista;

    /**
     * Label amb les bales que queden.
     */
    private GLabel balesDisponibles;
    /**
     * Generador de números.
     */
     private Random r;

    /**
     * Crea una pantalla.
     * @param app pantalla on escriure
     */
    public Pantalla(final App app) {
        escriptori = app;
        coses = new  CopyOnWriteArrayList<Cosa>();
        r = new Random();
    }

    /**
     * @return the pantalla
     */
    public App getPantalla() {
        return escriptori;
    }

    /**
     * @return amplada de la pantalla.
     */
    public double getPantallaWidth() {
        return escriptori.getWidth();
    }
    /**
     * @return altura de la pantalla.
     */
    public double getPantallaHeight() {
        return escriptori.getHeight();
    }

    /**
     * Carregar la imatges.
     *
     */
    public final void carregarImatges() {
        ObjectesFactory.carregarImatges();
    }

    /**
     * Afegir la nau principal del joc.
     */
    public final  void addProtagonista() {

        int posicio = addNau(TipusNau.NAUAMIGA,
                 posicioAleatoria((int) escriptori.getWidth()),
                 (int) escriptori.getHeight() - POSICIOCINCUANTA);
        protagonista = (NauAmiga) coses.get(posicio);
    }

    /**
     * Afegir bala.
     * @param b Bala a afegir
     */
    public final void addBala(final Bala b) {
        coses.add(b);
        escriptori.add(b.getImatge());
    }

    /**
     * Treure imatge de la pantalla.
     * @param g Imatge a treure
     */
    public void removeElement(final Cosa g) {
        escriptori.remove(g.getImatge());
        coses.remove(g);
    }


    /**
     * Obtenir el protagonista del joc.
     * @return Nau protagonista
     */
    public final NauAmiga getProtagonista() {
        return protagonista;
    }
    /**
     * Obtenir un valor aleatòri.
     * @param max valor màxim
     * @return número a retornar
     */
    final int posicioAleatoria(final int max) {
        return r.nextInt(max);
    }

    /**
     * Actualitza el marcador de bales.
     */
    public final void canviaMarcador() {
        String numBales = ((NauAmiga) protagonista).getBalesDisponibles();
        balesDisponibles.setLabel("bales:" + numBales);
    }

    /**
     * Crea el marcador.
     */
    public final void creaMarcador() {
        balesDisponibles = new GLabel("bales: 0");
        escriptori.add(balesDisponibles,
                escriptori.getWidth() - balesDisponibles.getWidth(),
                escriptori.getHeight() - balesDisponibles.getAscent());
        canviaMarcador();
    }

    /**
     * Afegeix una nau al joc.
     * @param tipus tipus de nau
     * @param x coordenada x
     * @param y coordenada y
     * @return posició on s'afegeix
     */
    public final int addNau(final TipusNau tipus,
            final double x, final double y) {

        Cosa c = ObjectesFactory.build(tipus, x, y, Direccio.ESQUERRA);
        if (c != null) {
            coses.add(c);
            escriptori.add(c.getImatge());
        }
        return coses.size() - 1;
    }

    /**
     * Moure tots els personatges.
     */
    public final void mou() {
        for (Iterator< Cosa > it = coses.iterator(); it.hasNext();) {

            Cosa p = it.next();

            if (p.isMort()) {
                // Eliminar els elements morts
                removeElement(p);
            } else if (p instanceof CosaMobil) {
                CosaMobil m = (CosaMobil) p;
                m.mou();
                if (p instanceof Bala) {
                    if (!dinsPantalla(p) || balaXoca((Bala) p)) {
                       removeElement(p);
                   }
                } else if (p instanceof NauEnemiga) {
                    if (!totDins(p)) {
                       ((CosaMobil) p).mouUndo();
                       ((NauEnemiga) p).gira();
                    } else {
                        Bala b = ((NauEnemiga) p).comprovaSiDispara();
                        if (b!=null) {
                            addBala(b);
                        }
                    }
               }
            }
        }
    }

    /**
     * Comprova si la bala xoca amb un 'personatge'.
     * @param bala La bala que comprovem
     * @return si ha xocat
     */
    public final boolean balaXoca(final Bala bala) {
        GRectangle rectBala = bala.getRectanglePosicio();

        for (Cosa personatge: coses) {
            if (!(personatge instanceof Bala)) {
                if (personatge.getRectanglePosicio().intersects(rectBala)) {
                        personatge.tocat();
                        if (personatge.isMort()) {
                            personatge.setImatge(
                                    ObjectesFactory.getImatge("explosio.gif"));
                        }
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * Comprova si està dins de la pantalla.
     * @param b Objecte a comprovar
     * @return Torna si està dins o no
     */
    public final boolean dinsPantalla(final Cosa b) {
        GRectangle pant  = new GRectangle(0, 0,
                escriptori.getWidth(), escriptori.getHeight());
        return pant.intersects(b.getRectanglePosicio());
    }

    public final boolean totDins(final Cosa b) {
        GRectangle pant  = new GRectangle(0, 0,
                escriptori.getWidth(), escriptori.getHeight());
        GRectangle resultat = b.getImatge().getBounds().intersection(pant);
        return resultat.equals(b.getImatge().getBounds());


    }

}
