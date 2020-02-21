package latency_troubleshooter_client;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateWithApacheHttpConnectionPool {

    Logger log = LoggerFactory.getLogger(RestTemplateWithApacheHttpConnectionPool.class);

    @Value("${connection.request.timeout}")
    int connectionRequestTimeout;

    @Value("${connect.timeout}")
    int connectTimeout;

    @Value("${socket.timeout}")
    int socketTimeout;

    @Value("${max.total}")
    int maxTotal;

    @Value("${default.maxPerRoute}")
    int defaultMaxPerRoute;

    @Bean(name = "restTemplateApache")
    public RestTemplate restTemplate(HttpClient httpClient) {

        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpComponentsClientHttpRequestFactory.setHttpClient(httpClient);
        return new RestTemplate(httpComponentsClientHttpRequestFactory);
    }

    @Bean
    public CloseableHttpClient httpClient(PoolingHttpClientConnectionManager poolingHttpClientConnectionManager,
                                          RequestConfig requestConfig) {

        CloseableHttpClient result = HttpClientBuilder.create()
                                                      .setConnectionManager(poolingHttpClientConnectionManager)
                                                      .setDefaultRequestConfig(requestConfig)
                                                      .build();
        return result;
    }

    @Bean
    public RequestConfig requestConfig() {

        RequestConfig result = RequestConfig.custom()
                                            .setConnectionRequestTimeout(connectionRequestTimeout)
                                            .setConnectTimeout(connectTimeout)
                                            .setSocketTimeout(socketTimeout)
                                            .build();
        return result;
    }

    @Bean
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {

        PoolingHttpClientConnectionManager result = new PoolingHttpClientConnectionManager();
        result.setMaxTotal(maxTotal);
        result.setDefaultMaxPerRoute(defaultMaxPerRoute);
        return result;
    }
}