package mc.hibernatetutorial;

import org.apache.log4j.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import mc.hibernatetutorial.model.*;

public class App 
{
    private static final Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args)
    {
        logger.debug("starting application....");

        final EntityManagerFactory emf = 
                Persistence.createEntityManagerFactory("HelloWorldPU");

        // create session and save TestEntity to database
        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        try {
            TestEntity testEntity = new TestEntity();
            testEntity.setName("super foo");    

            entityManager.persist(testEntity);
            tx.commit();
        }
        catch (Exception e) {
            logger.error("cannot commit transaction", e);
            tx.rollback();
        }
        finally {
            entityManager.close();
        }
        
        emf.close();
    }
}
