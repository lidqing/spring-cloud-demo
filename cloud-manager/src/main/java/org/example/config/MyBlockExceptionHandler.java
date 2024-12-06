package org.example.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Slf4j
public class MyBlockExceptionHandler implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        JSONObject data = new JSONObject();
        if (e instanceof FlowException) {
            log.warn("Occur flow limiting exception, uri: {}", request.getRequestURI());
            data.put("code", -1);
        } else if (e instanceof DegradeException) {
            log.warn("Occur degrade exception, uri: {}", request.getRequestURI());
            data.put("code", -2);
        }

        data.put("msg", "网络繁忙，请稍后再试");
        response.setStatus(429);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json;charset=UTF-8");
        out.print(data.toJSONString());
        out.flush();
        out.close();
    }
}
