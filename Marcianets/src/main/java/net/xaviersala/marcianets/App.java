package net.xaviersala.marcianets;

import java.awt.event.KeyEvent;
import java.util.Random;

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
     * Classe que conté les imatges del joc.
     */
     private Armari armari = Armari.getInstance();

     /**
      * Protagonista del joc que es controla per l'usuari.
      */
     private Nau protagonista;
    /**
     * Generador de números.
     */
     private Random r;

     /**
      * Label amb les bales que queden.
      */
     private GLabel balesDisponibles;
     /**
      * Execució del programa.
      */
    public final void run() {

        armari.setPantalla(this);

        carregarImatges();

        clicaPerComencar();

        afegirProtagonista();

        afegirNausEnemigues();

        creaMarcador();
        canviaMarcador();

        while (true) {
            pause(RETARD);
            armari.mou();
        }

    }


    /**
     * Afegir la nau principal del joc.
     */
    private void afegirProtagonista() {
        int principal = armari.addNau(TipusNau.NAUAMIGA,
                posicioAleatoria(SCREENWIDTH),
                SCREENHEIGHT - POSICIOCINCUANTA);
        protagonista =  (Nau) armari.getElement(principal);
    }


    /**
     * Agegir naus enemigues.
     */
    private void afegirNausEnemigues() {
        int lloc = PosicioFiles.TERCERAFILA.getPosicio();
        for (int i = 0; i < NUMNAUS; i++) {
            armari.addNau(TipusNau.NAUENEMIGANORMAL,
                    POSICIOCINCUANTA * i, lloc);
        }
        lloc = PosicioFiles.SEGONAFILA.getPosicio();
        for (int i = 0; i < NUMNAUS; i++) {
            armari.addNau(TipusNau.NAUENEMIGAFORTA,
                    POSICIOCINCUANTA * i, lloc);
        }
        lloc = PosicioFiles.PRIMERAFILA.getPosicio();
        armari.addNau(TipusNau.NAUENEMIGAKAMIKAZE,
                0, lloc);
        armari.addNau(TipusNau.NAUENEMIGAKAMIKAZE,
                getWidth() - POSICIOCINCUANTA, lloc);
        armari.addNau(TipusNau.NAUENEMIGAKAMIKAZE,
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
     * Carrega les imatges del joc en l'armari i l'inicialitza perquè
     * es pugui gestionar la pantalla des d'ell.
     */
    private void carregarImatges() {

        armari.setImatge("bala.jpg");

        for (TipusNau d : TipusNau.values()) {
            armari.setImatge(d.getFitxer());
        }
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
     * Prem una tecla i es mou en la direcció que toca.
     * @param e event
     */
    @Override
    public final void keyPressed(final KeyEvent e) {

        switch(e.getKeyCode()) {
        case KeyEvent.VK_UP:
           protagonista.dispara();
           canviaMarcador();
            break;
        case KeyEvent.VK_LEFT:
            protagonista.setDireccio(Direccio.ESQUERRA);
            protagonista.setVelocitat(2);
            break;
        case KeyEvent.VK_RIGHT:
            protagonista.setDireccio(Direccio.DRETA);
            protagonista.setVelocitat(2);

            break;
        case KeyEvent.VK_R:
            ((NauAmiga) protagonista).recarrega();
            canviaMarcador();
        default:
            break;
        }
    }

    /**
     * Actualitza el marcador de bales.
     */
    private void canviaMarcador() {
        String numBales = ((NauAmiga) protagonista).getBalesDisponibles();
        balesDisponibles.setLabel("bales:" + numBales);
    }

    /**
     * Crea el marcador.
     */
    private void creaMarcador() {
        balesDisponibles = new GLabel("bales: 0");
        add(balesDisponibles, getWidth() - balesDisponibles.getWidth(),
                getHeight() - balesDisponibles.getAscent());
    }
    /**
     * Deixa anar la tecla. Només té efecte per la tecla d'avançar.
     * @param e event
     */
    @Override
    public final void keyReleased(final KeyEvent e) {
            protagonista.setVelocitat(0);
    }

    /**
     * Inicialitza el sistema.
     */
    public final void init() {

        setSize(SCREENWIDTH, SCREENHEIGHT);
        addKeyListeners(this);
        r = new Random();
    }
}
