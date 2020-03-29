package latency_troubleshooter;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class Controller {

    private static Logger log = LoggerFactory.getLogger(Controller.class);

    private ResultsGenerator resultsGenerator;

    @GetMapping("/generate/{size}")
    @Timed(description="generateJSON")
    private Response generate(@PathVariable int size) {

        log.info("Start: generate JSON response");
        List<Result> results = resultsGenerator.generate(size);
        log.info("End: generate JSON response");

        return Response.builder()
                       .size(size)
                       .results(results)
                       .build();
    }
}
