package dao;

import model.Studente;

/**
 * Definisce le operazioni di accesso ai dati relative agli studenti.
 * Le implementazioni di questa interfaccia si occupano di recuperare
 * le informazioni degli studenti dal database.
 */
public interface StudenteDAO {

    /**
     * Recupera uno studente dal database tramite email e password.
     * Il metodo viene utilizzato durante la fase di login dello studente.
     *
     * @param email email inserita nella GUI
     * @param password password inserita nella GUI
     * @return oggetto {@link Studente} se le credenziali sono corrette,
     *         null altrimenti
     */
    Studente loginStudente(String email, String password);
}