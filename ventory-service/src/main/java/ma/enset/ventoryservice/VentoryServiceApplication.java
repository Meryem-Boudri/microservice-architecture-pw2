package ma.enset.ventoryservice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class VentoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VentoryServiceApplication.class, args);
    }
@Bean
    CommandLineRunner start(ProductRepository productRepository , RepositoryRestConfiguration restConfiguration){

        return args -> {
            restConfiguration.exposeIdsFor(Product.class);
            productRepository.save(new Product( null,"ordinateur", 1200.0,12));
            productRepository.save(new Product( null,"telephonr", 2200.0,10));
            productRepository.save(new Product( null,"imprimante", 900.0,8));
productRepository.findAll().forEach(System.out::println);
        };
    }


}
@Entity
@Data@NoArgsConstructor@AllArgsConstructor
@ToString@Builder
class Product{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String name;
    private Double price;
    private int quantity;

}
@RepositoryRestResource
interface ProductRepository extends JpaRepository<Product, Long> {

}