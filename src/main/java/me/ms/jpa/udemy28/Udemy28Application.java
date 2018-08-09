package me.ms.jpa.udemy28;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ms.jpa.udemy28.entity.Course;
import me.ms.jpa.udemy28.entity.FullTimeEmployee;
import me.ms.jpa.udemy28.entity.PartTimeEmployee;
import me.ms.jpa.udemy28.repository.CourseRepository;
import me.ms.jpa.udemy28.repository.EmployeeRepository;
import me.ms.jpa.udemy28.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class Udemy28Application {
    @NonNull
    private CourseRepository courseRepository;
    @NonNull
    private StudentRepository studentRepository;
    @NonNull
    private EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(Udemy28Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(){
        return args -> {
//            final Course course = courseRepository.findById(10001L);
//            log.info("course: {}", course);

//            courseRepository.save(new Course("insert test"));
//            courseRepository.playWithEntityManger();
//            studentRepository.saveWithPassport();
//            courseRepository.addReviewsForCourse();
//            studentRepository.insertStudentAndCourse();

//            employeeRepository.insert(new PartTimeEmployee("갑을병정", BigDecimal.valueOf(999L)));
//            employeeRepository.insert(new FullTimeEmployee("무기경신", BigDecimal.valueOf(10000L)));
//            log.info("##employeeRepository.findAll() ==> {}", employeeRepository.findAllFullTimeEmployee());
//            log.info("##employeeRepository.findAll() ==> {}", employeeRepository.findAllPartTimeEmployee());
        };
    }
}
