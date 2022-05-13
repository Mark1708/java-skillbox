import dao.Course;
import dao.Purchase;
import dao.Student;
import dao.Subscription;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Subscription.Key key = new Subscription.Key();

        Student student = new Student();
        student.setId(1);

        Course course = new Course();
        course.setId(2);

        key.setStudent(student);
        key.setCourse(course);

        Subscription subscription = session.get(Subscription.class, key);

        System.out.println(subscription.getSubscriptionDate());

        transaction.commit();
        session.close();

    }

}
