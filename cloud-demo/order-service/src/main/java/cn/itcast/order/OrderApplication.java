package cn.itcast.order;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@MapperScan("cn.itcast.order.mapper")
@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    /**
     * RestTemple
     * 发送http请求
     */
    @Bean
    @LoadBalanced//服务器负载均衡
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 服务器负载均衡
     * @return
     */
//    @Bean
//    public IRule rule(){
//        //随机
//        return new RandomRule();
//    }
}