import org.hibernate.Query;
import org.hibernate.Session;

public class Test {
    public static void main(String[] args) {
        Session session = HibernateSession.getSessionFactory().openSession();
        Query query = session.createQuery("from Bilety");
    }
}
