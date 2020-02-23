package latency_troubleshooter;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LatencyTroubleshooter {

	public static void main(String[] args) {
		SpringApplication.run(LatencyTroubleshooter.class, args);
	}
}
