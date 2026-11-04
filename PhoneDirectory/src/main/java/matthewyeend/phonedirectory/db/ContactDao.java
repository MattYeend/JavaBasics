/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.phonedirectory.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import matthewyeend.phonedirectory.model.Contact;

/**
 * Data access object for contacts. All SQL uses {@link PreparedStatement}, and
 * log entries never contain contact data (personally identifiable information).
 *
 * @author matthewyeend
 */
public final class ContactDao {

    private static final Logger LOG = Logger.getLogger(ContactDao.class.getName());
    private static final int MYSQL_DUPLICATE_ENTRY = 1062;
    private static final int MAX_SEARCH_LENGTH = 100;

    private static final String SELECT_SEARCH = """
            SELECT id, first_name, last_name, phone_number, email
            FROM contacts
            WHERE first_name LIKE ? ESCAPE '!'
               OR last_name LIKE ? ESCAPE '!'
               OR phone_number LIKE ? ESCAPE '!'
            ORDER BY last_name, first_name
            LIMIT 500
            """;
    private static final String INSERT =
            "INSERT INTO contacts (first_name, last_name, phone_number, email) VALUES (?, ?, ?, ?)";
    private static final String UPDATE =
            "UPDATE contacts SET first_name = ?, last_name = ?, phone_number = ?, email = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM contacts WHERE id = ?";

    private final DataSource dataSource;

    /**
     * Constructs the DAO.
     *
     * @param dataSource the pooled data source to obtain connections from
     */
    public ContactDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Searches contacts by first name, last name or phone number.
     *
     * @param term the text to look for, where blank returns all contacts
     * @return up to 500 matching contacts ordered by last then first name
     * @throws DataAccessException if the query fails
     */
    public List<Contact> search(String term) {
        String safe = term == null ? "" : term.strip();
        if (safe.length() > MAX_SEARCH_LENGTH) {
            safe = safe.substring(0, MAX_SEARCH_LENGTH);
        }
        String pattern = "%" + escapeLike(safe) + "%";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_SEARCH)) {
            ps.setString(1, pattern);
            ps.setString(2, pattern);
            ps.setString(3, pattern);
            try (ResultSet rs = ps.executeQuery()) {
                List<Contact> results = new ArrayList<>();
                while (rs.next()) {
                    results.add(new Contact(
                            rs.getLong("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("phone_number"),
                            rs.getString("email")));
                }
                return results;
            }
        } catch (SQLException ex) {
            throw handle(ex, "search");
        }
    }

    /**
     * Inserts a new contact.
     *
     * @param contact the validated contact to store
     * @throws DuplicateContactException if the phone number already exists
     * @throws DataAccessException       if the insert fails
     */
    public void insert(Contact contact) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT)) {
            ps.setString(1, contact.firstName());
            ps.setString(2, contact.lastName());
            ps.setString(3, contact.phoneNumber());
            ps.setString(4, contact.email());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw handle(ex, "insert");
        }
    }

    /**
     * Updates an existing contact.
     *
     * @param contact the validated contact, which must have a non null id
     * @throws DuplicateContactException if the phone number clashes with another contact
     * @throws DataAccessException       if the update fails
     */
    public void update(Contact contact) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE)) {
            ps.setString(1, contact.firstName());
            ps.setString(2, contact.lastName());
            ps.setString(3, contact.phoneNumber());
            ps.setString(4, contact.email());
            ps.setLong(5, contact.id());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw handle(ex, "update");
        }
    }

    /**
     * Deletes a contact.
     *
     * @param id the identifier of the contact to remove
     * @throws DataAccessException if the delete fails
     */
    public void delete(long id) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw handle(ex, "delete");
        }
    }

    /**
     * Escapes LIKE wildcards so user input is always treated as literal text.
     *
     * @param input the raw search text
     * @return the text with {@code !}, {@code %} and {@code _} escaped
     */
    private static String escapeLike(String input) {
        return input.replace("!", "!!").replace("%", "!%").replace("_", "!_");
    }

    /**
     * Translates an {@link SQLException} into a safe application exception.
     * Only the SQL state and code are logged, as driver messages can echo user data.
     *
     * @param ex        the original exception
     * @param operation a short label for the failed operation
     * @return the exception to throw to the caller
     */
    private static DataAccessException handle(SQLException ex, String operation) {
        if (ex.getErrorCode() == MYSQL_DUPLICATE_ENTRY) {
            return new DuplicateContactException();
        }
        LOG.log(Level.SEVERE, "Database operation failed: {0} (SQLState={1}, code={2})",
                new Object[] {operation, ex.getSQLState(), ex.getErrorCode()});
        return new DataAccessException("Database operation failed.");
    }
}
