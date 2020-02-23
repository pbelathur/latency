package latency_troubleshooter;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Meter;
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

    private static Logger log = LoggerFactory.getLogger(Controller.class);

    private JsonGeneratorService service;

    public Controller(JsonGeneratorService service) {
        this.service = service;
    }

    @GetMapping("/invoke")
    private List<Response> invokeService() {
       return service.generate();
    }
}