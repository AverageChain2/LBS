package staffs.staffleave;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class StaffLeaveApplication {

    public static void main(String[] args) {
        SpringApplication.run(StaffLeaveApplication.class, args);
    }

}
