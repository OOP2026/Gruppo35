package database_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SuppressWarnings({"java:S6548"})
public class ConnessioneDatabase {
    private static ConnessioneDatabase instance;
    private Connection connection;

    private static final String URL = "jdbc:postgresql://localhost:5432/ProgettoPOO";
    private static final String USER = "postgres";

    @SuppressWarnings({"java:S2068", "java:S6437"})
    private static final String PASSWORD = "ale1926";

    @SuppressWarnings({"java:S106", "java:S4507", "java:S2068", "java:S6437"})
    private ConnessioneDatabase() {
        try {
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Errore di connessione al database!");
            e.printStackTrace();
        }
    }

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

    public Connection getConnection() {
        return connection;
    }
}