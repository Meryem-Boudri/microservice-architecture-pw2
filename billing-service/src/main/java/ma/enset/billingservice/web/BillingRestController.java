package ma.enset.billingservice.web;

import ma.enset.billingservice.beans.Bill;
import ma.enset.billingservice.feign.CustomerRestClient;
import ma.enset.billingservice.feign.ProductItemRestClient;
import ma.enset.billingservice.model.Customer;
import ma.enset.billingservice.model.Product;
import ma.enset.billingservice.repositories.BillRepository;
import ma.enset.billingservice.repositories.ProductItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillingRestController {

   private BillRepository billRepository;
   private ProductItemRepository productItemRepository;

    public BillingRestController(BillRepository billRepository, ProductItemRepository productItemRepository, CustomerRestClient customerRestClient, ProductItemRestClient productItemRestClient) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClient = customerRestClient;
        this.productItemRestClient = productItemRestClient;
    }

    private CustomerRestClient customerRestClient ;
   private ProductItemRestClient productItemRestClient ;


    @GetMapping("/fullBill/{id}")
    public Bill getBillById(@PathVariable Long id) {
        Bill bill = billRepository.findById(id).orElseThrow(
                RuntimeException::new
        );
        Customer customer =customerRestClient.getCustomerById(bill.getCustomerId());
         bill.setCustomer(customer);
         bill.getProductItems().forEach(pi->{
             Product product = productItemRestClient.getProduct(pi.getProductId());
             pi.setProduct(product);
         });
        return bill ;
    }

}
