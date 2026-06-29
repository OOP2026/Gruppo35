import gui.GUIhome;
import controller.Controller;

public class Main {
    public static void main(String[] args) {
        // 1. Inizializza l'interfaccia grafica principale
        GUIhome gui = new GUIhome();

        // 2. Inizializza il Controller passandogli SOLO la GUI (un solo parametro)
        Controller controller = new Controller(gui);

        // 3. Rendi visibile la finestra di login
        gui.setVisible(true);
    }
}