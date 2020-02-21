# Latency Troubleshooting App.

## USAGE:

### latency-troubleshooter-app
1. `results.generation.size` in `application.properties` controls the count of `Response` items in the JSON response.   This value also controls the service execution time with `results.generation.size = 100000` resulting in `~ 2000ms` execution time.

2. tweak the `results.generation.size` to your needs. 
