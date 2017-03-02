package kld.tumanov.aggregator.web.news;

import kld.tumanov.aggregator.strategy.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NewsController {

    @Autowired
    private Provider provider;

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String getAllNews(Model model) {
        model.addAttribute("news", provider.getNews());
        return "news";
    }
}
