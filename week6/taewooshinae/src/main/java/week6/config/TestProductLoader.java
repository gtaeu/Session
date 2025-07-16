//package week6.config;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import week6.entity.Cart;
//import week6.entity.Product;
//import week6.repository.CartRepository;
//import week6.repository.ProductRepository;
//
//@Component
//@RequiredArgsConstructor
//public class TestProductLoader implements CommandLineRunner {
//
//    private final ProductRepository productRepository;
//    private final CartRepository cartRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        Product product1 = new Product();
//        product1.setProductName("아식스신발");
//        product1.setPrice(10000);
//        productRepository.save(product1);
//
//        Product product2 = new Product();
//        product2.setProductName("뉴발란스스신발");
//        product2.setPrice(20000);
//        productRepository.save(product2);
//
//        Cart cart = new Cart();
//        cartRepository.save(cart);
//    }
//
//}
