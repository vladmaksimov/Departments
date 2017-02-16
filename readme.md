# Departments and employees on example httpServlet and jdbc

Simple example of the technologies. We have departments and employees in them. We can create, delete and edit all of them. List of items looks like simple table with pagination.

# Used technologies

On backend:
* httpServlet + jdbc + oval validation

On frontend:
* jsp + jstl + el + bootstrap-css


Maven dependency
* `javax.servlet` - servlet-api version 2.5
* `junit` - junit version 3.8.1
* `mysql` - mysql-connector-java version 5.1.6
* `log4j` - log4j version 1.2.17
* `jstl` - jstl version 1.2
* `net.sf.oval` - oval version 1.84

# Build tools

Maven with compiler plugin 1.7

```
mvn clean
mvn package
```