package mk.ukim.ukim.wp.lab.service;

import mk.ukim.ukim.wp.lab.model.Author;

import java.util.List;

public interface AuthorService {


    List<Author> findAll();

    Author findById(Long id);

}
