package org.example.tp_spring_mvc.Web;

import jakarta.validation.Valid;
import org.example.tp_spring_mvc.entities.Product;
import org.example.tp_spring_mvc.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name="page", defaultValue = "0") int page,
                        @RequestParam(name="keyword", defaultValue = "") String keyword) {

        Page<Product> products =
                productRepository.findAll(PageRequest.of(page, 5));

        model.addAttribute("products", products.getContent());
        model.addAttribute("pages", new int[products.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);

        return "products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/index";
    }

    @GetMapping("/formProducts")
    public String formProducts(Model model) {
        model.addAttribute("product", new Product());
        return "formProducts";
    }

    @PostMapping("/save")
    public String save(@Valid Product product, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "formProducts";

        productRepository.save(product);
        return "redirect:/index";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        model.addAttribute("product", product);
        return "formProducts";
    }

    @GetMapping("/recherche")
    public String search(Model model,
                         @RequestParam(name = "keyword", defaultValue = "") String keyword) {

        List<Product> products =
                productRepository.findByNameContains(keyword);

        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword);

        return "products";
    }
}