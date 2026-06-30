package model;

/**
 * Rappresenta i possibili stati di una richiesta di spostamento lezione.
 * Una richiesta viene inizialmente creata in attesa e può poi essere
 * approvata o rifiutata dal responsabile degli orari.
 */
public enum StatoRichiesta {

    /**
     * Stato iniziale della richiesta, in attesa di valutazione.
     */
    IN_ATTESA,

    /**
     * Stato della richiesta dopo l'approvazione da parte del responsabile.
     */
    APPROVATA,

    /**
     * Stato della richiesta dopo il rifiuto da parte del responsabile.
     */
    RIFIUTATA
}