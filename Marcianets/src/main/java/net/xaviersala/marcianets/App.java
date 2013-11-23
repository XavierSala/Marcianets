package net.xaviersala.marcianets;

import java.awt.event.KeyEvent;
import java.util.Random;

import acm.graphics.GLabel;
import acm.program.GraphicsProgram;

/**
 * Hello world!
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
     * Direcció esquerra.
     */
    private static final int DIRECCIOESQUERRA = 180;
    /**
     * Direcció dreta.
     */
    private static final int DIRECCIODRETA = 0;
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
     private ArmariImatges armari = ArmariImatges.getInstance();

     /**
      * Protagonista del joc que es controla per l'usuari.
      */
     private Nau protagonista;
    /**
     * Generador de números.
     */
     private Random r;
     /**
      * Execució del programa.
      */
    public final void run() {

        carregarImatges();
        clicaPerComencar();

        int principal = armari.addNau("nau.gif", 0,
                posicioAleatoria(SCREENWIDTH),
                SCREENHEIGHT - POSICIOCINCUANTA);
        protagonista =  (Nau) armari.getElement(principal);

        for (int i = 0; i < NUMNAUS; i++) {
            armari.addNau("enemic1.gif", 1, POSICIOCINCUANTA * i, 0);
        }


        while (true) {
            pause(RETARD);
            armari.mou();
        }


        // System.out.println("Hello World!");
    }


    /**
     * @return
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
        final String[] imatges = {"nau.gif",
                "enemic1.gif",
                "enemic2.gif",
                "bala.jpg"};

        armari.setPantalla(this);

        for (String imatge: imatges) {
            armari.setImatge(imatge);
        }
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
           protagonista.setVelocitat(0);
            break;
        case KeyEvent.VK_LEFT:
            protagonista.setDireccio(DIRECCIOESQUERRA);
            protagonista.setVelocitat(2);
            break;
        case KeyEvent.VK_RIGHT:
            protagonista.setDireccio(DIRECCIODRETA);
            protagonista.setVelocitat(2);
            break;
        default:
            break;
        }
    }

    /**
     * Deixa anar la tecla. Només té efecte per la tecla d'avançar
     * @param e event
     */
    @Override
    public final void keyReleased(final KeyEvent e) {
            protagonista.setVelocitat(0);
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
     * Inicialitza el sistema.
     */
    public final void init() {

        setSize(SCREENWIDTH, SCREENHEIGHT);
        addKeyListeners(this);
        r = new Random();
    }
}
