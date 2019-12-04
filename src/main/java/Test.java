import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        Session session = HibernateSession.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            List<Kierowcy> kierowcies = session.createQuery("from Kierowcy",Kierowcy.class).list();
            kierowcies.forEach(s -> System.out.println(s.getImie()+" "+s.getNazwisko()+" "+s.getIdAutokaru()));
            session.flush();
            session.close();
        }catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }
}
