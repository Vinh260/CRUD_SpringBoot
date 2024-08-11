package com.mycompany.webapplication.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;



@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public String showUserList(Model model) {
        List<User> listUser = userService.ListAll();
        model.addAttribute("listUser", listUser);

        return "users";
    }
    @GetMapping("/user/addNew")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add User");
        return "add_form";
    }
    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes redirectAttributes) {
        userService.save(user);
        redirectAttributes.addFlashAttribute("message", "User added successfully");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model,RedirectAttributes redirectAttributes) {

        try {
            User user = userService.get(id);
            model.addAttribute("user",user);
            model.addAttribute("pageTitle", "Edit User (ID: " +id + ")");
            return "add_form";
        } catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/users";

    }

    @GetMapping("/users/delete/{id}")
    public String DeleteUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            userService.delete(id);

        } catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/users";
    }


}
