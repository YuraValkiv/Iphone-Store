package ua.yuravalkiv.springboot.SpringYzerApp.controllers;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.yuravalkiv.springboot.SpringYzerApp.models.Product;
import ua.yuravalkiv.springboot.SpringYzerApp.repositories.ProductRepository;

import java.util.Optional;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping()
    public String index(Model model, @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "9") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAll(pageable);
        model.addAttribute("products", products);
        return "adminView/index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("product", new Product());
        return "adminView/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Product product) {
        productRepository.save(product);
        return "redirect:/admin";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "adminView/edit";
        } else {
            return "redirect:/admin";
        }
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Product product) {
        productRepository.save(product);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/admin";
    }
    @GetMapping("/search")
    public String search(@RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "9") int size,
                         @RequestParam String keyword,
                         Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findByNameContainingIgnoreCase(keyword, pageable);
        model.addAttribute("products", products);
        return "adminView/index";
    }
}
