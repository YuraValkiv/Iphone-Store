package ua.yuravalkiv.springboot.SpringYzerApp.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

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
}