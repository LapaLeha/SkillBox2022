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

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PurchaseLists> query = builder.createQuery(PurchaseLists.class);
        Root<PurchaseLists> root = query.from(PurchaseLists.class);
        query.select(root);
        List<PurchaseLists> purchaseLists = session.createQuery(query).getResultList();

        for (PurchaseLists p : purchaseLists) {
            String nameStudent = p.getStudentName();
            String nameCourse = p.getCourseName();

            CriteriaBuilder builderC = session.getCriteriaBuilder();
            CriteriaQuery<Course> queryC = builderC.createQuery(Course.class);
            Root<Course> rootC = queryC.from(Course.class);
            queryC.select(rootC).where(builderC.equal(rootC.<String>get("name"), nameCourse));
            List<Course> courseP = session.createQuery(queryC).getResultList();
            Course courseP1 = courseP.get(0);
            Integer c = courseP1.getId();

            CriteriaBuilder builderS = session.getCriteriaBuilder();
            CriteriaQuery<Student> queryS = builderS.createQuery(Student.class);
            Root<Student> rootS = queryS.from(Student.class);
            queryS.select(rootS).where(builderS.equal(rootS.<String>get("name"), nameStudent));
            List<Student> studentsP = session.createQuery(queryS).getResultList();
            Student studentP1 = studentsP.get(0);
            Integer s = studentP1.getId();

            Transaction transaction = session.beginTransaction();
            KeyLinkedPurchaseList keyLinkedPurchaseList = new KeyLinkedPurchaseList(s, c);
            LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList();
            linkedPurchaseList.setId(keyLinkedPurchaseList);
            linkedPurchaseList.setCourseId(c);
            linkedPurchaseList.setStudentId(s);

            session.save(linkedPurchaseList);
            transaction.commit();

        }
        sessionFactory.close();
    }
}