package kld.tumanov.aggregator.service;


import kld.tumanov.aggregator.model.News;

import java.util.List;

public interface NewsService {
    List<News> save(List<News> news);

    boolean delete(int id);

    News get(int id);

    List<News> getLast10();

    List<News> updateList(long id);

    List<News> getMore(long id);

    List<News> getBySite(String site);


}
