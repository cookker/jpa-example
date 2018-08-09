package me.ms.jpa.udemy28.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "student")
public class Passport {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private String number;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "passport")
    private Student student;

    public Passport(String number) {
        this.number = number;
    }
}
