package latency_troubleshooter;

import org.jeasy.random.EasyRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResponseGenerator {

    private static Logger log = LoggerFactory.getLogger(ResponseGenerator.class);

    private EasyRandom easyRandom;

    @Value("${application.config.results.generation.size}")
    private int resultsGenerationSize;

    public ResponseGenerator() {
        easyRandom = new EasyRandom();
    }

    public List<Response> generate() {

       return easyRandom.objects(Response.class, resultsGenerationSize)
                        .collect(Collectors.toList());
    }
}
