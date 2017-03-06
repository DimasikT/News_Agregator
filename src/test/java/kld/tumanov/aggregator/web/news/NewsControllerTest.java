package kld.tumanov.aggregator.web.news;

import kld.tumanov.aggregator.web.AbstractControllerTest;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;



public class NewsControllerTest extends AbstractControllerTest {
    @Test
    public void getAllNews() throws Exception {
        mockMvc.perform(get("/news"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("news"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/news.jsp"))
                .andExpect(model().attribute("news", hasSize(20)));
//                .andExpect(model().attribute("users", hasItem(
//                        allOf(
//                                hasProperty("id", is(START_SEQ)),
//                                hasProperty("name", is(USER.getName()))
//                        )
//                )));

    }

}