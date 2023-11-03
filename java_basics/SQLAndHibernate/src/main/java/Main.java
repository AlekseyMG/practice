import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {
    private static StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure("hibernate.cfg.xml").build();
    private static Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
    private static SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

    public static void main(String[] args) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hqlInsert = "insert into LinkedPurchaseList (student, course) " +
                "select student, course " +
                "from PurchaseList";
        int createdEntities = session.createQuery(hqlInsert).executeUpdate();

        transaction.commit();
        sessionFactory.close();
    }
}