package mk.ukim.ukim.wp.lab.model;

import jakarta.persistence.*;


@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String genre;
    private double averageRating;
    @ManyToOne
    private Author author;
    private int likeCounter=0;

    public Book() {}
    public Book(Long id, String title, String genre, double averageRating, Author author) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.averageRating = averageRating;
        this.author = author;
        this.likeCounter=0;
    }

    public int getLikeCounter() {
        return likeCounter;
    }

    public void setLikeCounter(int likeCounter) {
        this.likeCounter = likeCounter;
    }

    public Book(String title, String genre, double averageRating, Author author) {
        this.genre = genre;
        this.title = title;
        this.averageRating = averageRating;
        this.author = author;
        this.likeCounter=0;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public Author getAuthor() { return author; }
    public void setAuthor(Author author) { this.author = author; }
}
