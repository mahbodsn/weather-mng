This project uses the www.visualcrossing.com website to fetch data about weather.
I used spring boot 2.7, docker-compose, Ehcache for in-memory caching for better performance, and Prometheus for metrics.
Because you mentioned in my task that the ecosystem could be microservice, I created a modular spring boot application containing weather-mng and weather-mng-spec modules. The spec module is a lightweight module that contains everything that someday we may want to share between microservices as a dependency, so we call services using a contract, and we don't have to import the whole project when we want to call this service from other services.
I created some simple metrics about how our customers call our APIs and how they usually want to interact with this API by city or coordinates.
Also, I implemented in-memory caching using the EhCache library in order to reduce response time and not call API every time for duplicate requests. Each request cache for 5 minutes.
In order to have Prometheus and MongoDB up and running, I created a docker-compose so the new developer only needs to have docker and run `docker-compose up -d` so we don't have to install database or dependencies.
You can run the project using `cd weather-mng & mvn spring-boot:run`; it uses an 8080 port.
Swagger is also available at `http://localhost:8080/api/swagger-ui/index.html`.
In my implementation, I used a combination of strategy patterns and factory patterns with the use of spring IoC containers.
you see the `WeatherSearchFactory` class that is responsible for choosing implementation based on the users' input. This class returns an implementation that implements `WeatherSearchStrategy` with two methods. One is the `search` method, and the other one returns `searchType`, which is an enum with two values, `coordinate` and `city.`
This implementation uses some best practices and does not violate the `SOLID` pattern. New implementations can implement the `WeatherSearchStrategy` interface and let spring boot do the magic.
In order to fetch data, I have only 1 API, `search.` this API is an abstract API that accepts client input and returns data as they want exclusively. Using the below curl, you can send requests and test applications.
``` 
curl --request POST \
  --url http://localhost:8080/api/weather/search \
  --header 'Content-Type: application/json' \
  --cookie JSESSIONID=F0C312A1576DD6051945826C0C441640 \
  --data '{
    "searchType": "CITY", // required
    "timeCriteria": {
       "from": 1684609893000, // required
       "to": 1685126036000 // required
    },
    "cityName": "Los Angeles,"// required when you want to search by cityName
    "coordinate": { // required when you want to search by coordinates
       "latitude": 51,
       "longitude": 35
    }
}
```
I wrote 4 unit tests for application that tested critical functionalities.