package chien.myweb.NFAC300ATimer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Nfac300ATimerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Nfac300ATimerApplication.class, args);
	}

}
