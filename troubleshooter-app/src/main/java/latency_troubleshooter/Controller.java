package latency_troubleshooter;

import io.micrometer.core.annotation.Timed;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class Controller {

    private static Logger log = LoggerFactory.getLogger(Controller.class);

    private AppConfig appConfig;
    private ResponseGenerator responseGenerator;

    @GetMapping("/generate")
    @Timed(description = "Time spent generating random response")
    private Map generate() {

        Map map = new HashMap();
        map.put("total random responses generated",
                responseGenerator.generate(appConfig.getResultsGenerationSize()).size());
        return map;
    }

    @GetMapping("/generateJson")
    @Timed(description = "Time spent generating JSON response")
    private List<Response> generateBigJSON() {
        
        return responseGenerator.generate(appConfig.getResultsGenerationSize());
    }

    @PutMapping("/responseSize/{size}")
    private AppConfig updateResponseSize(@PathVariable int size) {

        appConfig.setResultsGenerationSize(size);
        return appConfig;
    }
}