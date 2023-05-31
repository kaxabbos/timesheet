package com.timesheet.controller;

import com.timesheet.controller.main.Attributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teachers")
public class TeachersCont extends Attributes {
    @GetMapping
    public String Teachers(Model model) {
        AddAttributesTeachers(model);
        return "teachers";
    }
}
