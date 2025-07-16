package week6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import week6.entity.Cart;
import week6.entity.CartItem;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}
