package academy.devgus.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import test.outside.Connection;

@Configuration
public class BeanConfig {
    //@Bean(name = "MongoDB")
//@Primary
    public Connection connectionMongoDb() {
        return new Connection("localhost", "gusdev", "a730fda");
    }
    @Bean(name = "mySQL")
    public Connection connectionMySql() {
        return new Connection("localhost", "gusdev", "3840za9");
    }
}
