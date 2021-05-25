package ua.taras.kushmyruk;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.taras.kushmyruk.controller.TourController;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("123")
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql", "/create-tour-before.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-tour-after.sql", "/create-user-after.sql"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class TourControllerMvcTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private TourController tourController;

  @Test
  public void contextTest(){
    Assert.assertNotNull(tourController);
  }

  @Test
  public void getCatalogTest() throws Exception {
    this.mockMvc.perform(get("/catalog"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(authenticated())
        .andExpect(xpath("//*[@id='tour-list']").nodeCount(2));
  }

  @Test
  public void getAddTourTest() throws Exception {
    this.mockMvc.perform(get("/tour/add"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(authenticated());
  }

  @Test
  public void addTourTest() throws Exception {
    this.mockMvc.perform(post("/tour/add")
    .param("tourName", "Best Tour")
    .param("countOfPeople", "2")
    .param("price", "250.00")
    .param("startDate", "2021-12-21")
    .param("endDate", "2021-12-28")
    .param("departingFrom", "Kiev")
    .param("country", "Turkey")
    .param("locality", "Kumur")
    .param("tourType", "REST")
    .param("roomType", "STANDART")
    .param("hotelStars", "THREE")
    .param("hotelName", "Grand Hotel")
    .param("isAllInclusive", "allInclusive")
    .param("isHot", "hot"))
    .andDo(print())
    .andExpect(status().is3xxRedirection())
    .andExpect(redirectedUrl("/catalog"));

  }

}
