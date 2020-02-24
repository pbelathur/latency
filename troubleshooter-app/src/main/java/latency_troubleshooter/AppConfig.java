package latency_troubleshooter;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

    @Value("${application.results.generation.size}")
    private int resultsGenerationSize;

    public int getResultsGenerationSize() {
        return resultsGenerationSize;
    }

    public void setResultsGenerationSize(int resultsGenerationSize) {
        this.resultsGenerationSize = resultsGenerationSize;
    }
}
