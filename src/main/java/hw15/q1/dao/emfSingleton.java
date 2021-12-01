package hw15.q1.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class emfSingleton {

        private static EntityManager entityManager= Persistence
                .createEntityManagerFactory("bank_application")
                .createEntityManager();
        public static EntityManager getEntityManager(){
            return entityManager;
        }

}
