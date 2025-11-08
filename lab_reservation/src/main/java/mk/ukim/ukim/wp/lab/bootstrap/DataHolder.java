package mk.ukim.ukim.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.ukim.wp.lab.model.Author;
import mk.ukim.ukim.wp.lab.model.Book;
import mk.ukim.ukim.wp.lab.model.BookReservation;
import mk.ukim.ukim.wp.lab.repository.AuthorRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Book> books = null;
    public static List<BookReservation> reservations = null;


    @PostConstruct
    public void init() {
        books = new ArrayList<>();
        reservations = new ArrayList<>();
        List<Author> authors = AuthorRepository.getInitialsAuthors();

        books.add(new Book(1L, "The Great Gatsby", "Novel", 4.3, authors.get(0)));
        books.add(new Book(2L, "Crime and Punishment", "Philosophical novel", 4.7, authors.get(1)));
        books.add(new Book(3L, "Pride and Prejudice", "Romance", 4.5, authors.get(2)));
        books.add(new Book(4L, "To Kill a Mockingbird", "Novel", 4.6, authors.get(0)));
        books.add(new Book(5L, "1984", "Dystopia", 4.4, authors.get(1)));
        books.add(new Book(6L, "Emma", "Novel", 4.1, authors.get(2)));
        books.add(new Book(7L, "War and Peace", "Historical", 4.2, authors.get(1)));
        books.add(new Book(8L, "Sense and Sensibility", "Romance", 3.9, authors.get(2)));
        books.add(new Book(9L, "Animal Farm", "Satire", 4.0, authors.get(1)));
        books.add(new Book(10L, "The Sun Also Rises", "Novel", 3.8, authors.get(0)));

    }
}
