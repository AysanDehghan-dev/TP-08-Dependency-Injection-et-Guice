package tp05.templateMethod2.application.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import tp05.templateMethod2.application.modele.Siege;
import tp05.templateMethod2.utilitaires.template.SQLQueryTemplate;
import tp05.templateMethod2.utilitaires.template.SQLSimpleHelper;

/**
 * DAO pour les sièges.
 * Modifié : la connexion est injectée via le constructeur.
 */
public class SiegeDAO extends AbstractDAO {
    
    /**
     * Constructeur avec injection de la connexion.
     * 
     * @param connection la connexion à utiliser
     */
    public SiegeDAO(Connection connection) {
        super(connection);
    }

    public void save(Siege siege) throws SQLException {
        String sql = "insert into siege values (?,?,?)";
        SQLSimpleHelper.insert(getConnection(), sql, siege.getId(), siege.getNumeroVoiture(), siege.getNumeroPlace());
    }

    public void update(Siege siege) throws SQLException {
        String sql = "update siege set voiture = ?, place = ? where siege_id = ?";
        SQLSimpleHelper.update(getConnection(), sql, siege.getNumeroVoiture(), siege.getNumeroPlace(), siege.getId());
    }

    public void delete(Long id) throws SQLException {
        String sql = "delete from siege where siege_id = ?";
        SQLSimpleHelper.update(getConnection(), sql, id);
    }

    public Siege findById(Long id) throws SQLException {
        String sql = "select * from siege where siege_id = ?";        
        List<Siege> res = new SiegeQueryTemplate(sql).execute(id);
        if (res.size() == 0)
            return null;
        else
            return res.get(0);
    }

    public List<Siege> findAll() throws SQLException {
        String sql = "select * from siege order by siege_id";
        return new SiegeQueryTemplate(sql).execute();
    }

    private class SiegeQueryTemplate extends SQLQueryTemplate<Siege> {        
        protected SiegeQueryTemplate(String sql) {
            super(getConnection(), sql);          
        }

        @Override
        protected Siege extractData(ResultSet res) throws SQLException {
            Long id = res.getLong("siege_id");
            int voiture = res.getInt("voiture");
            int place = res.getInt("place");
            return new Siege(id, voiture, place);
        }            
    }
}