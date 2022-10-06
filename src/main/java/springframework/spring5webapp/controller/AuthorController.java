package springframework.spring5webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import springframework.spring5webapp.repositories.AuthorRepository;

@Controller
public class AuthorController {

    private final AuthorRepository authorRepository;

    // Inject Author Repository via constructor into controller
    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    //Create method to handle request for authors at “/authors”
    @RequestMapping("/authors")
    public String getAuthor(Model model) {
        //Add list of authors to model
        model.addAttribute("authors", authorRepository.findAll());
        //return view “authors/list
        return("authors/list");
    }
}
