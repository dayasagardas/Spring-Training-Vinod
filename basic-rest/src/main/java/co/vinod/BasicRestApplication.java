package co.vinod;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@SpringBootApplication
public class BasicRestApplication {

	@GetMapping("/api/hello/{name}")
	public Map<String, Object> post(@PathVariable String name) {
		log.info("Got this data as query string param --> {}", name);
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("message", "Hello, " + name + "!");
		map.put("timestamp", new Date());
		log.info("Responding the user with --> {}", map);
		return map;
	}

	public static void main(String[] args) {
		SpringApplication.run(BasicRestApplication.class, args);
	}

}
