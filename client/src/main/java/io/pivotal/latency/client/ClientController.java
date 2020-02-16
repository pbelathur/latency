package io.pivotal.latency.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ClientController {

    Logger LOG = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private RestTemplate restTemplateApache;

    @Value("${server.url}")
    String serverUrl;

    @GetMapping("/")
    public List<Result> invokeService(HttpServletRequest request) {

        LOG.info("[Start invokeService]");

        Instant start = Instant.now();
        String url = serverUrl+ "/invoke";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<Result[]> responseEntity = restTemplateApache.exchange(url, HttpMethod.GET, entity, Result[].class);
        List<Result> results = Arrays.asList(responseEntity.getBody());
        Instant end = Instant.now();

        int totalResults = results.size();
        long duration = Duration.between(start, end).toMillis();

        String message = "Total results: " + totalResults + " total time taken: " + duration + "ms";
        LOG.info("[End invokeService] " + message);
        return results;
    }
}
