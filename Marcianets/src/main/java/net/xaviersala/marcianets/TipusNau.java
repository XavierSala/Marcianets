/**
 *
 */
package net.xaviersala.marcianets;

/**
 * Tipus de naus que té el joc.
 *
 * @author Xavier
 *
 */
public enum TipusNau {
    /**
     * Naus.
     */
    NAUAMIGA("nau.gif"),
    /**
     * Nau enemiga que no fa res d'especial.
     */
    NAUENEMIGANORMAL("enemic1.gif"),
    /**
     * Nau enemiga que necessita més d'un toc.
     */
    NAUENEMIGAFORTA("enemic2.gif"),
    /**
     * Canvi d'imatge per la nau enemiga forta.
     */
    NAUENEMIGAFORTA2("enemic2b.gif");

    /**
     * Tipus de nau que té la variable.
     */
    private String fitxer;

    /**
     * Construeix un tipus de nau.
     * @param t tipus de nau.
     */
    TipusNau(final String t) {
        this.fitxer = t;
    }

    /**
     * Retorna el tipus de nau.
     * @return el tipus de la nau
     */
    public String getFitxer() {
        return fitxer;
    }
}
