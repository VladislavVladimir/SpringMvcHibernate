package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String printUsers(Model model) {
        List<User> users = userService.listUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/create")
    public String createUser(Model model) {
        model.addAttribute("user", new User().setId(0L));
        model.addAttribute("infoText", "Заполните данные нового пользователя:");
        return "edit";
    }

    @GetMapping("/edit")
    public String updateUser(@RequestParam(name = "id", required = false, defaultValue = "0") Long id, Model model) {
        User user;
        if (id > 0) {
            user = userService.getUser(id);
            if (user == null) {
                return "redirect:/users";
            }
        } else {
            return "redirect:/users/create";
        }
        model.addAttribute("user", user);
        model.addAttribute("infoText", String.format("Измените данные пользователя с id=%d:", id));
        return "edit";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam(name = "id", required = false, defaultValue = "0") Long id) {
        User user;
        if (id > 0) {
            user = userService.getUser(id);
            if (user != null) {
                userService.deleteUser(id);
            }
        }
        return "redirect:/users";
    }

    @PostMapping("/edit")
    public String updateUser(@ModelAttribute("user") User user, Model model) {
        String isValid = isValidUser(user);
        if (isValid == null) {
            if (user.getId() > 0) {
                userService.updateUser(user);
            } else {
                userService.saveUser(user.setId(null));
            }
            return "redirect:/users";
        } else {
            model.addAttribute("user", user);
            model.addAttribute("infoText", isValid);
            return "edit";
        }
    }

    private String isValidUser(User user) {
        String isValid = null;
        if (user.getAge() < 0) {
            isValid = "Возраст пользователя не может быть ниже 0";
        } else if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            isValid = "Введите имя пользователя";
        } else if (user.getLastName() == null || user.getLastName().isEmpty()) {
            isValid = "Введите фамилию пользователя";
        }
        return isValid;
    }
}