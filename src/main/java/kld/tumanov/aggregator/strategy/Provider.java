package kld.tumanov.aggregator.strategy;


import kld.tumanov.aggregator.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Provider {

    @Autowired
    private Strategy strategy;

    public Provider(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public List<News> getNews(){
        return strategy.getNews();
    }
}
