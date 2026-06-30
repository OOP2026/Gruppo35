package controller;

import gui.GUIhome;

/**
 * Classe di avvio dell'applicazione.
 * Inizializza la finestra principale e crea il controller che collega
 * la GUI alla logica applicativa.
 */
public class Main {

    /**
     * Metodo principale dell'applicazione.
     * Crea la schermata home, inizializza il controller e rende visibile
     * la finestra principale.
     *
     * @param args argomenti della riga di comando, non utilizzati
     */
    public static void main(String[] args) {
        GUIhome gui = new GUIhome();
        new Controller(gui);
        gui.setVisible(true);
    }
}