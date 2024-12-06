package org.example.common.config;


import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.circuitbreaker.CircuitBreaker;
import com.alibaba.csp.sentinel.slots.block.degrade.circuitbreaker.EventObserverRegistry;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.util.TimeUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

/**
 * 熔断降级监听器，用于打印熔断状态变化日志
 */
@Slf4j
@Configuration
public class CircuitBreakerListener implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("初始化的熔断规则数：{}", DegradeRuleManager.getRules().size());
        log.info(JSONObject.toJSONString(DegradeRuleManager.getRules()));
        log.info("初始化的限流规则数：{}", FlowRuleManager.getRules().size());
        log.info(JSONObject.toJSONString(FlowRuleManager.getRules()));
        registerStateChangeObserver();
    }

    /**
     * 熔断降级状态变更监听器
     */
    private static void registerStateChangeObserver() {
        EventObserverRegistry.getInstance().addStateChangeObserver("CircuitBreakerMonitor",
                (prevState, newState, rule, snapshotValue) -> {
                    if (newState == CircuitBreaker.State.OPEN) {
                        log.error(String.format("==> Resource '%s' occur degrade: %s -> OPEN at %d, snapshotValue=%.2f", rule.getResource(), prevState.name(),
                                TimeUtil.currentTimeMillis(), snapshotValue));
                    } else {
                        log.error(String.format("==> Resource '%s' occur degrade: %s -> OPEN at %d, snapshotValue=%.2f", rule.getResource(), prevState.name(),
                                TimeUtil.currentTimeMillis(), snapshotValue));
                    }
                });
    }

}
