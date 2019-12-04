import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSession {
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory(){
        try {
            registry = new StandardServiceRegistryBuilder().configure().build();

            MetadataSources sources = new MetadataSources(registry);

            Metadata metadata = sources.getMetadataBuilder().build();

            sessionFactory = metadata.getSessionFactoryBuilder().build();
            //return new Configuration().configure().buildSessionFactory();
        }catch (Throwable e){
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
    public static void shutDown(){
        if(registry!=null){
            StandardServiceRegistryBuilder.destroy(registry);
        }
        //getSessionFactory().close();
    }
}
