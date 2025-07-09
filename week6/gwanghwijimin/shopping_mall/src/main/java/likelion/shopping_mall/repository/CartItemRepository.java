package likelion.shopping_mall.repository;

import likelion.shopping_mall.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findCartItemByProductIdAndCartId(Long productId, Long cartId);
}
