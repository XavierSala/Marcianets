package net.xaviersala.marcianets;

/**
 * Defineix les posicions de les naus enemigues.
 *
 * @author xavier
 *
 */
public enum PosicioFiles {
    /**
     * Primera fila.
     */
    PRIMERAFILA(0),
    /**
     * Segona fila.
     */
    SEGONAFILA(50),
    /**
     * Tercera fila.
     */
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
