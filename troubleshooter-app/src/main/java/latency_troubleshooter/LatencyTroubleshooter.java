package latency_troubleshooter;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class LatencyTroubleshooter {

	public static void main(String[] args) {
		SpringApplication.run(LatencyTroubleshooter.class, args);
	}

	@Bean
	MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
		return registry -> registry.config().commonTags("application", "LatencyTroubleshooter");
	}

	@Bean
	Map runtimeStatistics() {
		return new HashMap();
	}
}