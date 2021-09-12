
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class MainTwo {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernateTwo.cfg.xml")
                .addAnnotatedClass(UserTwo.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            List<UserTwo> theUsers = session.createQuery("from UserTwo").getResultList();

            displayUsers(theUsers);

            theUsers = session.createQuery("from UserTwo u where u.lastName='DAVIS'").getResultList();

            displayUsers(theUsers);

            theUsers = session.createQuery("from UserTwo u where" + " u.lastName='DAVIS' OR u.firstName='ED'")
                    .getResultList();


            displayUsers(theUsers);

            theUsers = session.createQuery("from UserTwo u where" + " u.email LIKE '%mail.com%'").getResultList();

            displayUsers(theUsers);

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }

    private static void displayUsers(List<UserTwo> theUsers) {
        for (UserTwo tempUser : theUsers) {
            System.out.println(tempUser.getLastName());
        }
    }
}
