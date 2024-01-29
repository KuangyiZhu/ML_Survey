package kuangyizhu;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "kuangyizhu.xyz")
@Data
public class TestProperties {
    private String helloworld;
}
