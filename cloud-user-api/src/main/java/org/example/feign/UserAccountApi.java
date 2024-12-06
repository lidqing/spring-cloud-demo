package org.example.feign;

import org.example.fallback.UserAccountFallBack;
import org.example.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(contextId = "cloud-user-userUserAccountApi", name = "cloud-user", path = "/api/user", fallback = UserAccountFallBack.class,
        configuration = UserAccountApi.FeignConfiguration.class)
public interface UserAccountApi {


    @PostMapping("/accountUpdate")
    Boolean updateAccount(@RequestParam("userId") String userId);


    class FeignConfiguration {
        @Bean
        public UserAccountFallBack userSearchFallBack() {
            return new UserAccountFallBack();
        }
    }

}
