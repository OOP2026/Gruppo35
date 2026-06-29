package database_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnessioneDatabase {
    private static ConnessioneDatabase instance;
    private Connection connection;

    private final String url = "jdbc:postgresql://localhost:5432/ProgettoPOO";
    private final String user = "postgres";
    private final String password = "ale1926";

    /* il costruttore privato impedisce l'istanziazione diretta dall'esterno */
    private ConnessioneDatabase() {
        try {
            /* carica esplicitamente il driver JDBC di Postgres */
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver PostgreSQL non trovato! Aggiungi la dipendenza nel pom.xml");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Errore di connessione al database!");
            e.printStackTrace();
        }
    }

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

    /* restituisce l'oggetto Connection utile per preparare le query SQL */
    public Connection getConnection() {
        return connection;
    }
}