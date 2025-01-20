package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public void addCar(Car car) {
      sessionFactory.getCurrentSession().save(car);
   }

   @Override
   public User getUserByCarModelAndSeries(String model, String series) {
      Session session = sessionFactory.openSession();
      Transaction transaction = null;
      User user = null;

      try {
         transaction = session.beginTransaction();
         Query<User> query = session.createQuery("SELECT u FROM User u JOIN u.car c WHERE c.model" +
                 " = :model AND c.series = :series", User.class);
         query.setParameter("model", model);
         query.setParameter("series", series);
         user = query.uniqueResult();
         transaction.commit();
      } catch (Exception e) {
         if (transaction != null) {
            transaction.rollback();
         }
         e.printStackTrace();
      } finally {
         session.close();
      }

      return user;
   }
}
