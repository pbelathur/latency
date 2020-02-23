package latency_troubleshooter;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Timer;
import org.jeasy.random.EasyRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JsonGeneratorService {

    private static Logger log = LoggerFactory.getLogger(JsonGeneratorService.class);

    private MeterRegistry meterRegistry;
    private Timer timer;
    private EasyRandom easyRandom;

    @Value("${application.config.results.generation.size}")
    private int resultsGenerationSize;

    public JsonGeneratorService(MeterRegistry meterRegistry) {

        easyRandom = new EasyRandom();
        this.meterRegistry = meterRegistry;
        timer = meterRegistry.timer("JsonGeneratorService");
    }

    @Timed(description = "Time spent generating JSON", longTask = true)
    public List<Response> generate() {
       return easyRandom.objects(Response.class, resultsGenerationSize)
                        .collect(Collectors.toList());
    }
}
