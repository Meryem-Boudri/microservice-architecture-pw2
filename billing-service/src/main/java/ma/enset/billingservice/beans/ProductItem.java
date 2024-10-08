package ma.enset.billingservice.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.billingservice.model.Product;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductItem {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private int quantity ;
    private Double price ;
    private Long productId ;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Bill bill ;
    @Transient
    private Product product ;

}
