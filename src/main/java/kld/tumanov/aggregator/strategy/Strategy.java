package kld.tumanov.aggregator.strategy;


import kld.tumanov.aggregator.model.News;
import java.util.List;

public interface Strategy extends Runnable {
    List<News> getNews();
}
