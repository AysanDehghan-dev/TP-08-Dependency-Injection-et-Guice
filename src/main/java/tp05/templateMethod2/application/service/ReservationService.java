package tp05.templateMethod2.application.service;

import java.util.List;
import tp05.templateMethod2.application.modele.Reservation;
import tp05.templateMethod2.application.modele.Siege;

/**
 * Interface pour le service de réservation.
 * Définit les opérations métier disponibles.
 */
public interface ReservationService {
    
    /**
     * Crée un nouveau siège.
     * 
     * @param numeroVoiture le numéro de la voiture
     * @param numeroPlace le numéro de la place
     */
    void creerSiege(int numeroVoiture, int numeroPlace);
    
    /**
     * Liste les sièges disponibles (non réservés).
     * 
     * @return liste des sièges disponibles
     */
    List<Siege> siegesDisponibles();
    
    /**
     * Passe une réservation pour un client.
     * S'il y a un siège disponible, il lui est attribué.
     * Sinon, on utilise l'id 0 comme id de siège.
     * 
     * @param nomClient le nom du client
     * @return la réservation créée
     */
    Reservation reserver(String nomClient);
    
    /**
     * Liste toutes les réservations.
     * 
     * @return liste des réservations
     */
    List<Reservation> reservations();
}