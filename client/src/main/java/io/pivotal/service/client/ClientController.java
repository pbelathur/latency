package io.pivotal.service.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/")
    public String invokeService(HttpServletRequest request) {

        LOG.info("-- Start invokeService--" + request.getHeader("transId"));

        Instant start = Instant.now();

        String url = "https://investserver.app.dev1.dal.pcf.syfbank.com/invoke";
        HttpHeaders headers = new HttpHeaders();
        headers.set("transId", request.getHeader("transId"));
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<Result[]> responseEntity = restTemplateApache.exchange(url, HttpMethod.GET, entity, Result[].class);
        List<Result> results = Arrays.asList(responseEntity.getBody());
        Instant end = Instant.now();

        int totalResults = results.size();
        long duration = Duration.between(start, end).toMillis();
        String message = "Total results:" + totalResults + " total time taken:" + duration + "ms";
        LOG.info(message);
        LOG.info("-- End invokeService--" + request.getHeader("transId"));
        return message;
    }
}
