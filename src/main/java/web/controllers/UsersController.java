package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.services.UserService;

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
        model.addAttribute("users", userService.listUsers());
        return "users";
    }

    @GetMapping("/create")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("infoText", "Заполните данные нового пользователя:");
        return "edit";
    }

    @GetMapping("/edit")
    public String updateUser(@RequestParam(name = "id", required = false, defaultValue = "0") Long id, Model model) {
        User user = userService.getUser(id);
        if (user == null) return "redirect:/users/create";
        model.addAttribute("user", user);
        model.addAttribute("infoText", String.format("Измените данные пользователя с id=%d:", id));
        return "edit";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam(name = "id", required = false, defaultValue = "0") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @PostMapping("/edit")
    public String updateUser(@ModelAttribute("user") User user, Model model) {
        try {
            userService.saveOrUpdateUser(user);
            return "redirect:/users";
        } catch (IllegalArgumentException e) {
            model.addAttribute("user", user);
            model.addAttribute("infoText", e.getMessage());
            return "edit";
        }
    }
}