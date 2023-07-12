package nom.youcanwell.order.repository;

import nom.youcanwell.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByTid(String tid);

}
