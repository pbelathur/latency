# Latency Troubleshooting App.

## USAGE:

### latency-troubleshooter-app
1. The `results.generation.size` in `application.properties` controls the count of `Response` items in the JSON response from the controller.  
   This value also controls the service execution time with `results.generation.size = 100000` results in `~ 2000ms` execution time.

2. tweak the `results.generation.size` to your needs. 

3. Metrics: `http://localhost:8080/actuator/prometheus`
