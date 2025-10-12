package Konecta.ERP.API_Gateway.routes;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.predicate.GatewayRequestPredicates.path;

@Configuration
public class Routes {

    // Load-balanced RestClient for service discovery
    @Bean
    @LoadBalanced
    public RestClient.Builder loadBalancedRestClientBuilder() {
        return RestClient.builder();
    }

    // Regular RestClient for direct URLs (like Eureka)
    @Bean
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }


    @Bean
    public RouterFunction<ServerResponse> exampleServiceRoute() {
        return route("service-example")
                .route(path("/api/example/**"), HandlerFunctions.http("lb://service-example"))
                .build();
    }

}