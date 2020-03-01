package co.vinod.kafkaproducer;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@EnableBinding(Source.class) // this creates a kafka (since, this binder is in build path) producer
@Slf4j
@RestController
@SpringBootApplication
public class KafkaProducerApplication {

	@Autowired
	MessageChannel output;

	@PostMapping("/api/data")
	public Map<String, Object> post(@RequestBody Map<String, Object> data) {
		log.info("Got this data as payload --> {}", data);
		output.send(MessageBuilder.withPayload(data).build());
		log.info("published to a message broker using {}", output.getClass().getName());
		return data;
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}

}
