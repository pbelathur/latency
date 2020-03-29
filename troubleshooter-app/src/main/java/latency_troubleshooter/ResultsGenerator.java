package latency_troubleshooter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jeasy.random.EasyRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@RequiredArgsConstructor
@Service
public class ResultsGenerator {

    private static Logger log = LoggerFactory.getLogger(ResultsGenerator.class);
    private EasyRandom easyRandom = new EasyRandom();

    public List<Result> generate(int size) {
       return easyRandom.objects(Result.class, size)
                        .collect(Collectors.toList());
    }
}
