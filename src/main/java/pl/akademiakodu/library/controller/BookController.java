package pl.akademiakodu.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.akademiakodu.library.model.Book;
import pl.akademiakodu.library.repository.AuthorRepository;
import pl.akademiakodu.library.repository.BookRepository;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/books/add")
    public String add(ModelMap modelMap){
        modelMap.addAttribute("book",new Book());
        modelMap.addAttribute("authors",authorRepository.findAll());
        return "books/add";
    }

    @PostMapping("/books")
    public String save(@ModelAttribute Book book){
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/books")
    public String index(ModelMap modelMap){
        modelMap.addAttribute("books",bookRepository.findAll());
        return "books/index";
    }
    @GetMapping("books/{id}/delete")
    public String delete(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }


}
