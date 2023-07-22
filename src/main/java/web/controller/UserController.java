package web.controller;

import hiber.config.AppConfig;
import hiber.model.User;
import hiber.config.UserService;
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

    @PostMapping (value = "/delete")
    public String deleteUser(@RequestParam(name = "del") long id) {

        userService.deleteUserById(id);
        return "delete";
    }
    @GetMapping(value = "/edit")
    public String edit (@RequestParam(name = "edit") long id, Model model) {

        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping(value = "/edit")
    public String saveEdit (@RequestParam(name = "id") long id,
                            @RequestParam(name = "name") String name,
                            @RequestParam(name = "last_name") String lastName,
                            @RequestParam(name = "email") String email, Model model) {

        User user = userService.getUser(id);
        user.setFirstName(name);
        user.setLastName(lastName);
        user.setEmail(email);

        userService.add(user);
        return this.user(model);
    }

}
