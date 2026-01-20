package tp05.templateMethod2.application;

import com.google.inject.Inject;
import java.sql.SQLException;
import tp05.templateMethod2.application.dao.DbDefinition;
import tp05.templateMethod2.application.dao.IdDAO;
import tp05.templateMethod2.application.dao.ReservationDAO;
import tp05.templateMethod2.application.dao.SiegeDAO;
import tp05.templateMethod2.application.modele.Reservation;
import tp05.templateMethod2.application.modele.Siege;


public class Q1 {
    
    private IdDAO idDAO;
    private SiegeDAO siegeDAO;
    private ReservationDAO reservationDAO;
    
    
    @Inject
    public Q1(IdDAO idDAO, SiegeDAO siegeDAO, ReservationDAO reservationDAO) {
        this.idDAO = idDAO;
        this.siegeDAO = siegeDAO;
        this.reservationDAO = reservationDAO;
    }
    
    public void run() throws SQLException {
        // AVANT : on créait la connexion et les DAOs
        // APRÈS : les DAOs sont déjà là, injectés par Guice
        
        // Initialiser la base de données
        DbDefinition db = new DbDefinition();
        db.initialiserDB();
        
        // Créer un siège
        Siege siege = new Siege(idDAO.getNextId(), 6, 10);
        siegeDAO.save(siege);
        
        // Créer une réservation
        reservationDAO.save(new Reservation(idDAO.getNextId(), "Lovelace", siege.getId()));
        
        // Vérifier
        System.out.println("Sièges : " + siegeDAO.findAll());
        System.out.println("Réservations : " + reservationDAO.findAll());
    }
}

/*@Test
public void testQ1() {
    // Problème : on doit créer une vraie connexion à la base
    Connection connection = ConnectionFactory.getInstance().getConnection();
    IdDAO idDAO = new IdDAO(connection);
    SiegeDAO siegeDAO = new SiegeDAO(connection);
    ReservationDAO reservationDAO = new ReservationDAO(connection);
    Q1 q1 = new Q1(idDAO, siegeDAO, reservationDAO);
    
    // Le test dépend de ConnectionFactory et de la BD réelle !
    q1.run();
} */