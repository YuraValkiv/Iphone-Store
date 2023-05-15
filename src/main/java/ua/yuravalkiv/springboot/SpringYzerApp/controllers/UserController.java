package ua.yuravalkiv.springboot.SpringYzerApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.yuravalkiv.springboot.SpringYzerApp.models.Order;
import ua.yuravalkiv.springboot.SpringYzerApp.models.Person;
import ua.yuravalkiv.springboot.SpringYzerApp.models.Product;
import ua.yuravalkiv.springboot.SpringYzerApp.repositories.OrderRepository;
import ua.yuravalkiv.springboot.SpringYzerApp.repositories.PeopleRepository;
import ua.yuravalkiv.springboot.SpringYzerApp.repositories.ProductRepository;
import ua.yuravalkiv.springboot.SpringYzerApp.security.PersonDetails;
import ua.yuravalkiv.springboot.SpringYzerApp.services.EmailService;
import ua.yuravalkiv.springboot.SpringYzerApp.util.Translator;
import java.util.List;


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
    public String index(Model model,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "9") int size,
                        @RequestParam(defaultValue = "null") String lang) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAll(pageable);
        model.addAttribute("products", products);
        Translator.langSetter(lang);
        Translator.translateTo(model, lang);

        return "userView/index";
    }


    @GetMapping("/{productId}")
    public String showProductDetails(@PathVariable Long productId ,@RequestParam(defaultValue = "null") String lang, Model model) {
        // Отримання продукту за id з бази даних
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not be found"));
        model.addAttribute("product", product);
        Translator.langSetter(lang);
        Translator.translateTo(model, lang);

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
    public String addProductToCart(@RequestParam("productId") Long productId,
                                   Authentication authentication) {
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        Person person = personDetails.getPerson();
        Product product = productRepository.findById(productId).orElseThrow();
        Order order = new Order(person, product);
        orderRepository.save(order);

        return "redirect:/Simple-iPhone-Store";
    }





    //Корзина

    @GetMapping("/cart")
    public String getCart(Authentication authentication,
                          Model model,
                          @RequestParam(defaultValue = "null") String lang) {
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        Person person = personDetails.getPerson();
        List<Order> orders = orderRepository.findByPerson(person);
        model.addAttribute("orders", orders);
        Translator.langSetter(lang);
        Translator.translateTo(model, lang);

        return "userView/cart";
    }


    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        orderRepository.deleteById(id);

        return "redirect:/Simple-iPhone-Store/cart";
    }

    @PostMapping("/buyAll")
    public String buyAllProductsFromCart(Authentication authentication) {
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        Person person = personDetails.getPerson();
        List<Order> orders = orderRepository.findByPerson(person);
        for (Order order : orders) {
            Product product = order.getProduct();
            emailService.sendEmail(person.getEmail(), "Підтвердження покупки", "Ви успішно придбали товар \" " + product.getName() + " за ціною:  "+ product.getPrice() + "$  " + product.getImagePath());
        }
        orderRepository.deleteAll(orders);

        return "redirect:/Simple-iPhone-Store/cart";
    }

    @GetMapping("/about")
    public String aboutPage(Model model,
                            @RequestParam(defaultValue = "null") String lang) {
        Translator.langSetter(lang);
        Translator.translateTo(model, lang);

        return "/userView/about";
    }




}
