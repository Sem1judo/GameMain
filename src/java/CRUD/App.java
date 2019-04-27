package CRUD;


import domain.HibernateUtil;
import domain.Player;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class App {
    public static void main(String[] args) {

        Player student = new Player();
        student.setNickName("ASdasda");
        student.setPhone("s1231231232");
        student.setBalance(12313);
        Player student1 = new Player();
        student1.setNickName("second");
        student1.setPhone("9999999999");
        student1.setBalance(55);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.save(student);
            session.save(student1);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }
}