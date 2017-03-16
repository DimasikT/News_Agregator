package kld.tumanov.aggregator.web.news;

import kld.tumanov.aggregator.model.News;
import kld.tumanov.aggregator.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(NewsRestController.REST_URL)
public class NewsRestController {
    static final String REST_URL = "/rest/news";
    private static final Logger LOG = LoggerFactory.getLogger(NewsRestController.class);

    @Autowired
    private NewsService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
<<<<<<< HEAD
    public List<News> getAllNews() {
        LOG.info("rest getAll");
        return provider.getNews();
=======
    public List<News> getLast10() {
        LOG.info("Get last 10 news");
        return service.getLast10();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<News> updateList(@PathVariable("id") long id){
        LOG.info("Update news");
        return service.updateList(id);
    }

    @GetMapping(value = "/more/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<News> getMore(@PathVariable("id") long id){
        LOG.info("Get more news");
        return service.getMore(id);
>>>>>>> dev
    }
}
