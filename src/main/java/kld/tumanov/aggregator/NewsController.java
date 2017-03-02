package kld.tumanov.aggregator;




import java.util.ArrayList;

import kld.tumanov.aggregator.model.News;
import kld.tumanov.aggregator.strategy.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NewsController {

    @Autowired
    private Provider provider;

    public NewsController(Provider... providers) {

    }


    public List<News> getAllNews() {
        List<News> news = new ArrayList<>();
            news.addAll(provider.getNews());
        return news;
    }
}
