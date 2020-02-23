package latency_troubleshooter;

import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    private static Logger log = LoggerFactory.getLogger(Controller.class);

    private ResponseGenerator responseGenerator;

    public Controller(ResponseGenerator responseGenerator) {
        this.responseGenerator = responseGenerator;
    }

    @GetMapping("/invoke")
    @Timed(description = "Time spent generating JSON response in generateJSON()")
    private List<Response> generateJSON() {
           return responseGenerator.generate();
    }
}