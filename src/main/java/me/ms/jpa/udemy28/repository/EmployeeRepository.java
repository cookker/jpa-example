package me.ms.jpa.udemy28.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ms.jpa.udemy28.entity.Employee;
import me.ms.jpa.udemy28.entity.FullTimeEmployee;
import me.ms.jpa.udemy28.entity.PartTimeEmployee;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class EmployeeRepository {
    private final EntityManager em;

    public Employee findById(Long id){
        return em.find(Employee.class, id);
    }

    @Transactional
    public void insert(Employee employee){
        em.persist(employee);
    }

    public List<Employee> findAll(){
        return em.createQuery("select e from Employee e", Employee.class)
                .getResultList();
    }

    public List<FullTimeEmployee> findAllFullTimeEmployee(){
        return em.createQuery("select e from FullTimeEmployee e", FullTimeEmployee.class)
                .getResultList();
    }

    public List<PartTimeEmployee> findAllPartTimeEmployee(){
        return em.createQuery("select e from PartTimeEmployee e", PartTimeEmployee.class)
                .getResultList();
    }
}
