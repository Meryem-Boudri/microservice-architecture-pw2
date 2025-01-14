package ma.enset.billingservice.feign;

import jakarta.ws.rs.QueryParam;
import ma.enset.billingservice.model.Customer;
import ma.enset.billingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "VENTORY-SERVICE")
public interface ProductItemRestClient {
    @GetMapping("/products")
    PagedModel<Product> pageProducts();
    @GetMapping("/products/{productId}")
    Product getProduct(@PathVariable("productId") Long productId);

}
