package mk.ukim.ukim.wp.lab.service;

import mk.ukim.ukim.wp.lab.bootstrap.DataHolder;
import mk.ukim.ukim.wp.lab.model.Book;
import mk.ukim.ukim.wp.lab.model.BookReservation;
import mk.ukim.ukim.wp.lab.repository.BookReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookReservationServiceImpl implements BookReservationService {

    private final BookReservationRepository reservationRepository;

    public BookReservationServiceImpl(BookReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public BookReservation placeReservation(String bookTitle, String readerName, String readerAddress, Long numberOfCopies) {
        BookReservation reservation = new BookReservation(bookTitle, readerName, readerAddress, numberOfCopies);
        return reservationRepository.save(reservation);
    }

    @Override
    public List<BookReservation> findByBookTitle(String bookTitle) {
        return List.of();
    }

//    @Override
//    public List<BookReservation> findByBookTitle(String bookTitle) {
//        return DataHolder.reservations.stream()
//                .filter(r -> r.getBookTitle() != null && r.getBookTitle().equalsIgnoreCase(bookTitle))
//                .collect(Collectors.toList());
//    }

}
