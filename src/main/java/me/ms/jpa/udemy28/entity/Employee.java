package me.ms.jpa.udemy28.entity;

import lombok.*;

import javax.persistence.*;

//@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "EmployeeType")
@MappedSuperclass
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public abstract class Employee {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;
    private String name;

    public Employee(String name) {
        this.name = name;
    }
}
