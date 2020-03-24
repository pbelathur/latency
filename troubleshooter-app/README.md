# Latency Troubleshooting App.

## USAGE:

### latency-troubleshooter-app
1. **Usage:** `http://localhost:8080/api/generate/<response-size>`  
   e.g. `http://localhost:8080/api/generate/200` generates a response with 200 items in JSON format.

2. Metrics: `http://localhost:8080/actuator/prometheus`

3. Search for `generateJSON` in `http://localhost:8080/actuator/prometheus` to find the execution time statistics.

### Using Docker

1. Edit `targets` in `troubleshooter-app\docker\prometheus\prometheus.xml`

    ```
    static_configs:
      - targets: ['LOCAL_MACHINE_IP:PORT']
    ```
    - `LOCAL_MACHINE_IP` is **NOT** `localhost` or `127.0.0.1`
       it is the actual IP address of the machine running Docker
    - `PORT` is the Spring Boot application port usually `8080`


2. `cd docker`
    `docker-compose up`

3. Start the `troubleshooter-app`: `mvn spring-boot:run`

4. Verify `prometheus` can communicate with `troubleshooter-app`: `http://localhost:9090/targets`

5. Verify `grafana` can communicate with `prometheus`:  `http://localhost:3000`

   - under the `Recently viewed dashboards` you should see an entry for `Spring Boot 2.1 Statistics`
   - click on `Spring Boot 2.1 Statistics` you should see the `Instance =` `LOCAL_MACHINE_IP:PORT` specified in `prometheus.xml`


## NOTES

1. https://github.com/vegasbrianc/prometheus
2. https://grafana.com/docs/grafana/latest/administration/provisioning/#datasources
3. http://www.inanzzz.com/index.php/post/yglp/export-and-import-grafana-dashboard-and-data-sources
4. https://blog.lwolf.org/post/going-open-source-in-monitoring-part-ii-creating-the-first-dashboard-in-grafana/
5. https://stackabuse.com/monitoring-spring-boot-apps-with-micrometer-prometheus-and-grafana/
