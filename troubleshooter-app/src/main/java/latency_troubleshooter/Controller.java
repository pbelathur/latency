package latency_troubleshooter;

import org.jeasy.random.EasyRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Controller {

    Logger log = LoggerFactory.getLogger(Controller.class);

    @Value("${results.generation.size}")
    private int resultsGenerationSize;

    private EasyRandom easyRandom = new EasyRandom();

    @GetMapping("/invoke")
    private List<Response> invokeService() {

        log.info("[Start invokeService]");

        Instant start = Instant.now();
        List<Response> responses = easyRandom.objects(Response.class, resultsGenerationSize)
                                             .collect(Collectors.toList());
        Instant end = Instant.now();

        long duration = Duration.between(start, end).toMillis();
        log.info("[End invokeService] - elapsed time: " + Long.toString(duration));
        return responses;
    }
}
