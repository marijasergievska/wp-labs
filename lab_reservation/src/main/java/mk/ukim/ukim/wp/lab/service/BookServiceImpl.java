package mk.ukim.ukim.wp.lab.service;

import mk.ukim.ukim.wp.lab.model.Author;
import mk.ukim.ukim.wp.lab.model.Book;
import mk.ukim.ukim.wp.lab.repository.AuthorRepository;
import mk.ukim.ukim.wp.lab.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    private Author getOrCreateAuthor(String name, String surname) {
        return authorRepository.findByNameAndSurname(name, surname)
                .orElseGet(() -> authorRepository.save(new Author(name, surname)));
    }

    @Override
    public Book save(String title, String genre, Double averageRating,
                     String authorName, String authorSurname) {

        Author author = getOrCreateAuthor(authorName, authorSurname);

        Book book = new Book(title, genre, averageRating, author);
        return bookRepository.save(book);
    }

    @Override
    public Book edit(Long id, String title, String genre, Double averageRating,
                     String authorName, String authorSurname) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Author author = getOrCreateAuthor(authorName, authorSurname);

        book.setTitle(title);
        book.setGenre(genre);
        book.setAverageRating(averageRating);
        book.setAuthor(author);

        return bookRepository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void incrementLikes(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setLikeCounter(book.getLikeCounter() + 1);
        bookRepository.save(book);
    }

    @Override
    public List<Book> listBooksByAuthor(Long authorId) {
        return bookRepository.findAllByAuthor_Id(authorId);
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {

        if (text == null) text = "";

        if (rating == null) rating = 0.0;

        return bookRepository
                .findByTitleContainingIgnoreCaseAndAverageRatingGreaterThanEqual(text, rating);
    }

}
