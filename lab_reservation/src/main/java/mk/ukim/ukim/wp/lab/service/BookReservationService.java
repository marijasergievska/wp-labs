package mk.ukim.ukim.wp.lab.service;

import mk.ukim.ukim.wp.lab.model.BookReservation;

import java.util.List;


public interface BookReservationService {
    BookReservation placeReservation(String bookTitle, String readerName, String readerAddress, Long numberOfCopies);
    List<BookReservation> findByBookTitle(String bookTitle);
}
