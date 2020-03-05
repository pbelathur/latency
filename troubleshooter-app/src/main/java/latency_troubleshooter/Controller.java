package latency_troubleshooter;

import io.micrometer.core.annotation.Timed;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class Controller {

    private ResponseGenerator responseGenerator;

    @GetMapping("/generate/{size}")
    @Timed(description = "Time spent generating *random* JSON response")
    private List<Response> generateJSON(@PathVariable int size) {
        return responseGenerator.generate(size);
    }
}