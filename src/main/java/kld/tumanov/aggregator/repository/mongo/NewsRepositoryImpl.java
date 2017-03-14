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
    public List<News> getFromTo(long begin, long end) {
        if (begin == 0 && end == 0) {
            LOG.info("Get last 10 news");
            return crudRepository.findAll().stream()
                    .sorted((n1, n2) -> (n2.getId().compareTo(n1.getId())))
                    .limit(10)
                    .collect(Collectors.toList());

        } else if (end == 0) {
            LOG.info("Get news from " + begin);
            return crudRepository.findByIdGreaterThan(begin);
        } else {
            LOG.info("Get from " + begin + " to " + end);
            return crudRepository.findByIdLessThan(begin).stream().limit(10).collect(Collectors.toList());
        }
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

}
