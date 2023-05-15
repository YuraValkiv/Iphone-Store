package ua.yuravalkiv.springboot.SpringYzerApp.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Table(name = "product")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "description")
    private String description;

    @Column(name = "image_path")
    private String imagePath;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Order> purchases;

    public List<Order> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Order> purchases) {
        this.purchases = purchases;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Product() {
    }

    public Product(Long id, String name, BigDecimal price, String description, String imagePath) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imagePath = imagePath;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getId().equals(product.getId()) && getName().equals(product.getName()) && getPrice().equals(product.getPrice()) && getDescription().equals(product.getDescription()) && getImagePath().equals(product.getImagePath()) && Objects.equals(getPurchases(), product.getPurchases());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPrice(), getDescription(), getImagePath(), getPurchases());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", purchases=" + purchases +
                '}';
    }
}