package tp05.templateMethod2.application.dao;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tp05.templateMethod2.application.modele.Siege;

// Note : la base de données est détruite et recréée à chaque test,
// donc ils sont indépendants.
// 
// On pourrait aussi :
//
// - avoir deux bases, une en mémoire pour le test, l'autre persistante pour l'application (plus réaliste)
// - travailler sur la base persistante, mais faire chaque test dans une transaction, avec
//   rollback à la fin.
public class SiegeDAOTest {
    
    @Before
    public void init() throws SQLException {
        DbDefinition db = new DbDefinition();
        db.initialiserDB();
    }

    @Test
    public void testSaveAndFind() throws SQLException {
        SiegeDAO dao = new SiegeDAO();
        Siege siege = new Siege(1l, 3, 30);
        dao.save(siege);
        Siege siege1 = dao.findById(1l);
        assertEquals(1l, siege1.getId());
        assertEquals(3, siege1.getNumeroVoiture());
        assertEquals(30, siege1.getNumeroPlace());
    }

    @Test
    public void testFindAll() throws SQLException {
        SiegeDAO dao = new SiegeDAO();
        dao.save(new Siege(2, 3, 4));
        dao.save(new Siege(5, 8, 4));
        List<Siege> list = dao.findAll();
        assertEquals(2, list.size());
    }
}
