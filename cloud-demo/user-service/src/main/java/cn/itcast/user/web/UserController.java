package cn.itcast.user.web;

import cn.itcast.user.pojo.User;
import cn.itcast.user.properties.ConfigProperties;
import cn.itcast.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


//    @Value("${pattern.dateformat}")
//    private String dateformat;

    @Autowired
    private ConfigProperties configProperties;

    @GetMapping("prop")
    public ConfigProperties properties(){
        log.info("格式：{}",configProperties.getDateformat());
        log.info("格式2：{}",configProperties.getValueShare());
        return configProperties;
    }

    @GetMapping("now")
    public String now(){
        log.info("now格式：{}",configProperties.getDateformat());
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern(configProperties.getDateformat()));
    }

    /**
     * 路径： /user/110
     *
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id) {
        return userService.queryById(id);
    }
}
