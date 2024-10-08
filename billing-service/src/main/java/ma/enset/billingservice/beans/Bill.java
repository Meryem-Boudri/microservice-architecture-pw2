package ma.enset.billingservice.beans;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.billingservice.model.Customer;

import java.util.Collection;
import java.util.Date;
@Entity
@AllArgsConstructor @NoArgsConstructor @Data
public class Bill {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private Date billingDate ;
   @OneToMany(mappedBy = "bill")
    private Collection<ProductItem> productItems ;
   private long customerId ;
   @Transient
   private Customer customer ;
}
