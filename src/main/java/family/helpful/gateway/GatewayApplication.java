package family.helpful.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
public class GatewayApplication {

	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
		System.out.println(new Date());
	}
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}
