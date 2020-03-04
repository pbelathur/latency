package latency_troubleshooter;

import lombok.AllArgsConstructor;
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
public class ResponseGenerator {

    private static Logger log = LoggerFactory.getLogger(ResponseGenerator.class);
    private EasyRandom easyRandom = new EasyRandom();

    public List<Response> generate(int size) {
       return easyRandom.objects(Response.class, size)
                        .collect(Collectors.toList());
    }
}
