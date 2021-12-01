package hw15.q1.utilities;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class emfSingleton {

        private static EntityManager entityManager= Persistence
                .createEntityManagerFactory("bank")
                .createEntityManager();
        public static EntityManager getEntityManager(){
            return entityManager;
        }

}
