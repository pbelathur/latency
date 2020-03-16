package latency_troubleshooter;

import io.micrometer.core.annotation.Timed;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class Controller {

    private ResponseGenerator responseGenerator;

    @GetMapping("/generate/{size}")
    @Timed(value="generateJSON")
    private List<Response> generate(@PathVariable int size) {
        return responseGenerator.generate(size);
    }
}