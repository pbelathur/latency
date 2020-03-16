package latency_troubleshooter;

import io.micrometer.core.annotation.Timed;
import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class Controller {

    private static Logger log = LoggerFactory.getLogger(Controller.class);

    private ResponseGenerator responseGenerator;

    @GetMapping("/generate/{size}")
    @Timed(value="generateJSON")
    private List<Response> generate(@PathVariable int size) {

        log.info("Start: generate JSON response");
        List<Response> response =  responseGenerator.generate(size);
        log.info("End: generate JSON response");

        return response;
    }
}