package database_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SuppressWarnings({"java:S106", "java:S1148"})
public class ConnessioneDatabase {
    private static ConnessioneDatabase instance;
    private Connection connection;

    private static final String URL = "jdbc:postgresql://localhost:5432/ProgettoPOO";
    private static final String USER = "postgres";

    @SuppressWarnings("java:S2068")
    private static final String PASSWORD = "ale1926";

    /* Il costruttore privato impedisce l'istanziazione diretta dall'esterno */
    private ConnessioneDatabase() {
        try {
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
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

    /* Restituisce l'oggetto Connection utile per preparare le query SQL */
    public Connection getConnection() {
        return connection;
    }
}