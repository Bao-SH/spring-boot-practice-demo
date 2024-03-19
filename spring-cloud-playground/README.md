# Getting Started

this module is used to try spring cloud netflix.

we have three submodules to make up a library system
and one module to run as eureka server

the order to run locally should be:
1. run postgres locally
2. run eureka-server 
3. run book-service
4. run transaction-service

you can see the registered client in http://localhost:8080

and you can try to call transaction-service endpoint to update the book info.

the point is: we use service name instead of host to call the book-service endpoint
```java
    public BookResponse updateBookInfo(TransactionRequest transactionRequest) {
        return builder.build().post()
            .uri("http://book-service/books/update")
            .bodyValue(UpdateBookRequest.builder().id(transactionRequest.getBookId())
                .userId(transactionRequest.getUserId().toString())
                .build())
            .retrieve()
            .bodyToMono(BookResponse.class)
            .block();
    }
```

one thing need to mention is somehow in config directory, we can only create bean like this: 
```java
@Configuration
public class WebClientConfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder lbWebClientBuilder() {
        return WebClient.builder();
    }
}
```

if we create bean WebClient directly, the call to book-service service will fail.

see this link:
https://stackoverflow.com/questions/67649680/loadbalanced-webclient-together-with-eureka-webclient-enabled