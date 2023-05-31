package com.timesheet.controller;

import com.timesheet.controller.main.Attributes;
import com.timesheet.model.Teachers;
import com.timesheet.model.Users;
import com.timesheet.model.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersCont extends Attributes {
    @GetMapping
    public String subs(Model model) {
        AddAttributesUsers(model);
        return "users";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, @RequestParam Role role) {
        Users user = usersRepo.getReferenceById(id);

        if (role != Role.MANAGER && user.getRole() == Role.MANAGER) {
            Teachers teacher = user.getTeacher();
            user.setTeacher(null);
            teachersRepo.deleteById(teacher.getId());
        }
        if (role == Role.MANAGER && user.getRole() != Role.MANAGER) {
            Teachers teacher = new Teachers(defPhoto);
            teachersRepo.save(teacher);
            user.setTeacher(teacher);
        }

        user.setRole(role);
        usersRepo.save(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(Model model, @PathVariable Long id) {
        Users user = usersRepo.getReferenceById(id);
        if (user == getUser()) {
            AddAttributesUsers(model);
            model.addAttribute("message", "Вы не можете удалить свой профиль!");
            return "users";
        }
        usersRepo.deleteById(id);
        return "redirect:/users";
    }
}
