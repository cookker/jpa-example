package me.ms.jpa.udemy28.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ms.jpa.udemy28.entity.Course;
import me.ms.jpa.udemy28.entity.Review;
import me.ms.jpa.udemy28.entity.ReviewRating;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CourseRepository {
    private final EntityManager em;

    public Course findById(Long id){
        return em.find(Course.class, id);
    }

    @Transactional
    public void deleteById(Long id){
        em.remove(findById(id));
    }

    @Transactional
    public Course save(Course course){
        if(course.getId() == null){
            em.persist(course);
        }else{
            em.merge(course);
        }
        return course;
    }

    @Transactional
    public void playWithEntityManger(){
        Course course1 = new Course("course1");
        Course course2 = new Course("course2");
        em.persist(course1);
        em.persist(course2);
        em.flush();

        course1.setName("updated course1");
        em.refresh(course1);

        em.flush();
//        course2.setName("updated course2");
//        em.flush();
    }

    @Transactional
    public void addReviewsForCourse(){
        final Course course = findById(10003L);
        log.info("course.getReviews() ==> {}", course.getReviews());

        Review review1 = new Review(ReviewRating.FIVE, "Great Hands-on");
        Review review2 = new Review(ReviewRating.FIVE, "Hatsoff");

        course.addReview(review1);
        review1.setCourse(course);

        course.addReview(review2);
        review2.setCourse(course);

        em.persist(review1);
        em.persist(review2);
    }
}
