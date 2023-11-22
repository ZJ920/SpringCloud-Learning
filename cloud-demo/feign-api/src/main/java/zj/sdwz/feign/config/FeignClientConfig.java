package zj.sdwz.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

public class FeignClientConfig {

    @Bean
    public Logger.Level log(){
        return Logger.Level.BASIC;
    }
}
