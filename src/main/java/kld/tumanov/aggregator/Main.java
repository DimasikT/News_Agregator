package kld.tumanov.aggregator;

import kld.tumanov.aggregator.repository.NewsRepository;
import kld.tumanov.aggregator.repository.mongo.NewsRepositoryImpl;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Created by Admin on 02.03.2017.
 */
public class Main {

    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-mvc.xml", "spring/spring-app.xml")) {

            NewsRepository repo = appCtx.getBean(NewsRepositoryImpl.class);
            System.out.println(repo.getBySite("mysite.ru"));

        }
    }
}
