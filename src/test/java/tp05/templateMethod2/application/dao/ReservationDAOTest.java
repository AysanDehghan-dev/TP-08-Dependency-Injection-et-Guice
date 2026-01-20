package tp05.templateMethod2.application.dao;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import tp05.templateMethod2.application.modele.Reservation;

// Note : la base de données est détruite et recréée à chaque test,
// donc ils sont indépendants.
// 
// On pourrait aussi :
//
// - avoir deux bases, une en mémoire pour le test, l'autre persistante pour l'application (plus réaliste)
// - travailler sur la base persistante, mais faire chaque test dans une transaction, avec
//   rollback à la fin.
public class ReservationDAOTest {
   
    @Before
    public void init() throws SQLException {
        DbDefinition db = new DbDefinition();
        db.initialiserDB();
    }
    
    @Test
    public void testSaveAndLoadFull() throws SQLException{
        Reservation reservation = new Reservation(2l, "toto", 4l);
        ReservationDAO dao = new ReservationDAO();
        dao.save(reservation);
        Reservation r1 = dao.findById(2l);
        assertEquals(2l, r1.getId());
        assertEquals("toto", r1.getClient());
        assertEquals(4l, r1.getSiegeId());
    }

    @Test
    public void testSaveWithNull() throws SQLException{
        // Test réservation sans siège.
        Reservation reservation = new Reservation(2l, "toto");
        ReservationDAO dao = new ReservationDAO();
        dao.save(reservation);
        Reservation r1 = dao.findById(2l);
        assertEquals(2l, r1.getId());
        assertEquals("toto", r1.getClient());
        assertEquals(0, r1.getSiegeId());
    }

}
