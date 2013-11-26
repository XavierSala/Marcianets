package net.xaviersala.marcianets;

/**
 * Defineix les direccions segons el angle que formen.
 *
 * @author xavier
 *
 */
public enum Direccio {
    /**
     * En dalt és 90 graus.
     */
    AMUNT(90d),
    /**
     * L'esquerra està a 180 graus.
     */
    ESQUERRA(180d),
    /**
     * Avall és a 270 graus.
     */
    AVALL(270d),
    /**
     * La dreta està a 0 graus.
     */
    DRETA(0d),
    /**
     * La volta sencera és 360 graus.
     */
    VOLTA(360d);

/**
 * Valor de la variable.
 */
    private double valor;

    /**
     * Crea una direcció.
     * @param quina Direcció a crear
     */
    Direccio(final double quina) {
        valor = quina;
    }

    /**
     * @return Retorna el valor de la direcció.
     */
    double getValor() {
        return valor;
    }

}
