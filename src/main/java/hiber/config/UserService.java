package hiber.config;

import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    List<User> listUsers();

    void dropUsersTable();

     void deleteUserById(long id);

    User getUser(long id);

//    void editUser(long id, String name, String lastName, String email);

}
