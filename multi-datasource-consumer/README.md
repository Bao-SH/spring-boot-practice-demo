# Getting Started
Still, in order to use multi-datasource-provider, you need to firstly publish it to local.

You can find the provider module only contains related config, and that's why we need to config below:
```yaml
multi-datasource:
  repositories:
    mysqlPackage: "com.example.multidatasourceconsumer.repository.mysql"
    snowflakePackage: "com.example.multidatasourceconsumer.repository.snowflake"
  entities:
    snowflakePackage:
      - "com.example.multidatasourceconsumer.entity.product"
    mysqlPackage:
      - "com.example.multidatasourceconsumer.entity.user"
      - "com.example.multidatasourceconsumer.entity.order"

```
remember to start MySql server locally and replace snowflake related config in application.yml.