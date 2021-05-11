package engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.annotation.PreDestroy;

/*
 * useful links:
 * Using databases:
 * https://www.javaguides.net/2019/08/spring-boot-crud-rest-api-spring-data-jpa-h2-database-example.html
 * https://attacomsian.com/blog/spring-data-jpa-h2-database
 *
 * Spring security:
 * The best description in Russian!!!
 * https://habr.com/ru/company/otus/blog/488418/
 * https://habr.com/ru/post/482552/
 *
 * also good in English:
 * https://www.marcobehler.com/guides/spring-security
 *
 *
 * About pagination and sorting:
 * https://howtodoinjava.com/spring-boot2/pagination-sorting-example/
 *
 */


@SpringBootApplication
public class WebQuizEngine {

    public static void main(String[] args) {
        SpringApplication.run(WebQuizEngine.class, args);
    }
}
