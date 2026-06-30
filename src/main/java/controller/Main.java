package controller;

import gui.GUIhome;

public class Main {
    public static void main(String[] args) {
        GUIhome gui = new GUIhome();
        new Controller(gui);
        gui.setVisible(true);
    }
}