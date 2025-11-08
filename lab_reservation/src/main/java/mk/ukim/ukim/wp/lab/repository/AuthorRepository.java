package mk.ukim.ukim.wp.lab.repository;

import mk.ukim.ukim.wp.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorRepository {

    private static final List<Author> authors = new ArrayList<>();

    static {
        authors.add(new Author(1L, "Ivo", "Andric", "Yugoslavia", "Nobel prize winner"));
        authors.add(new Author(2L, "Fyodor", "Dostoevsky", "Russia", "Russian novelist"));
        authors.add(new Author(3L, "Jane", "Austen", "England", "English novelist"));
    }

    public List<Author> findAll() {
        return authors;
    }

    public Author findById(Long id) {
        return authors.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public static List<Author> getInitialsAuthors(){
        return authors;
    }

}
