package mk.ukim.ukim.wp.lab.service;

import mk.ukim.ukim.wp.lab.model.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {

        List<Book> listAll();

        Optional<Book> findById(Long id);

        Book save(String title, String genre, Double averageRating,
                  String authorName, String authorSurname);

        Book edit(Long id, String title, String genre, Double averageRating,
                  String authorName, String authorSurname);

        void deleteById(Long id);

        void incrementLikes(Long id);

        List<Book> listBooksByAuthor(Long authorId);
        List<Book> searchBooks(String text, Double rating);

}
