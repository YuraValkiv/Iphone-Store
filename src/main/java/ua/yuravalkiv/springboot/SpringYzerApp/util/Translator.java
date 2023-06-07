package ua.yuravalkiv.springboot.SpringYzerApp.util;

import org.springframework.ui.Model;

public class Translator {
    public void translate(String lang, Model model) {
        boolean setUA = langSetter(lang);
        translateTo(model, setUA);
    }

    private boolean langSetter(String lang) {
        if (lang.equals("uk")) {
            return true;
        } else if (lang.equals("en")) {
            return false;
        }
        // За замовчуванням повертаємо false, якщо мова не визначена
        return false;
    }

    public void translateTo(Model model, boolean setUA) {
        if (setUA) {
            model.addAttribute("ListProducts", "Список товарів");
            model.addAttribute("Cart", "Закази");
            model.addAttribute("About", "Про нас");
            model.addAttribute("Logout", "Розлогінитись");
            model.addAttribute("Search", "Пошук");
            model.addAttribute("Details" , "Деталі");
            model.addAttribute("AddToCart", "Добавити до заказів");
            model.addAttribute("AllRights", "Всі права захищені");
            model.addAttribute("TermsUse", "Умови використання");
            model.addAttribute("PrivacyPolice", "Політика конфіденційності");
            model.addAttribute("FAQ", "ФАКС");
            model.addAttribute("Back", "Повернутись назад");
            model.addAttribute("Prod", "Товари");
            model.addAttribute("Price", "Ціна");
            model.addAttribute("Delete", "Видалити");
            model.addAttribute("BuyAll", "Купити всьо");
            model.addAttribute("DESCRIPTION", "Simple-iPhone-Store - це інтернет-магазин, який спеціалізується на продажі продукції Apple, зокрема iPhone. Наш магазин надає клієнтам можливість купувати як нові, так і відновлені моделі iPhone за доступною ціною. У нас завжди великий вибір товарів, а також ми забезпечуємо високу якість обслуговування та швидку доставку. Звертайтеся до нас, якщо вам потрібен надійний та професійний інтернет-магазин для покупки продукції Apple.");
        } else {
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
}
