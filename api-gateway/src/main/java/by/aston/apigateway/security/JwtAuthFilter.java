package by.aston.apigateway.security;

import by.aston.apigateway.utils.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Component
public class JwtAuthFilter implements GlobalFilter, Ordered {

    private final JwtUtil jwtUtil;

    // Пути, не требующие JWT
    private static final List<String> PUBLIC_PATHS = List.of(
//            "/api/auth/login",
//            "/api/auth/register",
            "/users"
    );

    public JwtAuthFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("filter works!");
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().toString();

        // 1. Пропускаем публичные пути
        if (isPublicPath(path)) {
            return chain.filter(exchange);
        }
        System.out.println("filter works fr!");
        // 2. Извлекаем токен
        String token = extractToken(request);

        // 3. Проверяем токен
        if (token == null || !jwtUtil.validateToken(token)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // 4. Добавляем user ID в атрибуты запроса
        UUID userId = jwtUtil.getUserIdFromToken(token);



        ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                .header("X-User-Id", userId.toString())
                .build();
        System.out.println("Added X-User-Id header: " + mutatedRequest.getHeaders().get("X-User-Id"));

        ServerWebExchange mutatedExchange = exchange.mutate()
                .request(mutatedRequest)
                .build();


        return chain.filter(mutatedExchange);
    }

    private boolean isPublicPath(String path) {
        return PUBLIC_PATHS.stream().anyMatch(path::startsWith);
    }

    private String extractToken(ServerHttpRequest request) {
        List<String> headers = request.getHeaders().get("Authorization");
        if (headers != null && !headers.isEmpty()) {
            String header = headers.get(0);
            if (header.startsWith("Bearer ")) {
                return header.substring(7);
            }
        }
        return null;
    }

    @Override
    public int getOrder() {
        return -100; // Высокий приоритет среди фильтров
    }
}

