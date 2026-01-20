package tp05.templateMethod2.application.dao;

import java.sql.Connection;
import tp05.templateMethod2.utilitaires.template.SQLSimpleHelper;


public class IdDAO extends AbstractDAO {
    
    /**
     * Constructeur avec injection de la connexion.
     * 
     * @param connection la connexion à utiliser
     */
    public IdDAO(Connection connection) {
        super(connection);
    }

    public long getNextId() {
        String sql = "SELECT NEXT VALUE FOR SEQUENCE_ID";
        return SQLSimpleHelper.sqlLongQuery(getConnection(), sql);
    }
}