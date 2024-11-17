package org.example.gateway.filter;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.common.secure.TokenUtil;
import org.example.gateway.config.WhiteListConfig;
import org.example.gateway.model.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * 网关鉴权
 */
@Slf4j
@Component
public class AuthFilter implements GlobalFilter {
    @Autowired
    private WhiteListConfig whiteListConfig;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();
        if (whiteListConfig.getUrls().contains(url)) {
            log.info("allowed url: {}", url);
            return chain.filter(exchange);
        }

        String token = exchange.getRequest().getHeaders().getFirst("Token");
        log.info("get Token: {}", token);
        //验证token信息
        boolean checked = checkToken(token);
        //token验证失败
        if (!checked) {
            return setUnauthorizedResponse(exchange, "token verify failed.");
        }


        //验证token成功
        return chain.filter(exchange);
    }

    private boolean checkToken(String token) {
        boolean valid = TokenUtil.checkToken(token);
        //合法token，查询token是否在redis
        if (valid) {
            //redis查询token
            log.info("走redis查询token");
            return true;
        }
        //token不合法或者未登录
        return false;
    }

    private Mono<Void> setUnauthorizedResponse(ServerWebExchange exchange, String msg) {
        ServerHttpResponse originalResponse = exchange.getResponse();
        originalResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
        originalResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        byte[] response = JSON.toJSONString(R.error(401, msg)).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = originalResponse.bufferFactory().wrap(response);
        return originalResponse.writeWith(Flux.just(buffer));
    }
}
