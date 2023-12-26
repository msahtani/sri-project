package ma.ensa.sriapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync(proxyTargetClass = true)

@SpringBootApplication
public class SriApplication {

    public static void main(String[] args) {
        SpringApplication.run(SriApplication.class, args);
    }

}
