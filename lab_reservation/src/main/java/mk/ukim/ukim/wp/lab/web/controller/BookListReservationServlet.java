package mk.ukim.ukim.wp.lab.web.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.ukim.wp.lab.model.BookReservation;
import mk.ukim.ukim.wp.lab.service.BookReservationService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name="list-book-by-name",urlPatterns = "/bookReservationList")
public class BookListReservationServlet extends HttpServlet {

    private final BookReservationService bookReservationService;
    private final SpringTemplateEngine springTemplateEngine;

    public BookListReservationServlet(BookReservationService bookReservationService, SpringTemplateEngine springTemplateEngine) {
        this.bookReservationService = bookReservationService;
        this.springTemplateEngine = springTemplateEngine;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");

        List<BookReservation> reservations = bookReservationService.findByBookTitle(title);

        WebContext context = new WebContext(
                JakartaServletWebApplication.buildApplication(getServletContext())
                        .buildExchange(req, resp)
        );

        context.setVariable("bookTitle", title);
        context.setVariable("reservationsName", reservations);

        springTemplateEngine.process("listByName.html", context, resp.getWriter());
    }
}
