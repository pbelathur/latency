package latency_troubleshooter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

    @Value("${application.config.results.generation.size}")
    private int resultsGenerationSize;

    public int getResultsGenerationSize() {
        return resultsGenerationSize;
    }

    public void setResultsGenerationSize(int resultsGenerationSize) {
        this.resultsGenerationSize = resultsGenerationSize;
    }
}
