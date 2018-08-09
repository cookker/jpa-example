package me.ms.jpa.udemy28.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "course")
public class Review {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private ReviewRating rating;

    @ManyToOne
    private Course course;

    @Column(nullable = false)
    private String description;

    public Review(ReviewRating rating, String description) {
        this.rating = rating;
        this.description = description;
    }
}
