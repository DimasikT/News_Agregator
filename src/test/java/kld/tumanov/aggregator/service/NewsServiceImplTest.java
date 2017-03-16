package kld.tumanov.aggregator.service;

import kld.tumanov.aggregator.model.News;
import kld.tumanov.aggregator.repository.NewsRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Admin on 14.03.2017.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class NewsServiceImplTest {

    @Autowired
    private NewsRepository repository;

    private long count = 5;


    @Before
    public void init() {
        for (long i = 0; i < count; i++) {
            News news = new News();
            news.setId(i);
            news.setTitle("Test news " + i);
            news.setText("Bla bla bla " + i);
            news.setIcon("no icon");
            news.setImage("No image");
            news.setUrl("mysite.ru");
            repository.save(news);
        }
    }

    @After
    public void clear(){
        for (long i = 1; i < count; i++) {
            repository.delete(i);
        }
    }

    @Test
    public void get() throws Exception {
        News news = repository.get(2);
        assertEquals(news.getText(), "Bla bla bla 2");
        assertEquals(news.getTitle(), "Test news 2");

    }

    @Test
    public void getBySite() throws Exception {
        List<News> news = repository.getBySite("mysite.ru");
        assertEquals(5, news.size());
    }

    @Test
    public void getAll() throws Exception{
        List<News> news = repository.getAll();
        assertEquals(5, news.size());
    }



}