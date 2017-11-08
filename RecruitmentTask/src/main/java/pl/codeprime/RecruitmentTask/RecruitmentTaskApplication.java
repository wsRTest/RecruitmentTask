package pl.codeprime.RecruitmentTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("pl.codeprime")
public class RecruitmentTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecruitmentTaskApplication.class, args);
	}
}
