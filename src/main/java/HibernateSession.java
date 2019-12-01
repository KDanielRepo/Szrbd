import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSession {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory(){
        try {
            return new Configuration().configure().buildSessionFactory();
        }catch (Throwable e){
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
    public static void shutDown(){
        getSessionFactory().close();
    }
}
