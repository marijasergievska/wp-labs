package mk.ukim.ukim.wp.lab.web.servlet;

import mk.ukim.ukim.wp.lab.model.Book;
import mk.ukim.ukim.wp.lab.service.AuthorService;
import mk.ukim.ukim.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;



@Controller
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

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
            books = bookService.searchBooks(text, rating != null ? rating : 0.0);
        } else {
            books = bookService.listAll();
        }

        model.addAttribute("books", books);
        model.addAttribute("text", text);
        model.addAttribute("rating", rating);
        return "listBooks";
    }


    @PostMapping("/books/edit/{id}")
    public String editBook(@PathVariable Long id,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId) {

        bookService.edit(id, title, genre, averageRating, authorId);
        return "redirect:/books";
    }


    @PostMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }


    @GetMapping("/books/book-form")
    public String getAddBookPage(Model model) {
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("book", new Book());
        model.addAttribute("isEdit", false);
        return "book-form";
    }

    @GetMapping("/books/book-form/{id}")
    public String getEditBookForm(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id)
                .orElse(null);

        if (book == null) {
            return "redirect:/books?error=BookNotFound";
        }

        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("book", book);
        model.addAttribute("isEdit", true);
        return "book-form";
    }
}
