package net.xaviersala.marcianets;


import java.awt.Image;

import acm.graphics.GImage;
import acm.graphics.GRectangle;

/**
 * Defineix un objecte Cosa que bàsicament conté una imatge
 * i la seva posició (es fa servir el que conté un objecte GImage).
 *
 * @author xavier
 *
 */
public abstract class Cosa {
    /**
     * Objecte que conté la imatge i la posició de l'objecte.
     */
    private GImage imatge = null;

    /**
     * Identificador del tipus.
     */
    private String tipus;

    /**
     * Determina si està mort.
     */
    private boolean mort = false;

    /**
     * @return the tipus
     */
    public final String getTipus() {
        return tipus;
    }

    /**
     * @param tipusCosa the tipus to set
     */
    public final void setTipus(final String tipusCosa) {
        this.tipus = tipusCosa;
    }

    /**
     * Crea una imatge .
     * @param fitxer fitxer que conté la imatge
     */
    public Cosa(final String fitxer) {
        imatge = new GImage(fitxer);
    }

    /**
     * Crea una imatge i la posiciona en el lloc adequat.
     * @param fitxer Fitxer qeu conté la imatge
     * @param x coordenada
     * @param y coordenada
     */
    public Cosa(final String fitxer, final double x, final double y) {
        imatge = new GImage(fitxer, x, y);
    }

    /**
     * Crea un objecte a partir d'una imatge. La envio ja carregada per
     * aconseguir que no s'hagin de carregar totes les imatges cada cop.
     * @param img Imatge a posar
     */
    public Cosa(final Image img) {
        imatge = new GImage(img);
    }

    /**
     * Crea una imatge i la posiciona en el lloc adequat.
     * @param img Imatge a posar
     * @param x coordenada
     * @param y coordenada
     */
    public Cosa(final Image img, final double x, final double y) {
        this(img);
        imatge.setLocation(x, y);
    }

    /**
     * @return Imatge de l'objecte
     */
   public final GImage getImatge() {
       return imatge;
   }

   /**
    * Canvia la imatge existent per una de nova.
    * @param novaImatge nom del fitxer
    */
   public final void setImatge(final GImage novaImatge) {
       imatge = novaImatge;
   }

   /**
    * Canvia la imatge per una de nova que ja està.
    * carregada
    * @param img Imatge a posar
    */
   public final void setImatge(final Image img) {
       imatge.setImage(img);
   }

   /**
    * Canvia la imatge existent per una de nova des d'un fitxer.
    * @param nomFitxer del fitxer a carregar
    */
    public final void setImatgeFile(final String nomFitxer) {
        imatge = new GImage(nomFitxer);
    }
   /**
    * Posiciona el personatge en les coordenades especificades (x,y).
    * @param x posicio x
    * @param y posicio y
    */
   public final void setPosicio(final double x, final double y) {
           imatge.setLocation(x,  y);
   }

   /**
    * Mou el personate en la direcció i píxels especificats.
    * @param x direcció x
    * @param y direcció y
    */
   public final void mouA(final double x, final double y) {
       imatge.move(x,  y);
   }

   /**
    * @return Posició x de l'objecte
    */
   public final double getEsquerra() {
       return imatge.getX();
   }

   /**
    * @return Posició y de l'objecte
    */
   public final double getDalt() {
       return imatge.getY();
   }

   /**
    * @return retorna la posició màxima de l'objecte.
    */
   public final double getDreta() {
       return imatge.getX() + imatge.getWidth();
   }

   /**
    * @return retorna la posició inferior de l'objecte.
    */
   public final double getBaix() {
       return imatge.getY() + imatge.getHeight();
   }

   /**
    * Retorna l'altura.
    * @return altura de la imatge
    */
   public final double getAltura() {
       return imatge.getHeight();
   }

   /**
    * Retorna l'amplada.
    * @return amplada de la imatge
    */
   public final double getAmplada() {
       return imatge.getWidth();
   }
   /**
    * Retorna el rectangle que conté la imatge.
    *
    * @return Rectangle que conté la imatge
    */
   public final GRectangle getRectanglePosicio() {
       return imatge.getBounds();
   }

   /**
    * @return Determina si està mort o no.
    */
   public final boolean isMort() {
      return mort;
   }

    /**
     * @param estaMort the mort to set
     */
    public final void setMort(final boolean estaMort) {
        this.mort = estaMort;
    }

   /**
    * Indicarà a un objecte que ha estat tocat.
    * @return retorna si l'hem d'eliminar o no.
    */
   public abstract boolean tocat();

}
