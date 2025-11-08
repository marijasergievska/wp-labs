package mk.ukim.ukim.wp.lab.service;


import mk.ukim.ukim.wp.lab.bootstrap.DataHolder;
import mk.ukim.ukim.wp.lab.model.Book;

import mk.ukim.ukim.wp.lab.repository.InMemoryBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BookServiceImpl implements BookService {
    private final InMemoryBookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceImpl(InMemoryBookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }


    @Override
    public Book save(String title, String genre, Double averageRating, Long authorId) {
        Book book = new Book();
        book.setTitle(title);
        book.setGenre(genre);
        book.setAverageRating(averageRating);
        book.setAuthor(authorService.findById(authorId));
        return bookRepository.save(book);
    }

    @Override
    public Book edit(Long id, String title, String genre, Double averageRating, Long authorId) {
        Book book = findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setTitle(title);
        book.setGenre(genre);
        book.setAverageRating(averageRating);
        book.setAuthor(authorService.findById(authorId));
        return bookRepository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return bookRepository.searchBooks(text, rating);
    }


}
