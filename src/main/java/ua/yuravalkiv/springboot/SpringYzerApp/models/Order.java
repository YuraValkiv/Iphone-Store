package ua.yuravalkiv.springboot.SpringYzerApp.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Order() {
    }

    public Order(Person person, Product product) {
        this.person = person;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(getId(), order.getId()) && Objects.equals(getPerson(), order.getPerson()) && Objects.equals(getProduct(), order.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPerson(), getProduct());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", person=" + person +
                ", product=" + product +
                '}';
    }

}
