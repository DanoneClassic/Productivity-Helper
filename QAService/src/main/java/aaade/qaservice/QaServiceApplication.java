package aaade.qaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "aaade.qaservice")
public class QaServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(QaServiceApplication.class, args);
    }

}
