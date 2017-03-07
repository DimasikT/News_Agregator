package kld.tumanov.aggregator.web.news;

import kld.tumanov.aggregator.web.AbstractControllerTest;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class NewsRestControllerTest extends AbstractControllerTest {
    @Test
    public void getAllNews() throws Exception {
        mockMvc.perform(get("/rest/news"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

}