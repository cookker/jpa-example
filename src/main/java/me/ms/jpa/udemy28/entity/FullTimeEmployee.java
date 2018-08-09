package me.ms.jpa.udemy28.entity;

import lombok.*;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class FullTimeEmployee extends Employee{
    private BigDecimal salary;

    public FullTimeEmployee(String name, BigDecimal salary) {
        super(name);
        this.salary = salary;
    }
}
