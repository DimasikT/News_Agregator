package kld.tumanov.aggregator.repository;

import kld.tumanov.aggregator.model.News;

import java.util.List;


public interface NewsRepository {

    List<News> save(List<News> news);

    // false if not found
    boolean delete(int id);

    // null if not found
    News get(int id);

    List<News> getFromTo(int begin, int end);

    List<News> getBySite(String site);
}
