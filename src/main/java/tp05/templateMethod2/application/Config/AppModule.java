package tp05.templateMethod2.application.Config;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import java.sql.Connection;
import java.sql.SQLException;
import tp05.templateMethod2.application.dao.IdDAO;
import tp05.templateMethod2.application.dao.ReservationDAO;
import tp05.templateMethod2.application.dao.SiegeDAO;
import tp05.templateMethod2.application.dbConfig.ConnectionFactory;

/**
 * Module de configuration Guice.
 * 
 * Définit comment créer les objets et comment les injecter.
 * C'est le "cœur" de l'injection de dépendances.
 */
public class AppModule extends AbstractModule {
    
    @Override
    protected void configure() {
        // Les bindings "simples" vont ici (si nécessaire)
        // Pour l'instant, on va utiliser @Provides pour la plupart
    }
    
   
     //Fournit une Connection unique pour toute l'application.
     
    @Provides
    public Connection provideConnection() {
        return ConnectionFactory.getInstance().getConnection();
    }
    
    /**
     * Fournit un IdDAO injecté avec une Connection.
     * 
     * Guice voit que IdDAO a besoin d'une Connection.
     * Il appelle provideConnection() pour l'obtenir.
     * Puis crée IdDAO(connection).
     * 
     * @param connection fournie par Guice (via provideConnection)
     * @return un IdDAO prêt à utiliser
     */
    @Provides
    public IdDAO provideIdDAO(Connection connection) {
        return new IdDAO(connection);
    }
    
    /**
     * Fournit un SiegeDAO injecté avec une Connection.
     * 
     * @param connection fournie par Guice
     * @return un SiegeDAO prêt à utiliser
     */
    @Provides
    public SiegeDAO provideSiegeDAO(Connection connection) {
        return new SiegeDAO(connection);
    }
    
    /**
     * Fournit un ReservationDAO injecté avec une Connection.
     * 
     * @param connection fournie par Guice
     * @return un ReservationDAO prêt à utiliser
     */
    @Provides
    public ReservationDAO provideReservationDAO(Connection connection) {
        return new ReservationDAO(connection);
    }
}