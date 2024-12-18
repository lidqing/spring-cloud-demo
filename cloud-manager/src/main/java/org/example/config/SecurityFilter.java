package org.example.config;

import org.example.common.constant.Constants;
import org.example.common.secure.TokenUtil;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Component
public class SecurityFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader(Constants.TOKEN);
        String loginToken = request.getHeader(Constants.ACCESS_TOKEN);
        String userId = request.getHeader(Constants.userId);
        //验证请求是通过gateway分发的
        if (TokenUtil.checkToken(token) && loginToken != null && userId != null) {
            super.doFilter(request, response, chain);
        } else {
            //token验证不通过，转发到404
            request.getRequestDispatcher("/404.html").forward(request, response);
        }
    }

}
