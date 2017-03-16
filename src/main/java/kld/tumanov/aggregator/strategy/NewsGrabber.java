package kld.tumanov.aggregator.strategy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class NewsGrabber {

    @Value("#{strategies}")
    private List<Strategy> strategies;

    @PostConstruct
    public void init(){
        for (Strategy strategy : strategies) {
            Thread t = new Thread(strategy);
            t.setDaemon(true);
            t.start();
        }
    }
}
