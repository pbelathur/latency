package latency_troubleshooter;

import io.micrometer.core.annotation.Timed;
import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class Controller {

    private static Logger log = LoggerFactory.getLogger(Controller.class);

    private ResultsGenerator resultsGenerator;
    private Map runtimeStatistics;

    @GetMapping("/generate/{size}")
    @Timed(description="generateJSON")
    private Response generate(@PathVariable int size) {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
            List<Result> results = resultsGenerator.generate(size);
        stopWatch.stop();

        String serviceExecutionTimeAsString = String.valueOf(stopWatch.getTotalTimeMillis()) + "ms";

        // for application actuator endpoint use
        runtimeStatistics.put("size", String.valueOf(results.size()));
        runtimeStatistics.put("service-execution-time", serviceExecutionTimeAsString);

        return Response.builder()
                       .size(size)
                       .serviceExecutionTime(serviceExecutionTimeAsString)
                       .results(results)
                       .build();
    }
}