package likelion.shopping_mall.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String password;
    private String email;
    private String address;
    private String phone;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;


}
