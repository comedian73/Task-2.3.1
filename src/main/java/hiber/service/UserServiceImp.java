package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Transactional
   @Override
   public void dropUsersTable(){userDao.dropUsersTable();}

   @Transactional
   @Override
   public  void deleteUserById(long id) {userDao.deleteUserById(id);}

   @Transactional
   @Override
   public User getUser(long id) {return userDao.getUser(id);}

//   @Transactional
//   @Override
//   public void editUser(long id, String name, String lastName, String email) {
//      userDao.editUser(id,name,lastName,email);
//   }

}
