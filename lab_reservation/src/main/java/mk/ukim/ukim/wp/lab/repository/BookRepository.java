package mk.ukim.ukim.wp.lab.repository;

import mk.ukim.ukim.wp.lab.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAll();

//    List<Book> searchBooks(String text, Double rating);

    Optional<Book> findById(Long id);

    void deleteById(Long id);

    Book save(Book book);


    List<Book> findByTitleContainingIgnoreCaseAndAverageRatingGreaterThanEqual(
            String text, Double rating);

    List<Book> findAllByAuthor_Id(Long authorId);

}
