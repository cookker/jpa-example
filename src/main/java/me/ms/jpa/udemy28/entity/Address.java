package me.ms.jpa.udemy28.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Address {
    private String line1;
    private String line2;
    private String city;
}
