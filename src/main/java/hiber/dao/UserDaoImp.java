package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   @PersistenceContext
   protected EntityManager em;

   @Override
   public void add(User user) {
      em.persist(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      Query query = em.createQuery("from User");
      return query.getResultList();
   }

   @Override
   public void dropUsersTable() {

      Query query = em.createQuery("DELETE TABLE IF EXISTS users CASCADE");
      query.executeUpdate();

   }

   @Override
   public void deleteUserById(long id) {
      em.remove(id);
   }

}
