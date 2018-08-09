package me.ms.jpa.udemy28.repository;

import lombok.extern.slf4j.Slf4j;
import me.ms.jpa.udemy28.entity.Address;
import me.ms.jpa.udemy28.entity.Passport;
import me.ms.jpa.udemy28.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    EntityManager em;

    @Test
    @Transactional
    public void retrieveStudentWithPassportDetails(){
        final Student student = em.find(Student.class, 20001L);
        System.out.printf("##student" + student);
        System.out.printf("##student.getPassport" + student.getPassport());
    }

    @Test
    @Transactional
    public void someTest(){
        final Student student = em.find(Student.class, 20001L);
        final Passport passport = student.getPassport();
        passport.setNumber("E112233");
        student.setName("name - updated");

        final Student student1 = em.find(Student.class, 20001L);
        System.out.println(student1);
    }

    @Test
    @Transactional
    public void 학생과여권검색(){
        final Student student = em.find(Student.class, 20001L);
        final Passport passport = student.getPassport();
        System.out.println("##" + passport);
    }

    @Test
    public void 여권과학생검색(){
        final Passport passport = em.find(Passport.class, 40001L);
        final Student student = passport.getStudent();
        System.out.println("##" + student);
    }

    @Test
    @Transactional
    public void retrieveStudentsAndCourse(){
        final Student student = em.find(Student.class, 20001L);
        log.info("==> student             : {}", student);
        log.info("==> student.getCourses(): {}", student.getCourses());
    }

    @Test
    @Transactional
    public void setAddressTest(){
        final Student student = em.find(Student.class, 20001L);
        student.setAddress(new Address("amsaro 11", "sangamro11", "seoul"));
        em.flush();
        log.info("==> student             : {}", student);
        log.info("==> student             : {}", student.getPassport());
    }
}