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


@Repository
public class InMemoryNewsRepositoryImpl implements NewsRepository {
    private static final Logger LOG = getLogger(InMemoryNewsRepositoryImpl.class);
    private Map<Integer, News> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);


    @Override
    public boolean delete(int id) {
        News news = repository.remove(id);
        LOG.info(news==null ? "Don't remove news, news not found." : "delete" + news.toString());
        return news != null;
    }

    @Override
    public List<News>  save(List<News> news) {
        for (News n : news) {
            n.setId(counter.incrementAndGet());
            repository.put(n.getId(), n);
            LOG.info("save " + n.getId() + " - "+ n.getTitle());
        }
        System.out.println(repository.values().size());
        return news;
    }

    @Override
    public News get(int id) {
        News news = repository.values().stream().filter(u->u.getId()==id).findFirst().orElse(null);
        LOG.info(news==null ? "Don't get news, news not found." : "get" + news);
        return news;
    }

    @Override
    public List<News> getFromTo(int begin, int end) {
        if (begin == 0 && end == 0) {
            LOG.info("Get last 10 news");
            return repository.values().stream().sorted((n1, n2) -> (n2.getId().compareTo(n1.getId()))).limit(10l).collect(Collectors.toList());
        } else if (end == 0){
            LOG.info("Get news from " + begin);
            return repository.values().stream().filter(n -> n.getId() > begin).collect(Collectors.toList());
        } else {
            LOG.info("Get from " + begin +  " to " + end);
            return repository.values().stream().filter(n -> (n.getId() > begin && n.getId() < end)).collect(Collectors.toList());
        }
    }

    @Override
    public List<News> getBySite(String site) {
        return repository.values().stream().filter(n -> n.getSite().equals(site)).collect(Collectors.toList());
    }
}
