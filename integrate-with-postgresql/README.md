this module integrates with postgresql as well as redis for client caching.
to run this locally:
1 you need start the postgresql:
```brew service postgresql
```
in order to get connected to the postgres, you need to run 
```psql [your database]```
for example, in this tutorial, use psql sales

2 you need start redis locally:
```redis-stack-server```
and you can connect to the server by:
```redis-cli -h 127.0.0.1 -p 6379```

3 if cause error when call api, some error like cannot deserialize
try to run inside the redis:
```flushall```

4 you need @Data annotation to override Equals method in Sales.java, so that integration test can pass
5 @Getter annotation is needed in Sales.java to get the response of http request.