package kld.tumanov.aggregator.service;


import kld.tumanov.aggregator.model.News;

import java.util.List;

public interface NewsService {
    List<News> save(List<News> news);

    boolean delete(int id);

    News get(int id);

    List<News> getFromTo(int begin, int end);

    List<News> getBySite(String site);


}
