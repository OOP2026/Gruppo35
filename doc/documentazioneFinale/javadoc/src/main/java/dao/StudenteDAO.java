package dao;

import model.Studente;

public interface StudenteDAO {
    /**
     * Recupera uno studente dal database tramite la sua email e password (per il login).
     * @param email L'email inserita nella GUI
     * @param password La password inserita nella GUI
     * @return L'oggetto Studente se le credenziali sono corrette, null altrimenti.
     */
    Studente loginStudente(String email, String password);
}