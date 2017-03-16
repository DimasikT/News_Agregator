package kld.tumanov.aggregator.repository.mongo;

import kld.tumanov.aggregator.model.News;
import kld.tumanov.aggregator.repository.NewsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Admin on 12.03.2017.
 */

@Repository
public class NewsRepositoryImpl implements NewsRepository {
    private static final Logger LOG = LoggerFactory.getLogger(NewsRepositoryImpl.class);

    @Autowired
    private CrudNewsRepository crudRepository;

    @Override
    public void save(News news) {
        LOG.info("Save news: " + news);
        crudRepository.save(news);
    }

    @Override
    public boolean delete(long id) {
        LOG.info("Delete news with id - " + id);
        crudRepository.delete(id);
        return false ;
    }

    @Override
    public News get(long id) {
        LOG.info("Get news with id - " + id);
        return crudRepository.findOne(id);
    }


    @Override
    public List<News> getBySite(String url) {
        LOG.info("Get news with url - " + url);
        return crudRepository.findByUrlOrderByIdDesc(url);
    }

    @Override
    public List<News> getAll() {
        return crudRepository.findAll();
    }

    @Override
    public List<News> getLast10() {
        LOG.info("Get last 10 news");
        return crudRepository.findAll().stream()
                .sorted((n1, n2) -> (n2.getId().compareTo(n1.getId())))
                .limit(10)
                .collect(Collectors.toList());
    }

    @Override
    public List<News> updateList(long id) {
        LOG.info("Update news");
        return crudRepository.findByIdGreaterThan(id);
    }

    @Override
    public List<News> getMore(long id) {
        LOG.info("Get more news");
        return crudRepository.findByIdLessThan(id).stream().limit(10).collect(Collectors.toList());
    }

}
