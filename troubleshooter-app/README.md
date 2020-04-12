# Latency Troubleshooting App.


### How to run latency-troubleshooter-app 
#### Without Docker
1. `cd troubleshooter-app`
2. `./gradlew bootRun`

3. **Usage:** `http://localhost:8080/api/generate/<response-size>`  
   e.g. http://localhost:8080/api/generate/200 generates a response with 200 items in JSON format.

4. Metrics: `http://localhost:8080/actuator/prometheus`
   - Search for `generateJSON` in `http://localhost:8080/actuator/prometheus` to find the execution time statistics.

5. Application Metrics: http://localhost:8080/actuator/generate-json-statistics
   ```
   {
    "size":"100",
    "service-execution-time":"4ms"
   }
   ```
#### With Docker
1. `cd troubleshooter-app`
2. `./gradlew jibDockerBuild --image=myimages/latency-troubleshooter-app`
3. `docker run -p 8080:8080 -t myimages/latency-troubleshooter-app`

### Application performance profiling using Prometheus and Grafana in Docker

1. `cd troubleshooter-app\docker\prometheus`

2. Replace `LOCAL_MACHINE_IP` with the actual IP address of the machine running Docker
   in `prometheus.xml`
   
    - `LOCAL_MACHINE_IP` is **NOT** `localhost` or `127.0.0.1`
    - `PORT` is the Spring Boot application port usually `8080`

3. `cd troubleshooter-app\docker`
4. `docker-compose up`

5. Verify `prometheus` can communicate with `troubleshooter-app`
    - using a web browser access `http://localhost:9090/targets`

6. Verify `grafana` can communicate with `prometheus`
   - using a web browser access `http://localhost:3000`
       - under the `Recently viewed dashboards` look for the entry `Spring Boot 2.1 Statistics`
       - click on `Spring Boot 2.1 Statistics` and look for `Instance =` `LOCAL_MACHINE_IP:PORT` specified in `prometheus.xml`


## NOTES

1. https://github.com/vegasbrianc/prometheus
2. https://grafana.com/docs/grafana/latest/administration/provisioning/#datasources
3. http://www.inanzzz.com/index.php/post/yglp/export-and-import-grafana-dashboard-and-data-sources
4. https://blog.lwolf.org/post/going-open-source-in-monitoring-part-ii-creating-the-first-dashboard-in-grafana/
5. https://stackabuse.com/monitoring-spring-boot-apps-with-micrometer-prometheus-and-grafana/
