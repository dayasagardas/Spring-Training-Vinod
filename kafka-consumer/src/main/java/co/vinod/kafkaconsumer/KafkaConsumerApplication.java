package co.vinod.kafkaconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import lombok.extern.slf4j.Slf4j;

@EnableBinding(Sink.class) // this creates a kafka (since, this binder is in build path) consumer
@Slf4j
@SpringBootApplication
public class KafkaConsumerApplication {

	// One of 3 MessageChannel beans created by Spring cloud stream is "input"
	// others are "nullChannel", "errorChannel"
	// @Autowired
	// MessageChannel input;

	
	@StreamListener("input")
	public void handleMessage(Object object) {
		log.info("Got this message from the inputChannel --> {}", object);
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}

}
