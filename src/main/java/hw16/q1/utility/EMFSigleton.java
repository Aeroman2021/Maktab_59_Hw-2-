package hw16.q1.utility;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EMFSigleton {
    private static  EntityManager entityManager= Persistence
            .createEntityManagerFactory("league_1400")
            .createEntityManager();
    public static EntityManager getEntityManager(){
        return entityManager;
    }
}
