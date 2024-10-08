package ma.enset.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }


   // @Bean //static
    RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
        /*static way
        *
        * addresses doesn't change and we know all urls
        *  */
        return routeLocatorBuilder.routes()
                .route(r->r
                        .path("/customers/**")
                        .uri("lb://CUSTOMER-SERVICE"))
                .route(r->r
                        .path("/products/**")
                        .uri("lb://VENTORY-SERVICE"))
                       // .uri("http://localhost:8082/"))
                .build();

    }

    @Bean //dynamic
    DiscoveryClientRouteDefinitionLocator dynamicRouteLocator(ReactiveDiscoveryClient rdc , DiscoveryLocatorProperties dlp) {
        return new DiscoveryClientRouteDefinitionLocator(rdc, dlp); //prendre le nom du microservice à partir du requette
    }

}
