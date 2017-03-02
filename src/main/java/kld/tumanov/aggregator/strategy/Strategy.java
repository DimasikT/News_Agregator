package kld.tumanov.aggregator.strategy;


import kld.tumanov.aggregator.model.News;
import java.util.List;

public interface Strategy {
    List<News> getNews();
}
