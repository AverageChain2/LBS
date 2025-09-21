package staffs.identitymanagement;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@EntityScan(basePackages = {"staffs.common.security", "staffs.identitymanagement.infrastructure.events"})

@EnableRabbit
@SpringBootApplication
public class IdentityManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(IdentityManagementApplication.class, args);
    }
}
