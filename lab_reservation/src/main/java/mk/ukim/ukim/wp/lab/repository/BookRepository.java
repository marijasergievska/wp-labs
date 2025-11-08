package mk.ukim.ukim.wp.lab.repository;

import mk.ukim.ukim.wp.lab.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<Book> findAll();

    List<Book> searchBooks(String text, Double rating);

    Optional<Book> findById(Long id);

    void deleteById(Long id);

    Book save(Book book);

}
