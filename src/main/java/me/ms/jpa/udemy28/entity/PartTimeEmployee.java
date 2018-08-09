package me.ms.jpa.udemy28.entity;

import lombok.*;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class PartTimeEmployee extends Employee{

    private BigDecimal hourlyWage;

    public PartTimeEmployee(String name, BigDecimal hourlyWage) {
        super(name);
        this.hourlyWage = hourlyWage;
    }
}
