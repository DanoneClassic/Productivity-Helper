package aaade.gateway;

import aaade.gateway.filter.AuthenticationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder, AuthenticationFilter authenticationFilter) {
		return builder.routes()
				.route("workspace-service", r -> r.path("/api/workspaces/**")
						.filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
						.uri("http://localhost:8083"))
				.route("auth-service", r -> r.path("/api/auth/**")
						.uri("http://localhost:8082"))
				.route("user-service", r -> r.path("/api/users/**")
						.filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
						.uri("http://localhost:8081"))
				.route("issue-service", r -> r.path("/api/QA/**")
						.filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
						.uri("http://localhost:8084"))
				.route("email-service", r -> r.path("/mail/**")
						.filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
						.uri("http://localhost:8085"))
				.route("statistics-service", r -> r.path("/statistics/**")
						.filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
						.uri("http://localhost:8086"))
				.build();
	}
}
