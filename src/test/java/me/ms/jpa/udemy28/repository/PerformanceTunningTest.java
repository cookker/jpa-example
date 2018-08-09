package me.ms.jpa.udemy28.repository;

import lombok.extern.slf4j.Slf4j;
import me.ms.jpa.udemy28.entity.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PerformanceTunningTest {
    @Autowired
    EntityManager em;

    @Autowired
    CourseJpaRepository courseJpaRepository;

    @Test
    @Transactional
    public void createJpaNPlusOneProblem(){
        final List<Course> courses = em.createNamedQuery("query_get_all_courses", Course.class).getResultList();
        AtomicInteger counter = new AtomicInteger();
        courses.forEach(c -> log.info("count:{}, course:{}, student:{}", counter.incrementAndGet(), c, c.getStudents()));
    }

    @Test
    @Transactional
    public void solvingNPlusOneProblem_EntityGraph(){
        final EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
        final Subgraph<Object> students = entityGraph.addSubgraph("students");

        final List<Course> courses = em.createNamedQuery("query_get_all_courses", Course.class)
                .setHint("javax.persistence.loadgraph", entityGraph)
                .getResultList();
        AtomicInteger counter = new AtomicInteger();
        courses.forEach(c -> log.info("count:{}, course:{}, student:{}", counter.incrementAndGet(), c, c.getStudents()));
    }

    @Test
    @Transactional
    public void solvingNPlusOneProblem_EntityGraphAnnotation(){
        final List<Course> courses = courseJpaRepository.findAllCoursesWithEntityGraph();
        AtomicInteger counter = new AtomicInteger();
        courses.forEach(c -> log.info("count:{}, course:{}, student:{}", counter.incrementAndGet(), c, c.getStudents()));
    }

    @Test
    @Transactional
    public void solvingNPlusOneProblem_JoinFetch(){
        final List<Course> courses = em.createNamedQuery("query_get_all_courses_join_fetch", Course.class)
                .getResultList();
        AtomicInteger counter = new AtomicInteger();
        courses.forEach(c -> log.info("count:{}, course:{}, student:{}", counter.incrementAndGet(), c, c.getStudents()));
    }
}
