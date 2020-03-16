# Latency Troubleshooting App.

## USAGE:

### latency-troubleshooter-app
1. **Usage:** `http://localhost:8080/api/generate/<response-size>`  
   e.g. `http://localhost:8080/api/generate/200` generates a response with 200 items in JSON format.
   
2. Metrics: `http://localhost:8080/actuator/prometheus`

3. Search for `generateJSON` in `http://localhost:8080/actuator/prometheus` to find the execution time statistics.
