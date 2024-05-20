package org.example.graphql.controllers;

import org.example.graphql.domain.Author;
import org.example.graphql.domain.Book;
import org.example.graphql.domain.Publisher;
import org.example.graphql.services.AuthorService;
import org.example.graphql.services.BookService;
import org.example.graphql.services.PublisherService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;
    private final PublisherService publisherService;
    private final AuthorService authorService;

    public BookController(BookService bookService, PublisherService publisherService, AuthorService authorService) {
        this.bookService = bookService;
        this.publisherService = publisherService;
        this.authorService = authorService;
    }

    @QueryMapping
    public Book bookById(@Argument Long id) {
        return bookService.getBookById(id).orElse(null);
    }

    @QueryMapping
    public List<Book> allBooks() {
        return bookService.getAllBooks();
    }

    @MutationMapping
    public Book addBook(@Argument String title, @Argument String isbn, @Argument Long authorId, @Argument Long publisherId) {
        Author author = authorService.getAuthorById(authorId).orElse(null);
        Publisher publisher = publisherService.getPublisherById(publisherId).orElse(null);
        if (author == null || publisher == null) {
           return null;
        }
        Book newBook = new Book();
        newBook.setTitle(title);
        newBook.setIsbn(isbn);
        newBook.setAuthor(author);
        newBook.setPublisher(publisher);
        return bookService.saveBook(newBook);
        }

    @MutationMapping
    public Book updateBook(@Argument Long id, @Argument String title, @Argument String isbn) {
        Book bookToUpdate = bookService.getBookById(id).orElse(null);
        if (bookToUpdate == null) {
            return null;
        }
        if (title != null) {
            bookToUpdate.setTitle(title);
        }
        if (isbn != null) {
            bookToUpdate.setIsbn(isbn);
        }
        return bookService.saveBook(bookToUpdate);
    }

    @MutationMapping
    public void deleteBook(@Argument Long id) {
        System.out.println(id);
        bookService.deleteBook(id);
    }
}
