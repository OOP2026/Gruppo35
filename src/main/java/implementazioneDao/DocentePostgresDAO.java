package implementazioneDao;

import dao.DocenteDAO;
import database_connection.ConnessioneDatabase;
import model.Docente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DocentePostgresDAO implements DocenteDAO {

    private final Connection connection;

    public DocentePostgresDAO() {
        // Recuperiamo l'istanza unica della connessione (Singleton)
        this.connection = ConnessioneDatabase.getInstance().getConnection();
    }

    @Override
    public Docente loginDocente(String email, String password) {
        // Query con JOIN per verificare le credenziali e capire se è responsabile
        String query = "SELECT u.nome, u.cognome, u.email, u.password, d.is_responsabile " +
                "FROM utente u " +
                "JOIN docente d ON u.email = d.email " +
                "WHERE u.email = ? AND u.password = ?";

        // Uso il try-with-resources per chiudere automaticamente le risorse JDBC ed evitare memory leak
        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, email);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Estraiamo i dati dal record trovato
                    String nome = resultSet.getString("nome");
                    String cognome = resultSet.getString("cognome");
                    String emailDb = resultSet.getString("email");
                    String passwordDb = resultSet.getString("password");
                    boolean isResponsabile = resultSet.getBoolean("is_responsabile");

                    // Istanziamo l'oggetto Docente da restituire
                    Docente docente = new Docente(nome, cognome, emailDb, passwordDb);

                    // Se la tua classe Docente ha un setter o un flag per il ruolo, lo imposti qui.
                    // Ad esempio: docente.setResponsabile(isResponsabile);

                    return docente;
                }
            }
        } catch (SQLException e) {
            System.out.println("Errore durante il login del docente!");
            e.printStackTrace();
        }

        return null; // Credenziali errate o utente non trovato
    }
}