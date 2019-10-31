package io.pivotal.service;

import org.jeasy.random.EasyRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LatencyController {

    Logger log = LoggerFactory.getLogger(LatencyController.class);

    @Value("${results.generation.size}")
    private int resultsGenerationSize;

    private EasyRandom easyRandom = new EasyRandom();

    @GetMapping("/invoke")
    private List<Response> invokeService(HttpServletRequest request) {

        log.info("-- Start invokeService --" + request.getHeader("transId"));
        Instant start = Instant.now();
        List<Response> responses = easyRandom.objects(Response.class, resultsGenerationSize).collect(Collectors.toList());
        Instant end = Instant.now();
        log.info("-- End invokeService --" + request.getHeader("transId"));

        long duration = Duration.between(start, end).toMillis();
        String message = "[" + request.getHeader("transId") + "] Total time taken: " + duration + "ms";
        log.info(message);

        return responses;
    }
}
