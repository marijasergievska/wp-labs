package mk.ukim.ukim.wp.lab.web.controller;

import mk.ukim.ukim.wp.lab.model.Book;
import mk.ukim.ukim.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // ✔ SEARCH + LIST — единствена валидна /books GET рута
    @GetMapping("/books")
    public String getBooksPage(@RequestParam(required = false) String text,
                               @RequestParam(required = false) Double rating,
                               @RequestParam(required = false) String error,
                               Model model) {

        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Book> books;

        if ((text != null && !text.isEmpty()) || rating != null) {
            books = bookService.searchBooks(text, rating);
        } else {
            books = bookService.listAll();
        }

        model.addAttribute("books", books);
        model.addAttribute("text", text);
        model.addAttribute("rating", rating);

        return "listBooks";
    }

    // Like
    @PostMapping("/books/likes/{id}")
    public String likeBook(@PathVariable Long id) {
        bookService.incrementLikes(id);
        return "redirect:/books";
    }

    // Add Book
    @PostMapping("/books/add")
    public String addBook(@RequestParam String title,
                          @RequestParam String genre,
                          @RequestParam Double averageRating,
                          @RequestParam String authorName,
                          @RequestParam String authorSurname) {

        bookService.save(title, genre, averageRating, authorName, authorSurname);
        return "redirect:/books";
    }

    // Edit Book
    @PostMapping("/books/edit/{id}")
    public String editBook(@PathVariable Long id,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam String authorName,
                           @RequestParam String authorSurname) {

        bookService.edit(id, title, genre, averageRating, authorName, authorSurname);
        return "redirect:/books";
    }

    // Delete Book
    @PostMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }

    // Add Form
    @GetMapping("/books/book-form")
    public String getAddBookPage(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("isEdit", false);
        return "book-form";
    }

    // Edit Form
    @GetMapping("/books/book-form/{id}")
    public String getEditBookForm(@PathVariable Long id, Model model) {

        Book book = bookService.findById(id).orElse(null);

        if (book == null) {
            return "redirect:/books?error=BookNotFound";
        }

        model.addAttribute("book", book);
        model.addAttribute("isEdit", true);
        model.addAttribute("authorName", book.getAuthor().getName());
        model.addAttribute("authorSurname", book.getAuthor().getSurname());

        return "book-form";
    }
}
