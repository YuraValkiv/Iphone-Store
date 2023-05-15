package ua.yuravalkiv.springboot.SpringYzerApp.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name not be empty")
    @Size(min = 2, max = 100, message = "Name could be min 2 and max 100 char")
    @Column(name = "username")
    private String username;

    @Email(message = "Incorrect email")
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "password not be empty")
    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Person() {
    }

    public Person(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email =" + email +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getId().equals(person.getId()) && getUsername().equals(person.getUsername()) && getEmail().equals(person.getEmail()) && getPassword().equals(person.getPassword()) && getRole().equals(person.getRole()) && Objects.equals(getOrders(), person.getOrders());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getEmail(), getPassword(), getRole(), getOrders());
    }
}