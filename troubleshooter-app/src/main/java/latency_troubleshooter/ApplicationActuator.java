package latency_troubleshooter;

import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@AllArgsConstructor
@RestControllerEndpoint(id = "generate-json-statistics")
@Component
public class ApplicationActuator {

    private Map runtimeStatistics;

    @GetMapping
    public Map jsonGenerationStatistics() {
         return runtimeStatistics;
    }
}
