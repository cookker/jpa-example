package me.ms.jpa.udemy28.repository;

import me.ms.jpa.udemy28.entity.Course;
import me.ms.jpa.udemy28.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpqlTest {
    Logger log = LoggerFactory.getLogger(JpqlTest.class);

    @Autowired
    EntityManager em;

    @Test
    public void jpql_typed_test(){
        final TypedQuery<Course> query = em.createNamedQuery("find_all", Course.class);
        final List<Course> resultList = query.getResultList();
        log.info("==>> SELECT c FROM COURSE c RESULT: {}", resultList);
    }

    @Test
    public void jpql_native_test(){
        final Query query = em.createNativeQuery("SELECT * FROM COURSE", Course.class);
        final List<Course> resultList = query.getResultList();
        log.info("==>> NATIVE QUERY RESULT: {}", resultList);
    }

    @Test
    public void jpql_native_with_parameter_test(){
        final Query query = em.createNativeQuery("SELECT * FROM COURSE where id = :id", Course.class);
        query.setParameter("id", 10001L);
        final List<Course> resultList = query.getResultList();
        log.info("==>> NATIVE QUERY RESULT: {}", resultList);
    }

    @Test
    @Transactional
    public void jpql_native_update_test(){
        final Query query = em.createNativeQuery("UPDATE COURSE SET last_updated_date = sysdate()", Course.class);
        final int numberOfEffects = query.executeUpdate();
        log.info("==>> NATIVE QUERY UPDATE ROW RESULT: {}", numberOfEffects);
    }

    @Test
    public void jpql_course_without_student(){
        final TypedQuery<Course> query = em.createQuery("select c from Course c where c.students is empty", Course.class);
        final List<Course> resultList = query.getResultList();
        log.info("@@", resultList);
    }

    @Test
    public void jpql_course_atleast_2_student(){
        final TypedQuery<Course> query = em.createQuery("select c from Course c where size(c.students) >= 2", Course.class);
        final List<Course> resultList = query.getResultList();
        log.info("@@ courseList at least 2 student:{}", resultList);
    }

    @Test
    @Transactional
    public void jpql_Student_with_passport_in_a_certain_pattern(){
        final TypedQuery<Student> query = em.createQuery("select s from Student s where s.passport.number like '%1234%'", Student.class);
        final List<Student> resultList = query.getResultList();
        log.info("@@ stuentList in a certain pattern: {}", resultList);
    }

    @Test
    @Transactional
    public void join(){
        final Query query = em.createQuery("select c,s from Course c LEFT JOIN c.students s");
        final List<Object[]> resultList = query.getResultList();
        log.info("@@ join, resultList.size(): {}", resultList.size());
        for(Object[] result: resultList){
            log.info("@@ course:{}, result:{}", result[0], result[1]);
        }
    }
}
