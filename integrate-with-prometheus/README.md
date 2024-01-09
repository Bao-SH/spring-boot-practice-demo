after running locally, you can see the endpoints like: 
- http://localhost:8080/actuator/
- http://localhost:8080/actuator/prometheus

and you can see the custom Gauge you defined in CustomPrometheusConfig file. (search the name)
This Gauge will invoke **in schedule**. And you only need to expose it in any bean.

# integrate with Prometheus and Grafana
1 run Prometheus and Grafana locally
```brew install prometheus```
&&
```brew install grafana```

config prometheus to monitor your spring boot service:
```yaml
global:
  scrape_interval: 15s

scrape_configs:
  - job_name: "prometheus"
    metrics_path: '/actuator/prometheus'
    static_configs:
    - targets: ["localhost:8080"]
```
run locally
```brew services start prometheus```
```brew services start grafana```

config grafana to set datasource on localhost:3000
default username and password is admin / admin 
set datasource and dashboard

# tutorial address
overall tutorial: https://medium.com/simform-engineering/revolutionize-monitoring-empowering-spring-boot-applications-with-prometheus-and-grafana-e99c5c7248cf
prometheus: https://prometheus.io/docs/prometheus/latest/getting_started/#configure-prometheus-to-monitor-the-sample-targets
grafana: https://prometheus.io/docs/visualization/grafana/
micrometer: https://docs.micrometer.io/micrometer/reference/concepts/distribution-summaries.html

# timer 
timer can be created by exposing a Timer bean, or use @Timed annotation and spring AOP. 