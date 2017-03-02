package kld.tumanov.aggregator.web;

import kld.tumanov.aggregator.NewsController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Admin on 02.03.2017.
 */
public class NewsServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(NewsServlet.class);
    private WebApplicationContext wac;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("redirect to news list");

        NewsController controller = wac.getBean(NewsController.class);

        req.setAttribute("news", controller.getAllNews());
        req.getRequestDispatcher("/news.jsp").forward(req, resp);

    }

    @Override
    public void init() throws ServletException {
        super.init();
        wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    }
}
