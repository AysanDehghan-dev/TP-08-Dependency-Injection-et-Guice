package tp05.templateMethod2.application.dao;

import java.sql.Connection;

//import tp05.templateMethod2.application.dbConfig.ConnectionFactory;

/**
 * Version simpliste (et à modifier !!!) de AbstractDAO...
 */
public class AbstractDAO {

    private Connection connection;
    
    public AbstractDAO(Connection connection) {
        this.connection = connection;   
    }

    protected Connection getConnection() {
        return connection;
    }
    
}
