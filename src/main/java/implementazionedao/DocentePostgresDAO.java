package implementazionedao;

import dao.DocenteDAO;
import database_connection.ConnessioneDatabase;
import model.Docente;

import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementazione PostgreSQL dell'interfaccia {@link DocenteDAO}.
 * La classe gestisce le operazioni di accesso ai dati relative ai docenti,
 * utilizzando una connessione al database fornita da {@link ConnessioneDatabase}.
 */
public class DocentePostgresDAO implements DocenteDAO {
    private final Connection connection;
    private static final Logger LOGGER = Logger.getLogger(DocentePostgresDAO.class.getName());

    /**
     * Crea un nuovo DAO per i docenti utilizzando la connessione condivisa
     * al database PostgreSQL.
     */
    public DocentePostgresDAO() {
        this.connection = ConnessioneDatabase.getInstance().getConnection();
    }

    /**
     * Verifica le credenziali di accesso di un docente.
     * Il metodo cerca nel database un utente docente con email e password
     * corrispondenti e, se presente, restituisce un oggetto {@link Docente}
     * contenente i dati recuperati.
     *
     * @param email email del docente utilizzata per il login
     * @param password password del docente
     * @return il docente autenticato se le credenziali sono corrette,
     *         null se non esiste un docente corrispondente
     */
    @Override
    public Docente loginDocente(String email, String password) {
        String query = "SELECT u.nome, u.cognome, u.email, u.password, d.is_responsabile " +
                "FROM utente u " +
                "JOIN docente d ON u.email = d.email " +
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
                    boolean isResponsabile = resultSet.getBoolean("is_responsabile");

                    return new Docente(nome, cognome, emailDb, passwordDb, isResponsabile);
                }
            }
        } catch (SQLException e) {
            LOGGER.warning("Errore durante il login del docente!");
        }

        return null;
    }
}