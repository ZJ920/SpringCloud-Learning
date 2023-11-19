package cn.itcast.user.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "pattern")
public class ConfigProperties {
    private String dateformat;
    private String valueShare;
    private String name;
}
