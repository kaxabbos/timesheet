package com.timesheet.controller.main;

import com.timesheet.model.Teachers;
import com.timesheet.model.Subjects;
import com.timesheet.model.Users;
import com.timesheet.model.enums.Role;
import org.springframework.ui.Model;

import java.util.List;

public class Attributes extends Main {

    protected void AddAttributes(Model model) {
        model.addAttribute("role", getRole());
        model.addAttribute("user", getUser());
    }

    protected void AddAttributesUsers(Model model) {
        AddAttributes(model);
        model.addAttribute("users", usersRepo.findAll());
        model.addAttribute("roles", Role.values());
    }

    protected void AddAttributesSubjectAdd(Model model) {
        AddAttributes(model);
        model.addAttribute("categories", categoryRepo.findAll());
        model.addAttribute("teachers", usersRepo.findAllByRole(Role.MANAGER));
    }

    protected void AddAttributesSubjectEdit(Model model, Long id) {
        AddAttributes(model);
        model.addAttribute("categories", categoryRepo.findAll());
        model.addAttribute("subject", subjectsRepo.getReferenceById(id));
        model.addAttribute("teachers", usersRepo.findAllByRole(Role.MANAGER));
    }

    protected void AddAttributesSubjectsMy(Model model) {
        AddAttributes(model);
        Users user = getUser();
        model.addAttribute("subjects", user.getSubjects());
    }

    protected void AddAttributesSubject(Model model, Long id) {
        AddAttributes(model);
        Subjects subject = subjectsRepo.getReferenceById(id);
        List<Teachers> teachers = teachersRepo.findAllByCategory(subject.getCategory().getName());
        model.addAttribute("subject", subject);
        model.addAttribute("teachers", teachers);
    }

    protected void AddAttributesIndex(Model model) {
        AddAttributes(model);
        model.addAttribute("subjects", subjectsRepo.findAll());
        model.addAttribute("categories", categoryRepo.findAll());
    }

    protected void AddAttributesCategory(Model model) {
        AddAttributes(model);
        model.addAttribute("categories", categoryRepo.findAll());
    }

    protected void AddAttributesTeacher(Model model) {
        AddAttributes(model);
        model.addAttribute("categories", categoryRepo.findAll());
    }

    protected void AddAttributesTeachers(Model model) {
        AddAttributes(model);
        model.addAttribute("teachers", teachersRepo.findAll());
    }

    protected void AddAttributesSearch(Model model, String name, int price) {
        AddAttributes(model);
        model.addAttribute("subjects", subjectsRepo.findAllByNameContainingAndPrice(name, price));
        model.addAttribute("categories", categoryRepo.findAll());
        model.addAttribute("name", name);
        model.addAttribute("price", price);
    }

    protected void AddAttributesStats(Model model) {
        AddAttributes(model);
        model.addAttribute("teachers", usersRepo.findAllByRole(Role.MANAGER));
    }

    protected void AddAttributesStats(Model model, Long id) {
        AddAttributes(model);
        model.addAttribute("teacher", usersRepo.getReferenceById(id));
    }
}
