package xcode.com.recruicment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class RecruitmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecruitmentApplication.class, args);
//		testowa();
	}

	public static void testowa(){
		String url = "https://api.nbp.pl/api/exchangerates/rates/a/chf/?format=json";

		WebClient.Builder builder = WebClient.builder();

		String qaz = builder.build().get().uri(url).retrieve().bodyToMono(String.class).block();

		System.out.println(qaz);
	}




}
