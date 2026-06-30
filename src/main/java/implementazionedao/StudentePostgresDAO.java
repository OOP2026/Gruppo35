package implementazionedao;

import dao.StudenteDAO;
import database_connection.ConnessioneDatabase;
import model.Studente;

import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementazione PostgreSQL dell'interfaccia {@link StudenteDAO}.
 * La classe gestisce le operazioni di accesso ai dati relative agli studenti,
 * utilizzando la connessione al database fornita da {@link ConnessioneDatabase}.
 */
public class StudentePostgresDAO implements StudenteDAO {
    private static final Logger LOGGER = Logger.getLogger(StudentePostgresDAO.class.getName());
    private final Connection connection;

    /**
     * Crea un nuovo DAO per gli studenti utilizzando la connessione condivisa
     * al database PostgreSQL.
     */
    public StudentePostgresDAO() {
        this.connection = ConnessioneDatabase.getInstance().getConnection();
    }

    /**
     * Verifica le credenziali di accesso di uno studente.
     * Il metodo cerca nel database un utente studente con email e password
     * corrispondenti e, se presente, restituisce un oggetto {@link Studente}
     * contenente i dati recuperati.
     *
     * @param email email dello studente utilizzata per il login
     * @param password password dello studente
     * @return lo studente autenticato se le credenziali sono corrette,
     *         null se non esiste uno studente corrispondente
     */
    @Override
    public Studente loginStudente(String email, String password) {
        String query = "SELECT u.nome, u.cognome, u.email, u.password, s.matricola, s.anno_corso " +
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
                    String annoCorso = resultSet.getString("anno_corso");

                    return new Studente(nome, cognome, emailDb, passwordDb, matricola, annoCorso);
                }
            }
        } catch (SQLException e) {
            LOGGER.warning("Errore durante il login dello studente!");
        }

        return null;
    }
}