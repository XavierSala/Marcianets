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
     * Determina si està mort.
     */
    private boolean mort = false;

    /**
     * Crea una imatge .
     * @param fitxer fitxer que conté la imatge
     */
    public Cosa(final String fitxer) {
        imatge = new GImage(fitxer);
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
    * Canvia la imatge per una de nova que ja està.
    * carregada
    * @param img Imatge a posar
    */
   public final void setImatge(final Image img) {
       imatge.setImage(img);
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
    * Canviar només la posició Y.
    * @param y nova posició de Y
    */
   public final void setY(final double y) {
       imatge.setLocation(imatge.getX(), y);
   }

   /**
    * Canviar només la posició X.
    * @param x nova posició de X
    */
   public final void setX(final double x) {
       imatge.setLocation(x, imatge.getY());
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
     * Mata l'objecte.
     */
    public final void setMort() {
        this.mort = true;
    }

    /**
     * Comprova si el rectangle passat xoca amb l'objecte actual o no.
     * @param r Rectangle que es vol comprovar
     * @return retorna si xoca
     */
    public final boolean xocaAmb(final GRectangle r) {
        return imatge.getBounds().intersects(r);
    }
   /**
    * Indicarà a un objecte que ha estat tocat.
    * @return retorna si l'hem d'eliminar o no.
    */
   public abstract boolean tocat();

}
