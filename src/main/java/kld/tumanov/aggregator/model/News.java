package kld.tumanov.aggregator.model;

import java.util.Objects;

/**
 * Created by Admin on 02.03.2017.
 */
public class News {

    private Integer id;

    private String title;

    private String text;

    private String image;

    private String site;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(id, news.id) &&
                Objects.equals(title, news.title) &&
                Objects.equals(text, news.text) &&
                Objects.equals(image, news.image) &&
                Objects.equals(site, news.site);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, text, image, site);
    }

    @Override
    public String toString() {
        return "News:\n\t" + title + "\n" +
                "Text:\n\t" + text + "\n";
    }
}
