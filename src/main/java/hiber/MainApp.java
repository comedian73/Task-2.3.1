package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      carService.add(new Car("model1", 1));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      carService.add(new Car("model2", 2));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      carService.add(new Car("model3", 3));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
      carService.add(new Car("model4", 4));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      List<Car> cars = carService.listCar();
      for (Car car : cars) {
         System.out.println("Id = "+car.getId());
         System.out.println("Model = "+car.getModel());
         System.out.println("Series = "+car.getSeries());
         System.out.println();
      }

      List<User> usList = carService.getUser("model2", 2);
      for (User us : usList) {
         System.out.println("Возврат пользователя по модели и серии авто");
         System.out.println("ID = "+us.getId());
         System.out.println("First Name = "+ us.getFirstName());
         System.out.println("Last Name = "+us.getLastName());
         System.out.println("Email = "+us.getEmail());
         System.out.println();
      }

//      carService.dropCarTable();
//      userService.dropUsersTable();

      context.close();
   }
}
