package me.ms.jpa.udemy28.repository;

import lombok.extern.slf4j.Slf4j;
import me.ms.jpa.udemy28.entity.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CriteriaQueryTest {
    @Autowired
    EntityManager entityManager;

    @Test
    public void all_course_criteria(){
        //"SELECT c FROM Course c"

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> courseCriteriaQuery = criteriaBuilder.createQuery(Course.class);

        final Root<Course> from = courseCriteriaQuery.from(Course.class);

        final TypedQuery<Course> query = entityManager.createQuery(courseCriteriaQuery.select(from));
        final List<Course> resultList = query.getResultList();

        log.info("@@ courseList = {}, using criteria", resultList);
    }

    @Test
    public void all_course_having_100steps(){
        //"SELECT c FROM Course c WHERE name like '%100 Steps'"

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);

        final Root<Course> from = criteriaQuery.from(Course.class);
        final Predicate like = criteriaBuilder.like(from.get("name"), "%100 Steps");
        criteriaQuery.where(like);
        final TypedQuery<Course> query = entityManager.createQuery(criteriaQuery.select(from));
        final List<Course> resultList = query.getResultList();
        log.info("@@ 100 Steps courseList = {}, using criteria", resultList);
    }

    @Test
    public void all_course_without_students(){
        //SELECT c FROM Course c WHERE c.students is empty
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);

        final Root<Course> from = criteriaQuery.from(Course.class);
        final Predicate studentsIsEmpty = criteriaBuilder.isEmpty(from.get("students"));
        criteriaQuery.where(studentsIsEmpty);
        final TypedQuery<Course> query = entityManager.createQuery(criteriaQuery.select(from));
        final List<Course> resultList = query.getResultList();

        log.info("@@ not exists students course list = {}, using criteria", resultList);
    }

    @Test
    public void join(){
        //"SELECT c FROM Course c join c.students s"

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
        final Root<Course> from = criteriaQuery.from(Course.class);
        final Join<Object, Object> students = from.join("students");

        final TypedQuery<Course> query = entityManager.createQuery(criteriaQuery.select(from));
        final List<Course> resultList = query.getResultList();

        log.info("@@ Course join Student : {}", resultList);

        /*
         *  [Course(id=10001, name=JPA in 50 Steps, lastUpdatedDate=2018-08-07T22:19:29.811, createdDate=2018-08-07T22:19:29.811),
         *  Course(id=10001, name=JPA in 50 Steps, lastUpdatedDate=2018-08-07T22:19:29.811, createdDate=2018-08-07T22:19:29.811),
         *  Course(id=10001, name=JPA in 50 Steps, lastUpdatedDate=2018-08-07T22:19:29.811, createdDate=2018-08-07T22:19:29.811),
         *  Course(id=10003, name=Spring Boot in 100 Steps, lastUpdatedDate=2018-08-07T22:19:29.814, createdDate=2018-08-07T22:19:29.814)]
         * */
    }

    @Test
    public void left_join(){
        //"SELECT c FROM Course c join c.students s"

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
        final Root<Course> from = criteriaQuery.from(Course.class);
        final Join<Object, Object> students = from.join("students", JoinType.LEFT);

        final TypedQuery<Course> query = entityManager.createQuery(criteriaQuery.select(from));
        final List<Course> resultList = query.getResultList();

        log.info("@@ Course left join Student : {}", resultList);

        /*
         *  [Course(id=10001, name=JPA in 50 Steps, lastUpdatedDate=2018-08-07T22:19:29.811, createdDate=2018-08-07T22:19:29.811),
         *  Course(id=10001, name=JPA in 50 Steps, lastUpdatedDate=2018-08-07T22:19:29.811, createdDate=2018-08-07T22:19:29.811),
         *  Course(id=10001, name=JPA in 50 Steps, lastUpdatedDate=2018-08-07T22:19:29.811, createdDate=2018-08-07T22:19:29.811),
         *  Course(id=10003, name=Spring Boot in 100 Steps, lastUpdatedDate=2018-08-07T22:19:29.814, createdDate=2018-08-07T22:19:29.814)]
         * */
    }
}
