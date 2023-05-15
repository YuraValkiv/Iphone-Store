package ua.yuravalkiv.springboot.SpringYzerApp.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.yuravalkiv.springboot.SpringYzerApp.models.Order;
import ua.yuravalkiv.springboot.SpringYzerApp.models.Person;


import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByPerson(Person person);

}
