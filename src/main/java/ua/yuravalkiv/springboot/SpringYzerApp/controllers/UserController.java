package ru.alishev.springcourse.FirstSecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.FirstSecurityApp.models.Order;
import ru.alishev.springcourse.FirstSecurityApp.models.Person;
import ru.alishev.springcourse.FirstSecurityApp.models.Product;
import ru.alishev.springcourse.FirstSecurityApp.repositories.OrderRepository;
import ru.alishev.springcourse.FirstSecurityApp.repositories.PeopleRepository;
import ru.alishev.springcourse.FirstSecurityApp.repositories.ProductRepository;
import ru.alishev.springcourse.FirstSecurityApp.security.PersonDetails;
import ru.alishev.springcourse.FirstSecurityApp.services.EmailService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("Simple-iPhone-Store")
public class UserController {
    @Autowired
    private PeopleRepository peopleRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EmailService emailService;

    @GetMapping()
    public String index(Model model, @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "9") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAll(pageable);
        model.addAttribute("products", products);
        return "userView/index";
    }
    @GetMapping("/{productId}")
    public String showProductDetails(@PathVariable Long productId, Model model) {
        // Отримання продукту за id з бази даних
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Продукт не знайдено"));
        model.addAttribute("product", product);
        return "userView/details";
    }


    @GetMapping("/search")
    public String search(@RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "9") int size,
                         @RequestParam String keyword,
                         Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findByNameContainingIgnoreCase(keyword, pageable);
        model.addAttribute("products", products);
        return "userView/index";
    }
    @PostMapping("/add")
    public String addProductToCart(@RequestParam("productId") Long productId, Authentication authentication) {
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        Person person = personDetails.getPerson();
        Product product = productRepository.findById(productId).orElseThrow();
        Order order = new Order(person, product);
        orderRepository.save(order);
        return "redirect:/Simple-iPhone-Store";
    }





    //Корзина

    @GetMapping("/cart")
    public String getCart(Authentication authentication, Model model) {
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        Person person = personDetails.getPerson();
        List<Order> orders = orderRepository.findByPerson(person);
        model.addAttribute("orders", orders);
        return "userView/cart";
    }

    //@PostMapping("/buy")
    //public String buyProductFromCart(@RequestParam("productId") Long productId, Authentication authentication) {
   //     PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
   //     Person person = personDetails.getPerson();
  //      Product product = productRepository.findById(productId).orElseThrow();
 //       Order order = new Order(person, product);
  //      orderRepository.save(order);
 //       return "redirect:/Simple-iPhone-Store/cart";
 //   }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        orderRepository.deleteById(id);
        return "redirect:/Simple-iPhone-Store/cart";
    }
//    @PostMapping("/buyAll")
//    public String buyAllProductsFromCart(Authentication authentication) {
 //       PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
   //     Person person = personDetails.getPerson();
   //     List<Order> orders = orderRepository.findByPerson(person);
   //     for (Order order : orders) {
    //        orderRepository.delete(order);
   //     }
    //    return "redirect:/Simple-iPhone-Store/cart";
   // }

    @GetMapping("/about")
    public String aboutPage() {
        return "/userView/about";
    }




}
