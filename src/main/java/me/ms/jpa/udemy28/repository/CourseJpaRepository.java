package me.ms.jpa.udemy28.repository;

import me.ms.jpa.udemy28.entity.Course;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "courses")
public interface CourseJpaRepository extends JpaRepository<Course, Long> {
    List<Course> findByName(String name);
    @Query("SELECT c FROM Course c WHERE c.name like '%f100 Steps'")
    List<Course> findCourseBy100Steps();

    @Query(value = "SELECT * FROM Course c WHERE c.name like '%100 Steps'", nativeQuery = true)
    List<Course> findCourseBy100StepsUsingNativeQuery();

    @Query(name = "find_all_100step")
    List<Course> findCourseBy100StepsUsingNamedQuery();

    @EntityGraph(attributePaths = "students")
    @Query("SELECT c FROM Course c")
    List<Course> findAllCoursesWithEntityGraph();
}
