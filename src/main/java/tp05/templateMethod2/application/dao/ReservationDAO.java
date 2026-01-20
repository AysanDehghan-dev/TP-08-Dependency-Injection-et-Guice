package tp05.templateMethod2.application.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import tp05.templateMethod2.application.modele.Reservation;
import tp05.templateMethod2.utilitaires.template.SQLQueryTemplate;
import tp05.templateMethod2.utilitaires.template.SQLSimpleHelper;


public class ReservationDAO extends AbstractDAO {
    
    /**
     * Constructeur avec injection de la connexion.
     * 
     * @param connection la connexion à utiliser
     */
    public ReservationDAO(Connection connection) {
        super(connection);
    }

    public void save(Reservation reservation) {
        String sql = "insert into reservation values (?,?,?)";
        SQLSimpleHelper.insert(getConnection(), sql, reservation.getId(), reservation.getClient(), reservation.getSiegeId());
    }

    public void update(Reservation reservation) {
        String sql = "update reservation set client = ?, siege_id = ? where reservation_id = ?";
        SQLSimpleHelper.update(getConnection(), sql, reservation.getClient(), reservation.getSiegeId(), reservation.getId());
    }

    public void delete(Long id) {
        String sql = "delete from reservation where reservation_id = ?";
        SQLSimpleHelper.update(getConnection(), sql, id);
    }

    public Reservation findById(Long id) {
        String sql = "select * from reservation where reservation_id = ?";        
        List<Reservation> res = new ReservationQueryTemplate(sql).execute(id);
        if (res.size() == 0)
            return null;
        else
            return res.get(0);
    }

    public List<Reservation> findAll() {
        String sql = "select * from reservation order by reservation_id";
        return new ReservationQueryTemplate(sql).execute();
    }

    private class ReservationQueryTemplate extends SQLQueryTemplate<Reservation> {        
        protected ReservationQueryTemplate(String sql) {
            super(getConnection(), sql);          
        }

        @Override
        protected Reservation extractData(ResultSet res) throws SQLException {
            Long id = res.getLong("reservation_id");
            String client = res.getString("client");
            Long siegeId = res.getLong("siege_id");
            return new Reservation(id, client, siegeId);
        }            
    }
}