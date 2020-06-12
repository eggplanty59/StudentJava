package ru.studentjava.enity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="student")
public class Student {

    public static String TYPE_NAME = "Студент";

    public Student(Integer id, String name, String passport) {
        this.id = id;
        this.name = name;
        this.passport = passport;
    }

    public Student() {
    }

    @Id
    @Column(name= "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "passport")
    private String passport;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }
}

