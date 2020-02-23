package latency_troubleshooter;


import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Timer;
import org.jeasy.random.EasyRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JsonGeneratorService {

    private static Logger log = LoggerFactory.getLogger(JsonGeneratorService.class);
    private MeterRegistry meterRegistry;
    private Timer timer;

    private AppConfig appConfig;
    private EasyRandom easyRandom;

    public JsonGeneratorService(AppConfig appConfig, MeterRegistry meterRegistry) {

        this.appConfig = appConfig;
        easyRandom = new EasyRandom();
        this.meterRegistry = meterRegistry;
        timer = meterRegistry.timer("JsonGeneratorService");
    }

    @Timed(description = "Time spent generating JSON", longTask = true)
    public List<Response> generate() {

       return easyRandom.objects(Response.class,
                                 appConfig.getResultsGenerationSize())
                                          .collect(Collectors.toList());
    }
}
