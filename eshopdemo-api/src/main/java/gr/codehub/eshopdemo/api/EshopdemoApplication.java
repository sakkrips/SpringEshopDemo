package gr.codehub.eshopdemo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "gr.codehub.eshopdemo",
        "gr.codehub.eshopdemo.domain",
        "gr.codehub.eshopdemo.application"
})
@EnableJpaRepositories(basePackages = "gr.codehub.eshopdemo.domain.repository")
@EntityScan(basePackages = "gr.codehub.eshopdemo.domain.model")
public class EshopdemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(EshopdemoApplication.class, args);
    }
}
