package cn.itcast.gateway;


import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

//顺序注解，数字越小，优先级越高
//@Order(-1)
@Component
public class AuthorizeFilter implements GlobalFilter , Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        获取请求参数
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> params = request.getQueryParams();
//        获取参数中的authorization参数
        String authorization = params.getFirst("authorization");
//        判断
        if ("admin".equals(authorization)){
            return chain.filter(exchange);
        }
//        设置状态码
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//        拦截请求
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
