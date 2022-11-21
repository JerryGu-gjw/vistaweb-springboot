package lab.vista.vistaweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("lab.vista.vistaweb.mapper")
public class VistawebApplication {
	public static void main(String[] args) {
		SpringApplication.run(VistawebApplication.class, args);
		}

}
