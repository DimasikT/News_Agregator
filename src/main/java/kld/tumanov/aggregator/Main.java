package kld.tumanov.aggregator;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Created by Admin on 02.03.2017.
 */
public class Main {

    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            NewsController controller = appCtx.getBean(NewsController.class);
            controller.getAllNews().forEach(System.out::println);
        }
    }
}
