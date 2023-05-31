package com.timesheet.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Subjects {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
    private int count;
    private String photo;
    private int price;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    private Users teacher;
    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comments> comments;

    public Subjects(Users teacher, Category category, String name, String photo, int price, String description) {
        this.teacher = teacher;
        this.category = category;
        this.name = name;
        this.photo = photo;
        this.price = price;
        this.description = description;
        this.count = 0;
    }
}
