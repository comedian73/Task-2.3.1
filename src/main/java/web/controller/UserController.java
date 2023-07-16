package web.controller;

import hiber.config.AppConfig;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);
    private UserService userService = context.getBean(UserService.class);

    @GetMapping(value = "/user")
    public String user(Model model) {

        List<User> list = userService.listUsers();
        model.addAttribute("list", list);

        return "user";
    }

    @PostMapping(value = "/user")
    public String button(@RequestParam(name = "name") String name,
                         @RequestParam(name = "last_name") String lastName,
                         @RequestParam(name = "email") String email, Model model) {

        userService.add(new User(name, lastName, email));
        return this.user(model);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam(name = "del") long id) {
        userService.deleteUserById(id);
    }

}
