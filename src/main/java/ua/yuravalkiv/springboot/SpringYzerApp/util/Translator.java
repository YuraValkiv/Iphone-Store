package ua.yuravalkiv.springboot.SpringYzerApp.util;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;

@Component
public class Translator {
    public static void translateTo(Model model) {
            model.addAttribute("ListProducts", "List of products");
            model.addAttribute("Cart", "Cart");
            model.addAttribute("About", "About");
            model.addAttribute("Logout", "Logout");
            model.addAttribute("Search", "Search");
            model.addAttribute("Details" , "Details");
            model.addAttribute("AddToCart", "Add to cart");
            model.addAttribute("AllRights", "All rights reserved.");
            model.addAttribute("TermsUse", "Terms of Use");
            model.addAttribute("PrivacyPolice", "Privacy Policy");
            model.addAttribute("FAQ", "FAQ");
            model.addAttribute("Back", "Back");
            model.addAttribute("Prod", "Product");
            model.addAttribute("Price", "Price");
            model.addAttribute("Delete", "Delete");
            model.addAttribute("BuyAll", "Buy all");
            model.addAttribute("DESCRIPTION", "Simple-iPhone-Store is an online store specializing in the sale of Apple products, in particular the iPhone. Our store provides customers with the opportunity to purchase both new and refurbished iPhone models at an affordable price. We always have a large selection of products, and we also provide high quality service and fast delivery. Contact us if you need a reliable and professional online store to buy Apple products.");
        }
    }