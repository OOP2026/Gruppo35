package dao;

import model.Docente;

public interface DocenteDAO {
    /**
     * Recupera un docente dal database tramite la sua email e password (per il login).
     * @param email L'email inserita nella GUI
     * @param password La password inserita nella GUI
     * @return L'oggetto Docente se le credenziali sono corrette, null altrimenti.
     */
    Docente loginDocente(String email, String password);
}