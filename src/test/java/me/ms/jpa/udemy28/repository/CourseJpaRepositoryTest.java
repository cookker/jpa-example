package me.ms.jpa.udemy28.repository;

import lombok.extern.slf4j.Slf4j;
import me.ms.jpa.udemy28.entity.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CourseJpaRepositoryTest {

    @Autowired
    CourseJpaRepository courseJpaRepository;

    @Test
    public void findById(){
        final Optional<Course> optionalCourse = courseJpaRepository.findById(10001L);
        final long actualId = optionalCourse.map(Course::getId).orElse(0L);
        assertEquals(10001L, actualId);
    }

    @Test
    public void save(){
        final Course course = new Course("hmm");
        courseJpaRepository.save(course);
        course.setName("updated hmm");
        courseJpaRepository.save(course);

        log.info("courseJpaRepository.findAll() ==> {}", courseJpaRepository.findAll());
        log.info("courseJpaRepository.count() ==> {}", courseJpaRepository.count());

        final Sort sort = Sort.by(Sort.Direction.ASC, "name");
        final List<Course> courseList = courseJpaRepository.findAll(sort);
        log.info("name sorted courseList: {}", courseList);
    }

    @Test
    public void paging(){
        final Page<Course> coursePage = courseJpaRepository.findAll(PageRequest.of(0, 3));
        log.info("coursePage: {}", coursePage);
        final Pageable secondPageable = coursePage.nextPageable();
        final Page<Course> secondPage = courseJpaRepository.findAll(secondPageable);
        log.info("secondPage: {}", secondPage.getContent());
    }

    @Test
    public void findByName(){
        final List<Course> courseList = courseJpaRepository.findByName("Dummy1");
        log.info("findByName, courseList:{} ", courseList);

        final List<Course> courseBy100Steps = courseJpaRepository.findCourseBy100Steps();
        log.info("courseBy100Steps:{} ", courseBy100Steps);

        final List<Course> courseBy100StepsUsingNativeQuery = courseJpaRepository.findCourseBy100StepsUsingNativeQuery();
        log.info("courseBy100StepsUsingNativeQuery:{} ", courseBy100StepsUsingNativeQuery);

        final List<Course> courseBy100StepsUsingNamedQuery = courseJpaRepository.findCourseBy100StepsUsingNamedQuery();
        log.info("courseBy100StepsUsingNamedQuery:{} ", courseBy100StepsUsingNamedQuery);
    }

}