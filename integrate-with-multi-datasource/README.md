# steps
## connect to mySql
you need to start mysql locally. Steps can be found in the README.file in the module "integrate-with-mysql"
## config to connect snowflake
please fill in all the related configurations in application.yml.
Including:
```yaml
snowflake:
  account_identifier: ---
  username: ---
  password: ---
  warehouse: ""
  database:
    name: ""
  schema: ""
```