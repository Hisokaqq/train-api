### TrainStation API

This is the TrainStation API which supports full CRUD operations for managing trains, schedules, stations, routes, and tickets.

### Postman Link

You can explore and test the API using the following Postman workspace:

[TrainStation API Postman Workspace](https://www.postman.com/lunar-module-technologist-42827856/workspace/trainstation/overview)

### Database

The API uses PostgreSQL as the database.

### application.properties

Below is an example of the `application.properties` file that you need to configure for connecting to the PostgreSQL database:

```properties
spring.application.name=trainss
spring.datasource.url=jdbc:postgresql://localhost:5432/jwt_sequrity
spring.datasource.username=john
spring.datasource.password=123
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

### Setting Up the Project

#### Prerequisites

- Java 17
- Maven
- PostgreSQL

#### Steps to Set Up

1. **Clone the repository:**

   ```sh
   git clone <repository-url>
   cd trainstation-api
   ```

2. **Create a PostgreSQL database:**

   ```sql
   CREATE DATABASE jwt_sequrity;
   ```

3. **Configure the database:**

   Update the `application.properties` file with your PostgreSQL database details.

4. **Build the project:**

   ```sh
   mvn clean install
   ```

5. **Run the project:**

   ```sh
   mvn spring-boot:run
   ```

### pom.xml

Below is the `pom.xml` configuration for the project:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.6</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>alexburtyn.com</groupId>
    <artifactId>trainss</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>trainss</name>
    <description>TrainStation</description>
    <properties>
        <java.version>17</java.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.checkerframework</groupId>
            <artifactId>checker-qual</artifactId>
            <version>2.10.0</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>0.12.3</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>0.12.3</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <version>0.12.3</version>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

