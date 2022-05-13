import dao.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();


        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Purchase> query = builder.createQuery(Purchase.class);
        Root<Purchase> root = query.from(Purchase.class);
        query.select(root);
        List<Purchase> purchaseList = session.createQuery(query).getResultList();

        CriteriaBuilder builderLinked = session.getCriteriaBuilder();
        CriteriaQuery<LinkedPurchase> queryLinked = builderLinked.createQuery(LinkedPurchase.class);
        Root<LinkedPurchase> rootLinked = queryLinked.from(LinkedPurchase.class);
        queryLinked.select(rootLinked);
        List<LinkedPurchase> purchaseLinkedList = session.createQuery(queryLinked).getResultList();

        purchaseList.forEach(purchase -> {
            purchaseLinkedList.add(new LinkedPurchase(purchase.getKey().getStudentName(), purchase.getKey().getCourseName()));
        });

//        session.getSession().saveOrUpdate(purchaseLinkedList);

        transaction.commit();

        session.close();

    }

}
