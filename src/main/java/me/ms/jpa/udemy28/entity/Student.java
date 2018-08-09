package me.ms.jpa.udemy28.entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "courses")
public class Student {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    private Passport passport;

    @Embedded
    private Address address;

    @ManyToMany
    @Setter(AccessLevel.NONE)
    @JoinTable(name = "STUDENT_COURSE",
    joinColumns = @JoinColumn(name = "STUDENT_ID"),
    inverseJoinColumns = @JoinColumn(name="COURSE_ID"))
    private List<Course> courses = new ArrayList<>();

    public Student(String name) {
        this.name = name;
    }

    public void addCourse(Course course){
        this.courses.add(course);
    }
}
