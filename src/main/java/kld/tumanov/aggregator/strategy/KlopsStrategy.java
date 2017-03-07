package kld.tumanov.aggregator.strategy;


import kld.tumanov.aggregator.model.News;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class KlopsStrategy implements Strategy {

    private static final String URL_FORMAT = "http://klops.ru/news?page=1";

    @Override
    public List<News> getNews() {
        List<News> allNews = new ArrayList<>();
        try {
                Document newsList = getDocument(URL_FORMAT);
                Elements elements = newsList.select("[class=b-main-news__item]");
                if (elements.isEmpty()) {
                    return allNews;
                }
                for (Element element : elements){
                    String link = element.select("[class=b-link]").first().attr("href");
                    Document news = getDocument(link);
                    Element title = news.select("title").first();
                    String name = title.text();
                    String text = "";

                    Elements textBlocks = news.select("[style=text-align: justify;]");
                    for (Element e : textBlocks){
                        text += e.text() + "\n";
                    }

                    News n = new News();
                    n.setTitle(name);
                    n.setText(text);
                    n.setUrl(link);
                    allNews.add(n);
                }
        }catch (IOException ignore) {

        }
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
}
