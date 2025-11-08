package mk.ukim.ukim.wp.lab.repository;

import mk.ukim.ukim.wp.lab.bootstrap.DataHolder;
import mk.ukim.ukim.wp.lab.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryBookRepository implements BookRepository {

    private final List<Book> books = new ArrayList<>();
    private Long nextId = 1L;

    public InMemoryBookRepository() {
        // Наполни ја листата од DataHolder при старт
        books.addAll(DataHolder.books);
        nextId = books.stream()
                .mapToLong(Book::getId)
                .max()
                .orElse(0L) + 1;
    }

    public List<Book> findAll() {
        return books;
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return books.stream()
                .filter(b -> (text == null || text.isEmpty() || b.getTitle().toLowerCase().contains(text.toLowerCase())))
                .filter(b -> rating == null || b.getAverageRating() >= rating)
                .collect(Collectors.toList());
    }


    public Optional<Book> findById(Long id) {
        return books.stream().filter(b -> b.getId().equals(id)).findFirst();
    }

    public Book save(Book book) {
        if (book.getId() == null) {
            book.setId(nextId++);
            books.add(book);
        } else {
            books.removeIf(b -> b.getId().equals(book.getId()));
            books.add(book);
        }
        return book;
    }

    public void deleteById(Long id) {
        books.removeIf(b -> b.getId().equals(id));
    }

}
