package database_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestisce la connessione al database PostgreSQL dell'applicazione.
 * La classe utilizza il pattern Singleton per mantenere una sola istanza
 * di connessione condivisa dai DAO.
 */
@SuppressWarnings({"java:S6548"})
public class ConnessioneDatabase {
    private static ConnessioneDatabase instance;
    private Connection connection;

    private static final String URL = "jdbc:postgresql://localhost:5432/ProgettoPOO";
    private static final String USER = "postgres";

    @SuppressWarnings({"java:S2068", "java:S6437"})
    private static final String PASSWORD = "ale1926";

    /**
     * Crea una nuova connessione al database PostgreSQL.
     * Il costruttore è privato perché la classe viene istanziata tramite
     * il metodo {@link #getInstance()}.
     */
    @SuppressWarnings({"java:S106", "java:S4507", "java:S2068", "java:S6437"})
    private ConnessioneDatabase() {
        try {
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Errore di connessione al database!");
            e.printStackTrace();
        }
    }

    /**
     * Restituisce l'istanza condivisa della classe di connessione.
     * Se l'istanza non esiste o la connessione è stata chiusa, viene creata
     * una nuova connessione al database.
     *
     * @return istanza condivisa di {@code ConnessioneDatabase}
     */
    @SuppressWarnings({"java:S4507"})
    public static ConnessioneDatabase getInstance() {
        try {
            if (instance == null || instance.getConnection().isClosed()) {
                instance = new ConnessioneDatabase();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instance;
    }

    /**
     * Restituisce la connessione JDBC attiva al database.
     * I DAO utilizzano questa connessione per eseguire query SQL.
     *
     * @return connessione JDBC al database PostgreSQL
     */
    public Connection getConnection() {
        return connection;
    }
}