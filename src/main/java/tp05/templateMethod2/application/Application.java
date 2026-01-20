package tp05.templateMethod2.application;

import com.google.inject.Inject;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import tp05.templateMethod2.application.dao.IdDAO;
import tp05.templateMethod2.application.dao.ReservationDAO;
import tp05.templateMethod2.application.dao.SiegeDAO;
import tp05.templateMethod2.application.modele.Reservation;
import tp05.templateMethod2.application.modele.Siege;
import tp05.templateMethod2.application.service.*;

/**
 * La couche applicative (métier).
 * 
 * Implémente ReservationService.
 * Les dépendances sont injectées par Guice.
 */
public class Application implements ReservationService {
    
    private IdDAO idDAO;
    private SiegeDAO siegeDAO;
    private ReservationDAO reservationDAO;
    private Connection connection;
    
    /**
     * Constructeur avec injection.
     * 
     * Guice injecte tous les DAOs et la connexion.
     * 
     * @param idDAO DAO pour les IDs
     * @param siegeDAO DAO pour les sièges
     * @param reservationDAO DAO pour les réservations
     * @param connection la connexion à la base
     */
    @Inject
    public Application(IdDAO idDAO, SiegeDAO siegeDAO, ReservationDAO reservationDAO, Connection connection) {
        this.idDAO = idDAO;
        this.siegeDAO = siegeDAO;
        this.reservationDAO = reservationDAO;
        this.connection = connection;
    }
    
    @Override
    public void creerSiege(int numeroVoiture, int numeroPlace) {
        try {
            Siege siege = new Siege(idDAO.getNextId(), numeroVoiture, numeroPlace);
            siegeDAO.save(siege);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la création du siège", e);
        }
    }
    
    @Override
    public List<Siege> siegesDisponibles() {
        try {
            List<Siege> tousLesSieges = siegeDAO.findAll();
            List<Reservation> toutesLesReservations = reservationDAO.findAll();
            
            // Récupérer les IDs des sièges réservés
            List<Long> siegesReserves = new ArrayList<>();
            for (Reservation res : toutesLesReservations) {
                if (res.aSiegeAttribue()) {
                    siegesReserves.add(res.getSiegeId());
                }
            }
            
            // Retourner les sièges non réservés
            List<Siege> disponibles = new ArrayList<>();
            for (Siege siege : tousLesSieges) {
                if (!siegesReserves.contains(siege.getId())) {
                    disponibles.add(siege);
                }
            }
            return disponibles;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des sièges disponibles", e);
        }
    }
    
    @Override
    public Reservation reserver(String nomClient) {
        try {
            // Récupérer les sièges disponibles
            List<Siege> disponibles = siegesDisponibles();
            
            long siegeId = 0;
            if (!disponibles.isEmpty()) {
                // Attribuer le premier siège disponible
                siegeId = disponibles.get(0).getId();
            }
            
            // Créer la réservation
            Reservation reservation = new Reservation(idDAO.getNextId(), nomClient, siegeId);
            reservationDAO.save(reservation);
            
            return reservation;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la réservation", e);
        }
    }
    
    @Override
    public List<Reservation> reservations() {
        try {
            return reservationDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des réservations", e);
        }
    }
}