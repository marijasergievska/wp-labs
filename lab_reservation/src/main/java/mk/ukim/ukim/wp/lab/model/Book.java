package mk.ukim.ukim.wp.lab.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Book {
    private Long id;
    String title;
    String genre;
    double averageRating;
    private Author author;

    public Book() {}
    public Book(Long id, String title, String genre, double averageRating, Author author) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.averageRating = averageRating;
        this.author = author;
    }



    public Book(String genre, String title, double averageRating, Author author) {
        this.genre = genre;
        this.title = title;
        this.averageRating = averageRating;
        this.author = author;
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
