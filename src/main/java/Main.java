import ijse.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class Main {
    public static void main(String[] args) {
        /*Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Student");
        List<Student> studentList =  query.list();

        for (Student student :studentList) {
            System.out.println(student.getName());
        }

        transaction.commit();
        session.close();*/

        /*Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Student where name = ?1");
        query.setParameter(1,"dasd");
        Student student = (Student) query.uniqueResult();
        System.out.println(student.getName());
*/
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT id, name FROM Student where id = ?1 and name = ?2");
        query.setParameter(1,1);
        query.setParameter(2,"ttttt");

        Object[] objects = (Object[]) query.uniqueResult();
        transaction.commit();
        session.close();
    }
}
