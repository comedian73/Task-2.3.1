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
      if (user.getId() == null){
         em.persist(user);
      } else {em.merge(user);}
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
      User user = em.find(User.class, id);
      em.remove(user);
   }

   @Override
   public User getUser (long id) {
      User user = em.find(User.class, id);
      return user;
   }

//   @Override
//   public void editUser(long id, String name, String lastName, String email) {
//      em.createQuery("UPDATE users SET name = :name, last_name = :lastName, email = :email WHERE id = :id")
//              .setParameter("id", id)
//              .setParameter("name", name)
//              .setParameter("lastName", lastName)
//              .setParameter("email", email)
//              .executeUpdate();
//   }
}
