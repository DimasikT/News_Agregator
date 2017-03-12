package kld.tumanov.aggregator.repository.mock;

import kld.tumanov.aggregator.model.News;
import kld.tumanov.aggregator.repository.NewsRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;


@Repository("MockRepository")
public class InMemoryNewsRepositoryImpl implements NewsRepository {
    private static final Logger LOG = getLogger(InMemoryNewsRepositoryImpl.class);
    private Map<Long, News> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);


    @Override
    public boolean delete(long id) {
        News news = repository.remove(id);
        LOG.info(news == null ? "Don't remove news, news not found." : "delete" + news.toString());
        return news != null;
    }

    @Override
    public void save(News news) {
        news.setId((long) counter.incrementAndGet());
        repository.put(news.getId(), news);
    }

    @Override
    public News get(long id) {
        News news = repository.values().stream().filter(u -> u.getId() == id).findFirst().orElse(null);
        LOG.info(news == null ? "Don't get news, news not found." : "get" + news);
        return news;
    }

    @Override
    public List<News> getFromTo(long begin, long end) {
        if (begin == 0 && end == 0) {
            LOG.info("Get last 10 news");
            return repository.values().stream().sorted((n1, n2) -> (n2.getId().compareTo(n1.getId()))).limit(10l).collect(Collectors.toList());
        } else if (end == 0) {
            LOG.info("Get news from " + begin);
            return repository.values().stream().filter(n -> n.getId() > begin).collect(Collectors.toList());
        } else {
            LOG.info("Get from " + begin + " to " + end);
            return repository.values().stream().filter(n -> (n.getId() > begin && n.getId() < end)).collect(Collectors.toList());
        }
    }

    @Override
    public List<News> getBySite(String url) {
        return repository.values().stream().filter(n -> n.getUrl().equals(url)).collect(Collectors.toList());
    }
}
