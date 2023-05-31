package com.timesheet.controller;

import com.timesheet.controller.main.Attributes;
import com.timesheet.model.Teachers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/teacher")
public class TeacherCont extends Attributes {
    @GetMapping
    public String Teacher(Model model) {
        AddAttributesTeacher(model);
        return "teacher";
    }

    @PostMapping("/edit/photo")
    public String TeacherEditPhoto(Model model, @RequestParam MultipartFile photo) {
        if (photo != null && !Objects.requireNonNull(photo.getOriginalFilename()).isEmpty()) {
            String res = "";
            String uuidFile = UUID.randomUUID().toString();
            boolean createDir = true;
            try {
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) createDir = uploadDir.mkdir();
                if (createDir) {
                    res = "teachers/" + uuidFile + "_" + photo.getOriginalFilename();
                    photo.transferTo(new File(uploadImg + "/" + res));
                }
            } catch (Exception e) {
                model.addAttribute("message", "Некорректный данные!");
                AddAttributesTeacher(model);
                return "teacher";
            }
            Teachers teacher = getUser().getTeacher();
            teacher.setPhoto(res);
            teachersRepo.save(teacher);
        }
        return "redirect:/teacher";
    }

    @PostMapping("/edit")
    public String TeacherEdit(@RequestParam String fio, @RequestParam Long category, @RequestParam String tel, @RequestParam byte experience) {
        Teachers teacher = getUser().getTeacher();
        teacher.setFio(fio);
        teacher.setCategory(categoryRepo.getReferenceById(category).getName());
        teacher.setTel(tel);
        teacher.setExperience(experience);
        teachersRepo.save(teacher);
        return "redirect:/teacher";
    }
}
