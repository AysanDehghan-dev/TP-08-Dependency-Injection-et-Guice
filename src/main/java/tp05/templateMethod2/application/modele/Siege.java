package tp05.templateMethod2.application.modele;

/**
 * Un siège dans le train.
 */
public class Siege {
    private long id;
    private int numeroVoiture;
    private int numeroPlace;


    public Siege(long id, int numeroVoiture, int numeroPlace) {
        this.id = id;
        this.numeroVoiture = numeroVoiture;
        this.numeroPlace = numeroPlace;
    }
    
    public long getId() {
        return id;
    }

    public int getNumeroVoiture() {
        return this.numeroVoiture;
    }

    public int getNumeroPlace() {
        return this.numeroPlace;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + id + "'" +
            ", numeroVoiture='" + numeroVoiture + "'" +
            ", numeroPlace='" + numeroPlace + "'" +
            "}";
    }

}
