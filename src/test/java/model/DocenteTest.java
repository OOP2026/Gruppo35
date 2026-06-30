package model;

import org.junit.Test; // Import per JUnit 4
import static org.junit.Assert.*; // Import per gli Assert di JUnit 4
import java.time.LocalTime;

public class DocenteTest {

    @Test
    public void testCostruttoreEGetters() {
        // Rimosse le etichette sintattiche (nome:, cognome:, ecc)
        Docente d = new Docente("Mario", "Rossi", "mario@email.it", "password", false);
        assertEquals("Mario", d.getNome());
    }

    @Test
    public void testAggiungiVincoloLimite() {
        Docente d = new Docente("Claudio", "Mona", "mclaudiomona@studenti.unina.it", "11", true);

        d.aggiungiVincolo(new VincoloDocente("lunedì", LocalTime.parse("10:00"), LocalTime.parse("11:00")));
        d.aggiungiVincolo(new VincoloDocente("martedi", LocalTime.parse("10:00"), LocalTime.parse("11:00")));
        d.aggiungiVincolo(new VincoloDocente("mercoledi", LocalTime.parse("10:00"), LocalTime.parse("11:00")));
        d.aggiungiVincolo(new VincoloDocente("giovedi", LocalTime.parse("10:00"), LocalTime.parse("11:00")));

        assertEquals(3, d.getVincoli().size());
    }
}