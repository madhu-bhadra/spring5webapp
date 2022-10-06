package springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springframework.spring5webapp.domain.Author;
import springframework.spring5webapp.domain.Book;
import springframework.spring5webapp.domain.Publisher;
import springframework.spring5webapp.repositories.AuthorRepository;
import springframework.spring5webapp.repositories.BookRepository;
import springframework.spring5webapp.repositories.PublisherRepository;

import java.sql.SQLOutput;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Starting BootStrap");

        Publisher mdv = new Publisher();
        mdv.setName("MDV Publisher");
        mdv.setAddress_line_1("123 Spring Way");
        mdv.setCity("Frederick");
        mdv.setState("MD");
        mdv.setZip("55555");
        publisherRepository.save(mdv);

        System.out.println("Number of Publisher:" + publisherRepository.count());

        Author madhu = new Author("Madhu", "Bhadra");
        Book homedesign = new Book("Lovable Home", "1234");

        madhu.getBooks().add(homedesign);
        homedesign.getAuthors().add(madhu);
        homedesign.setPublisher(mdv);    // Assigning publisher 'MDV' to a book
        mdv.getBooks().add(homedesign); // Add first book to Publisher 'MDV'

        authorRepository.save(madhu);
        bookRepository.save(homedesign);
        publisherRepository.save(mdv);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development Without EJB", "55555");

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(mdv);      // Assigning publisher 'MDV' to a book
        mdv.getBooks().add(noEJB);    // Add second book to Publisher 'MDV'

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(mdv);

        System.out.println("Number of Books:" + bookRepository.count());
        System.out.println("Publisher number of books:" + mdv.getBooks().size());
    }
}
