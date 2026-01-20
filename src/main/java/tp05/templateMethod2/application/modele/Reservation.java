package tp05.templateMethod2.application.modele;

/**
 * Une réservation.
 * 
 * Un client peut toujours réserver,
 * mais s'il n'y a plus de place disponible, on
 * lui attribue une réservation sans siège (siegeId est 0).
 */

public class Reservation {
    private long id;
    private String client;
    private long siegeId;

    /**
     * Crée un objet réservation pour un client.
     * @param id l'id du client.
     * @param client
     * @param siegeId
     */
    public Reservation(long id, String client, long siegeId) {
        this.id = id;
        this.client = client;
        this.siegeId = siegeId;
    }


    public Reservation(long id, String client) {
        this.id = id;
        this.client = client;
        this.siegeId = 0;
    }

    public long getId() {
        return this.id;
    }

    public String getClient() {
        return this.client;
    }

    public long getSiegeId() {
        return this.siegeId;
    }

    public void setSiegeId(Long siegeId) {
        this.siegeId = siegeId;
    }
    
    /**
     * Cette réservation correspond-elle à un siège existant ?
     */
    public boolean aSiegeAttribue() {
        return this.siegeId != 0l;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + id + "'" +
            ", client='" + client + "'" +
            ", siegeId='" + siegeId + "'" +
            "}";
    }

}
