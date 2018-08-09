package me.ms.jpa.udemy28.entity;

public enum ReviewRating {
    ONE("1"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5");

    private String rating;

    ReviewRating(String rating) {
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }
}
