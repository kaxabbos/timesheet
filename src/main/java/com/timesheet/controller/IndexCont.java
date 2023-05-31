package com.timesheet.controller;

import com.timesheet.controller.main.Attributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexCont extends Attributes {

    @GetMapping
    public String index1(Model model) {
        AddAttributesIndex(model);
        return "subjects";
    }

    @GetMapping("/index")
    public String index2(Model model) {
        AddAttributesIndex(model);
        return "subjects";
    }

    @PostMapping("/search")
    public String search(Model model, @RequestParam String name,  @RequestParam int price) {
        AddAttributesSearch(model, name,price);
        return "subjects";
    }
}
