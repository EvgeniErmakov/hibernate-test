import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        Session session = factory.openSession();

        try {
            session.beginTransaction();
            List<User> users = (List<User>) session.createQuery("from User").getResultList();

            List<String> result = session.createQuery("select firstName FROM User").list();

            session.getTransaction().commit();

            for (String name : result) {
                System.out.println("-----" + name);
            }

            for (User user : users) {
                System.out.println(user.getUserId() + " " + user.getFirstName() + " " + user.getLastName() + " "
                        + user.getEmail() + " " + user.getPassword());
            }
        } finally {
            factory.close();

        }
    }
}
