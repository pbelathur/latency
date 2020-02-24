package latency_troubleshooter;

import org.jeasy.random.EasyRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResponseGenerator {

    private static Logger log = LoggerFactory.getLogger(ResponseGenerator.class);

    private EasyRandom easyRandom;
    private AppConfig appConfig;

    public ResponseGenerator(AppConfig appConfig) {

        easyRandom = new EasyRandom();
        this.appConfig = appConfig;
    }

    public List<Response> generate() {

       return easyRandom.objects(Response.class, appConfig.getResultsGenerationSize())
                        .collect(Collectors.toList());
    }
}
