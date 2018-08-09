package me.ms.jpa.udemy28.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"reviews", "students"})
@NamedQueries(value = {
        @NamedQuery(name = "find_all_100step", query = "SELECT c FROM Course c WHERE name like '%100 Steps'"),
        @NamedQuery(name = "query_get_all_courses", query = "SELECT c FROM Course c"),
        @NamedQuery(name = "query_get_all_courses_join_fetch", query = "SELECT c FROM Course c JOIN FETCH c.students s")
})

@Cacheable
@SQLDelete(sql = "update course set is_deleted = true where id = ?")
@Where(clause = "is_deleted = false")
@Slf4j
public class Course {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "course")
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(mappedBy = "courses")
    @Setter(AccessLevel.NONE)
    @JsonIgnore
    private List<Student> students = new ArrayList<>();

    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;

    @CreationTimestamp
    private LocalDateTime createdDate;

    private boolean isDeleted;

    public Course(String name) {
        this.name = name;
    }

    public void addReview(Review review){
        this.reviews.add(review);
    }

    public void remove(Review review){
        this.reviews.remove(review);
    }

    public void addStudent(Student student){
        this.students.add(student);
    }

    @PreRemove
    public void deleteCourse(){
        log.info("Setting: course is delele.");
        this.isDeleted = true;
    }
}
