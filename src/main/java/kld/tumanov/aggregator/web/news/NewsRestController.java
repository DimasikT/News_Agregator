package kld.tumanov.aggregator.web.news;

import kld.tumanov.aggregator.model.News;
import kld.tumanov.aggregator.strategy.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;


@Controller
public class NewsRestController {

    @Autowired
    private Provider provider;

    public List<News> getAllNews() {
        List<News> news = new ArrayList<>();
        news.addAll(provider.getNews());
        return news;
    }
}
