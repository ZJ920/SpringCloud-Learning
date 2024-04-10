package zj.sdwz.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import zj.sdwz.feign.pojo.User;

@FeignClient(value = "userservice",configuration = FeignClientProperties.FeignClientConfiguration.class)
public interface UserClient {
    //向userservice服务发送Get请求
    @GetMapping("user/{id}")
    User findById(@PathVariable Long id); 
}
