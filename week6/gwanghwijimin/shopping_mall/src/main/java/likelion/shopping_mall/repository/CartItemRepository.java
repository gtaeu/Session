package likelion.shopping_mall.repository;

import likelion.shopping_mall.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findCartItemByProductIdAndCartId(Long productId, Long cartId);
}
