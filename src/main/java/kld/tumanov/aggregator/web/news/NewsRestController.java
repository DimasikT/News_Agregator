package kld.tumanov.aggregator.web.news;

import kld.tumanov.aggregator.model.News;
import kld.tumanov.aggregator.strategy.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/rest/news")
public class NewsRestController {
    private static final Logger LOG = LoggerFactory.getLogger(NewsRestController.class);

    @Autowired
    private Provider provider;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<News> getAllNews() {
        LOG.info("rest getAll");
        return provider.getNews();
    }
}
