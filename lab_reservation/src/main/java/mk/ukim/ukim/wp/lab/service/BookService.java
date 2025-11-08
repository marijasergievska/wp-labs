package mk.ukim.ukim.wp.lab.service;

import mk.ukim.ukim.wp.lab.model.Book;
import mk.ukim.ukim.wp.lab.model.BookReservation;

import java.util.List;
import java.util.Optional;

public interface BookService {

        List<Book> listAll();

        Optional<Book> findById(Long id);


        Book save(String title, String genre, Double averageRating, Long authorId);

        Book edit(Long id, String title, String genre, Double averageRating, Long authorId);

        void deleteById(Long id);
        List<Book> searchBooks(String text, Double rating);

}

