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
     * Número de plantes.
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

        escriptori = new Pantalla(this);

        ObjectesFactory.carregarImatges();

        clicaPerComencar();

        escriptori.addProtagonista();

        afegirNausEnemigues();

        escriptori.creaMarcador();

        while (!escriptori.partidaAcabada()) {
            pause(RETARD);
            escriptori.mou();
        }

    }

    /**
     * Agegir naus enemigues.
     */
    private void afegirNausEnemigues() {
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

        switch(e.getKeyCode()) {
        case KeyEvent.VK_UP:
           BalaAmiga b = escriptori.getProtagonista().dispara();
           if (b != null) {
               escriptori.addBala(b);
               escriptori.canviaMarcador();
           }
            break;
        case KeyEvent.VK_LEFT:
            escriptori.getProtagonista().setDireccio(Direccio.ESQUERRA);
            escriptori.getProtagonista().setVelocitat(2);
            break;
        case KeyEvent.VK_RIGHT:
            escriptori.getProtagonista().setDireccio(Direccio.DRETA);
            escriptori.getProtagonista().setVelocitat(2);

            break;
        case KeyEvent.VK_R:
            escriptori.getProtagonista().recarrega();
            escriptori.canviaMarcador();
        default:
            break;
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
