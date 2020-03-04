package latency_troubleshooter;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Component
public class AppConfig {

    @Value("${application.results.generation.size}")
    private int resultsGenerationSize;
}
