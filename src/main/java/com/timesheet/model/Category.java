package com.timesheet.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Category {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Subjects> subjects;

    public Category(String name) {
        this.name = name;
        subjects = new ArrayList<>();
    }

    public void addSubject(Subjects subject) {
        subjects.add(subject);
        subject.setCategory(this);
    }

    public void removeSubject(Subjects subject) {
        subjects.remove(subject);
        subject.setCategory(null);
    }
}
