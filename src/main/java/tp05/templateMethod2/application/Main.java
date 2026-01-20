package tp05.templateMethod2.application;

import com.google.inject.Guice;
import com.google.inject.Injector;
import java.sql.SQLException;
import tp05.templateMethod2.application.Config.AppModule;

/**
 * Point d'entrée de l'application.
 * 
 * AVANT (Exercice 1) : créait manuellement Q1 et toutes ses dépendances
 * APRÈS (Exercice 2) : utilise Guice pour faire l'injection automatiquement
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        // ÉTAPE 1 : Créer un Injector avec la configuration du Module
        // C'est ici qu'on dit à Guice comment créer les objets
        Injector injector = Guice.createInjector(new AppModule());
        
        // ÉTAPE 2 : Demander à Guice une instance de Q1
        // Guice regarde : Q1 a besoin de IdDAO, SiegeDAO, ReservationDAO
        // Il appelle AppModule pour créer ces objets
        // Puis crée Q1 avec ces objets et nous la retourne
        Q1 q1 = injector.getInstance(Q1.class);
        
        // ÉTAPE 3 : Exécuter Q1
        q1.run();
    }
}