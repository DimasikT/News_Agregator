package kld.tumanov.aggregator.web.news;


import kld.tumanov.aggregator.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NewsController {

    @Autowired
    private NewsService service;

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String getNews(Model model) {
        model.addAttribute("news", service.getFromTo(0, 0));
        return "news";
    }
}
