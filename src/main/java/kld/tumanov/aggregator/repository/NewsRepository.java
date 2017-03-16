package kld.tumanov.aggregator.repository;

import kld.tumanov.aggregator.model.News;

import java.util.List;


public interface NewsRepository {

    void save(News news);

    // false if not found
    boolean delete(long id);

    // null if not found
    News get(long id);

    List<News> getBySite(String url);

    List<News> getAll();

    List<News> getLast10();

    List<News> updateList(long id);

    List<News> getMore(long id);

}
