package me.ms.jpa.udemy28.repository;

import lombok.extern.slf4j.Slf4j;
import me.ms.jpa.udemy28.Udemy28Application;
import me.ms.jpa.udemy28.entity.Course;
import me.ms.jpa.udemy28.entity.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Udemy28Application.class)
@Slf4j
public class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    EntityManager em;

    @Test
    public void findById_basic(){
        final Course course = courseRepository.findById(10001L);
        assertEquals("jpa ji gyup", course.getName());
    }

    @Test
    @DirtiesContext
    public void deleteById_test(){
        courseRepository.deleteById(10002L);
        assertNull(courseRepository.findById(10002L));
    }

    @Test
    @DirtiesContext
    public void save_test(){
        final Course course = courseRepository.findById(10001L);
        course.setName("updatedName");
        courseRepository.save(course);
        final Course course1 = courseRepository.save(course);
        assertEquals("updatedName", course1.getName());
    }

    @Test
    @Transactional
    public void retrieveReviewsForCourse(){
        final Course course = courseRepository.findById(10001L);
        log.info("==> course.getReviews() : {}", course.getReviews()); //기본 fetchType 은 lazy이다.
    }

    @Test
    @Transactional
    public void retrieveCourseForReview(){
        final Review review = em.find(Review.class, 50001L);
        log.info("==> review.getCourse() : {}", review.getCourse()); //ManyToOne 기본 FetchType은 Eager
    }
}