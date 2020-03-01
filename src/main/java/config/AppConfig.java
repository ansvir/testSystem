package config;
import dao.TestDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "servlet.data")
public class AppConfig {

    @Bean
    public TestDAO testDAO() {
        return new TestDAO();
    }
}