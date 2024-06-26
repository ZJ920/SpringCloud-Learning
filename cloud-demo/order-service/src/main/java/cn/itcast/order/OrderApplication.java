package cn.itcast.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import zj.sdwz.feign.client.UserClient;

@MapperScan("cn.itcast.order.mapper")
@SpringBootApplication
@EnableFeignClients(clients = UserClient.class)
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    /**
     * RestTemple
     * 发送http请求
     */
//    @Bean
//    @LoadBalanced//服务器负载均衡
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }

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
