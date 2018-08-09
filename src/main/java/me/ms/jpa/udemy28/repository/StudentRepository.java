package me.ms.jpa.udemy28.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ms.jpa.udemy28.entity.Course;
import me.ms.jpa.udemy28.entity.Passport;
import me.ms.jpa.udemy28.entity.Student;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
@Slf4j
public class StudentRepository {
    private final EntityManager em;

    public Student findById(Long id){
        return em.find(Student.class, id);
    }

    @Transactional
    public void deleteById(Long id){
        em.remove(findById(id));
    }

    @Transactional
    public Student save(Student student){
        if(student.getId() == null){
            em.persist(student);
        }else{
            em.merge(student);
        }
        return student;
    }

    @Transactional
    public void saveWithPassport(){
        Passport passport = new Passport("Z123456");
        em.persist(passport);

        Student mike = new Student("MIKE");
        mike.setPassport(passport);
        em.persist(mike);
    }

    @Transactional
    public void insertStudentAndCourse(){
        Student student = new Student("홍길동");
        Course course = new Course("도적질");

        em.persist(student);
        em.persist(course);

        student.addCourse(course);
        course.addStudent(student);
        em.persist(student); //?
    }
}
