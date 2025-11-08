package mk.ukim.ukim.wp.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class LabReservationApplication {
    public static void main(String[] args) {
        SpringApplication.run(LabReservationApplication.class, args);
    }

}
