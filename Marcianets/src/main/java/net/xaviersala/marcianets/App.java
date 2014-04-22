package net.xaviersala.marcianets;

import java.awt.event.KeyEvent;

import acm.graphics.GLabel;
import acm.program.GraphicsProgram;

/**
 * Joc de marcianets.
 *
 */
public class App extends GraphicsProgram {

    /**
     * Altura de la pantalla.
     */
    private static final int SCREENHEIGHT = 600;
    /**
     * Amplada de la pantalla.
     */
    private static final int SCREENWIDTH = 800;

    /**
     * Espera en cada passada.
     */
    private static final int RETARD = 30;
    /**
     * Número de naus "normals" per cada fila..
     */
    private static final int NUMNAUS = 10;
    /**
     * Posició inicial (una mica triada a l'atzar).
     */
    private static final int POSICIOCINCUANTA = 50;

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 5046186790508838483L;


     /**
      * Pantalla.
      */
     private Pantalla escriptori;

     /**
      * Execució del programa.
      */
    public final void run() {

        inicialitzarJoc();

        clicaPerComencar();

        while (escriptori.noPartidaAcabada()) {
            pause(RETARD);
            escriptori.mou();
        }

    }

    /**
     * Inicialitzar totes les coses que necessita el joc.
     * - Crear la pantalla
     * - Carregar les imatges
     * - Afegir el protagonista
     * - Afegir naus enemigues
     * - Crear el marcador
     */
    public final void inicialitzarJoc() {
        escriptori = new Pantalla(this);

        ObjectesFactory.carregarImatges();

        escriptori.addProtagonista();

        afegirNausEnemigues();

        escriptori.creaMarcador();
    }

    /**
     * Agegir naus enemigues.
     */
    public final void afegirNausEnemigues() {
        int lloc = PosicioFiles.TERCERAFILA.getPosicio();
        for (int i = 0; i < NUMNAUS; i++) {
            escriptori.addNau(TipusNau.NAUENEMIGANORMAL,
                    POSICIOCINCUANTA * i, lloc);
        }
        lloc = PosicioFiles.SEGONAFILA.getPosicio();
        for (int i = 0; i < NUMNAUS; i++) {
            escriptori.addNau(TipusNau.NAUENEMIGAFORTA,
                    POSICIOCINCUANTA * i, lloc);
        }
        lloc = PosicioFiles.PRIMERAFILA.getPosicio();
        escriptori.addNau(TipusNau.NAUENEMIGAKAMIKAZE,
                0, lloc);
        escriptori.addNau(TipusNau.NAUENEMIGAKAMIKAZE,
                getWidth() - POSICIOCINCUANTA * 2, lloc);
        escriptori.addNau(TipusNau.NAUENEMIGAKAMIKAZE,
                getWidth() / 2, lloc);
    }


    /**
     * @return the escriptori
     */
    public final Pantalla getEscriptori() {
        return escriptori;
    }

    /**
     * @param pantalla the escriptori to set
     */
    public final void setEscriptori(final Pantalla pantalla) {
        this.escriptori = pantalla;
    }

    /**
     * Clica per començar.
     */
    private void clicaPerComencar() {
        GLabel label = new GLabel("Clica per començar");
        double x = (getWidth() - label.getWidth()) / 2;
        double y = (getHeight() + label.getAscent()) / 2;
        add(label, x, y);
        waitForClick();
        remove(label);
    }

    /**
     * Prem una tecla i es mou en la direcció que toca.
     * @param e event
     */
    @Override
    public final void keyPressed(final KeyEvent e) {

        NauAmiga nau = escriptori.getProtagonista();

        switch(e.getKeyCode()) {
        case KeyEvent.VK_UP:
            protagonistaDispara(nau);
            break;
        case KeyEvent.VK_LEFT:
            nau.setDireccioIVelocitat(Direccio.ESQUERRA, 2);
            break;
        case KeyEvent.VK_RIGHT:
            nau.setDireccioIVelocitat(Direccio.DRETA, 2);
            break;
        case KeyEvent.VK_R:
            nau.recarrega();
            escriptori.canviaMarcador();
            break;
        default:
            break;
        }
    }

    /**
     * Fem que la nau especificada dispari.
     *
     * Si pot disparar ha d'afegir la bala a l'escriptori i se li ha de
     * descomptar una bala del seu marcador
     *
     * @param nau Nau que ha de disparar
     */
    public final void protagonistaDispara(final NauAmiga nau) {
        BalaAmiga b = nau.dispara();
           if (b != null) {
               escriptori.addBala(b);
               if (escriptori.getMarcador() != null) {
                   escriptori.canviaMarcador();
               }
           }
    }


    /**
     * Deixa anar la tecla. Només té efecte per la tecla d'avançar.
     * @param e event
     */
    @Override
    public final void keyReleased(final KeyEvent e) {
            escriptori.getProtagonista().setVelocitat(0);
    }

    /**
     * Inicialitza el sistema.
     */
    public final void init() {

        setSize(SCREENWIDTH, SCREENHEIGHT);
        addKeyListeners(this);

    }
}
