package dao;

import model.Docente;

/**
 * Definisce le operazioni di accesso ai dati relative ai docenti.
 * Le implementazioni di questa interfaccia si occupano di recuperare
 * le informazioni dei docenti dal database.
 */
public interface DocenteDAO {

    /**
     * Recupera un docente dal database tramite email e password.
     * Il metodo viene utilizzato durante la fase di login del docente
     * o del docente responsabile.
     *
     * @param email email inserita nella GUI
     * @param password password inserita nella GUI
     * @return oggetto {@link Docente} se le credenziali sono corrette,
     *         null altrimenti
     */
    Docente loginDocente(String email, String password);
}