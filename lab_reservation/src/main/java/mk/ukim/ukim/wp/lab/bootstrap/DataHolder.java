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
    public static List<BookReservation> reservations = null;


    @PostConstruct
    public void init() {
        reservations = new ArrayList<>();

    }
}
