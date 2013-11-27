package net.xaviersala.marcianets;

/**
 * Defineix les posicions de les naus enemigues.
 *
 * @author xavier
 *
 */
public enum PosicioFiles {
    PRIMERAFILA(0),
    SEGONAFILA(50),
    TERCERAFILA(100);

    /**
     * Lloc en el que està.
     */
    private int posicio;

    /**
     * Crea la variable.
     * @param pos Posició
     */
    PosicioFiles(final int pos) {
        this.posicio = pos;
    }

    /**
     * Retorna la posició.
     * @return posició
     */
    int getPosicio() {
        return posicio;
    }
}
