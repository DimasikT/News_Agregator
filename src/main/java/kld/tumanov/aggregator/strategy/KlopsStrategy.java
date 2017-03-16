package kld.tumanov.aggregator.strategy;


import kld.tumanov.aggregator.model.News;
import kld.tumanov.aggregator.service.NewsService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component("KlopsStrategy")
public class KlopsStrategy implements Strategy {

    private static final String URL_FORMAT = "http://klops.ru/news?page=1";
    private static final String URL_HOME = "http://klops.ru";

    private String lastNews;
    private String tmp;

    @Autowired
    private NewsService service;

    @Override
    public List<News> getNews() {
        List<News> allNews = new ArrayList<>();
        Document newsList;
        try {
            newsList = getDocument(URL_FORMAT);
        }catch (IOException e){
            e.printStackTrace();
            return allNews;
        }

            Elements items = newsList.select("[class=b-main-news__item]");
            if (items.isEmpty()) {
                return allNews;
            }
            for (Element item : items) {
                String icon = URL_HOME + item.select("[class=b-icon]").first().attr("src");
                String url = item.select("[class=b-link]").first().attr("href");

                Document news;
                try {
                    news = getDocument(url);
                }catch (IOException ignore){ continue; }

                String title = news.select("title").first().text();

                if (checkNews(title)){
                    break;
                }

                Element el = news.select("[rel='image_src']").first();
                String image;
                if (el != null){
                    image = el.attr("href");
                } else {
                    image = "no image";
                }


                Elements textBlocks = news.select("[style=text-align: justify;]");
                String text = "";
                for (Element e : textBlocks) {
                    text += e.text() + "\n";
                }

                News n = new News();
                n.setTitle(title);
                n.setText(text);
                n.setIcon(icon);
                n.setImage(image);
                n.setUrl(url);
                allNews.add(n);
            }

        tmp = null;
        Collections.reverse(allNews);
        return allNews;
    }

    protected Document getDocument(String url) throws IOException {
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 YaBrowser/16.6.1.30165 Yowser/2.5 Safari/537.36";
        String referrer = "https://hh.ua/search/vacancy?text=java+%D0%BA%D0%B8%D0%B5%D0%B2";
        Connection connection = Jsoup.connect(url);
        connection.userAgent(userAgent);
        connection.referrer(referrer);

        return connection.get();
    }

    //attention!!! govnokod ))
    private boolean checkNews(String name){
        if (lastNews != null) {
            if(name.equals(lastNews)){
                if (tmp != null) {
                    lastNews = tmp;
                    tmp = null;
                }
                return true;
            } else if (tmp == null) {
                tmp = name;
            }
        } else {
            lastNews = name;
        }
        return false;
    }

    @Override
    public void run() {
        while (true){
            service.save(getNews());
            try {
                Thread.sleep(120_000); //2 minutes
            } catch (InterruptedException e) {
                continue;
            }
        }
    }
}
