package com.timesheet.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Teachers {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String fio;
    private String photo;
    private byte experience;
    private String category;
    private String tel;

    public Teachers(String photo) {
        this.fio = "ФИО";
        this.photo = photo;
        this.experience = 1;
        this.category = "Не выбрана";
        this.tel = "";
    }
}
