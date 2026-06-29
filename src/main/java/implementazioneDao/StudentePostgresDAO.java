package implementazioneDao;

import dao.StudenteDAO;
import database_connection.ConnessioneDatabase;
import model.Studente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentePostgresDAO implements StudenteDAO {

    private final Connection connection;

    public StudentePostgresDAO() {
        this.connection = ConnessioneDatabase.getInstance().getConnection();
    }

    @Override
    public Studente loginStudente(String email, String password) {
        // Query con JOIN per verificare le credenziali dello studente e prendere la matricola
        String query = "SELECT u.nome, u.cognome, u.email, u.password, s.matricola " +
                "FROM utente u " +
                "JOIN studente s ON u.email = s.email " +
                "WHERE u.email = ? AND u.password = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, email);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nome = resultSet.getString("nome");
                    String cognome = resultSet.getString("cognome");
                    String emailDb = resultSet.getString("email");
                    String passwordDb = resultSet.getString("password");
                    String matricola = resultSet.getString("matricola");

                    // Adattalo ai parametri esatti del tuo costruttore Studente.
                    // Se il costruttore del controller.Main vuole anche l'anno di corso (es: "I"), lo passiamo statico o lo aggiungi a DB.
                    return new Studente(nome, cognome, emailDb, passwordDb, matricola, "I");
                }
            }
        } catch (SQLException e) {
            System.out.println("Errore durante il login dello studente!");
            e.printStackTrace();
        }

        return null;
    }
}