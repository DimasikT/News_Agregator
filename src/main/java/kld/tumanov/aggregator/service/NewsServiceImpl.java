package kld.tumanov.aggregator.service;

import kld.tumanov.aggregator.model.News;
import kld.tumanov.aggregator.model.Sequence;
import kld.tumanov.aggregator.repository.NewsRepository;
import kld.tumanov.aggregator.repository.SequenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository repository;

    @Autowired
    private SequenceRepository sequence;

    @Override
    public List<News> save(List<News> news) {
        for (News n : news) {
            n.setId(sequence.getNextSequenceId(News.COLLECTION_NAME));
            repository.save(n);
        }
        return news;
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }

    @Override
    public News get(int id) {
        return repository.get(id);
    }

    @Override
    public List<News> getFromTo(int begin, int end) {
        return repository.getFromTo(begin, end);
    }

    @Override
    public List<News> getBySite(String url) {
        return repository.getBySite(url);
    }
}
