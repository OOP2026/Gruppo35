package controller;

import gui.GUIhome;

public class Main {
    public static void main(String[] args) {
        // inizializzo l'interfaccia grafica principale
        GUIhome gui = new GUIhome();

        // inizializzo il Controller
        new Controller(gui);

        // rendo visibile la finestra di login
        gui.setVisible(true);
    }
}