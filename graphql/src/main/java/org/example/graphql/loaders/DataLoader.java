package org.example.graphql.loaders;

import org.example.graphql.domain.Author;
import org.example.graphql.domain.Book;
import org.example.graphql.domain.Publisher;
import org.example.graphql.services.AuthorService;
import org.example.graphql.services.BookService;
import org.example.graphql.services.PublisherService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final BookService bookService;
    private final PublisherService publisherService;
    private final AuthorService authorService;

    public DataLoader(BookService bookService, PublisherService publisherService, AuthorService authorService) {
        this.bookService = bookService;
        this.publisherService = publisherService;
        this.authorService = authorService;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher poli=new Publisher("Politehnica Press");
        Author joe=new Author("Joe Java");
        Author mary=new Author("Mary PHP");
        Book b1=new Book("Programming in Java","isbn123",joe,poli);
        Book b2=new Book("Programming in PHP","isbn124",mary,poli);
        bookService.saveBook(b1);
        bookService.saveBook(b2);
    }
}
