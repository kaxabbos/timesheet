package com.timesheet.controller;

import com.timesheet.controller.main.Attributes;
import com.timesheet.model.Comments;
import com.timesheet.model.Subjects;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/subjects")
public class SubjectsCont extends Attributes {

    @GetMapping("/add")
    public String subjectAdd(Model model) {
        AddAttributesSubjectAdd(model);
        return "subjectAdd";
    }

    @PostMapping("/comment/add/{id}")
    public String commentAdd(@RequestParam String text, @PathVariable Long id) {
        commentsRepo.save(new Comments(text, DateNow(), getUser(), subjectsRepo.getReferenceById(id)));
        return "redirect:/subjects/{id}";
    }

    @GetMapping("/my")
    public String orderingsMy(Model model) {
        AddAttributesSubjectsMy(model);
        return "subjectsMy";
    }


    @GetMapping("/{id}")
    public String subject(Model model, @PathVariable Long id) {
        AddAttributesSubject(model, id);
        return "subject";
    }

    @GetMapping("/edit/{id}")
    public String subjectEdit(Model model, @PathVariable Long id) {
        AddAttributesSubjectEdit(model, id);
        return "subjectEdit";
    }

    @GetMapping("/delete/{id}")
    public String subjectDelete(@PathVariable Long id) {
        subjectsRepo.deleteById(id);
        return "redirect:/";
    }


    @PostMapping("/add")
    public String subjectAddNew(Model model, @RequestParam String name, @RequestParam Long categoryId, @RequestParam Long teacherId, @RequestParam MultipartFile photo, @RequestParam int price, @RequestParam String description) {
        String res = "";
        if (photo != null && !Objects.requireNonNull(photo.getOriginalFilename()).isEmpty()) {
            String uuidFile = UUID.randomUUID().toString();
            boolean createDir = true;
            try {
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) createDir = uploadDir.mkdir();
                if (createDir) {
                    res = "subjects/" + uuidFile + "_" + photo.getOriginalFilename();
                    photo.transferTo(new File(uploadImg + "/" + res));
                }
            } catch (Exception e) {
                model.addAttribute("message", "Некорректный данные!");
                AddAttributesSubjectAdd(model);
                return "subjectAdd";
            }
        }

        subjectsRepo.save(new Subjects(usersRepo.getReferenceById(teacherId), categoryRepo.getReferenceById(categoryId), name, res, price, description));

        return "redirect:/subjects/add";
    }

    @PostMapping("/edit/{id}")
    public String subjectEditOld(Model model, @RequestParam String name, @RequestParam Long categoryId, @RequestParam Long teacherId, @RequestParam MultipartFile photo, @RequestParam int price, @RequestParam String description, @PathVariable Long id) {
        Subjects subject = subjectsRepo.getReferenceById(id);
        if (photo != null && !Objects.requireNonNull(photo.getOriginalFilename()).isEmpty()) {
            String res = "";
            String uuidFile = UUID.randomUUID().toString();
            boolean createDir = true;
            try {
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) createDir = uploadDir.mkdir();
                if (createDir) {
                    res = "subjects/" + uuidFile + "_" + photo.getOriginalFilename();
                    photo.transferTo(new File(uploadImg + "/" + res));
                }
            } catch (Exception e) {
                model.addAttribute("message", "Некорректный данные!");
                AddAttributesSubjectEdit(model, id);
                return "subjectEdit";
            }
            subject.setPhoto(res);
        }

        subject.setName(name);
        subject.setPrice(price);
        subject.setDescription(description);
        subject.setCategory(categoryRepo.getReferenceById(categoryId));
        subject.setTeacher(usersRepo.getReferenceById(teacherId));

        subjectsRepo.save(subject);

        return "redirect:/";
    }
}
