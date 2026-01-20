package tp05.templateMethod2.application.dao;

import java.sql.*;

import tp05.templateMethod2.application.dbConfig.ConnectionFactory;


/**
 * Cette classe contient simplement le code SQL
 * de définition de la base, sous une forme simple
 * à manipuler.
 */
public class DbDefinition {
    public static final String DB_DEFINITION[] = {
        "DROP TABLE IF EXISTS SIEGE",
        "DROP TABLE IF EXISTS RESERVATION",
        "DROP SEQUENCE IF EXISTS SEQUENCE_ID;",
        "CREATE SEQUENCE SEQUENCE_ID START WITH 1",
        "CREATE TABLE SIEGE("
            +"SIEGE_ID BIGINT PRIMARY KEY,"
            +"VOITURE INTEGER,"
            +"PLACE INTEGER"
        +")",
        "CREATE TABLE RESERVATION("
            +"RESERVATION_ID BIGINT PRIMARY KEY,"
            +"CLIENT VARCHAR(255),"
            +"SIEGE_ID BIGINT"
        +")"
    };

    public void initialiserDB() throws SQLException {
        try (
                // Fermeture automatique avec try-with-resources
                Connection connection = ConnectionFactory.getInstance().getConnection();
                Statement st = connection.createStatement();) {
            connection.setAutoCommit(false); // démarre une transaction !
            try {
                for (String ligne : DbDefinition.DB_DEFINITION) {
                    st.executeUpdate(ligne);
                }
                connection.commit();
            } catch (SQLException | RuntimeException e) {
                connection.rollback();
                throw e;
            }
        }
    }
}
