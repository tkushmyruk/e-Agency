package ua.taras.kushmyruk;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
import ua.taras.kushmyruk.controller.AccountController;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("123")
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql", "/create-tour-before.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-tour-after.sql", "/create-user-after.sql"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class AccountControllerMvcTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private AccountController accountController;

  @Test
  public void contextTest(){
    Assert.assertNotNull(accountController);
  }

  @Test
  public void getAccountTest() throws Exception {
    this.mockMvc.perform(get("/user/123"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(authenticated());
  }

  @Test
  public void getCreditCardTest() throws Exception{
    this.mockMvc.perform(get("/creditCard"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(authenticated())
        .andExpect(content().string(containsString("1234 4444 5555 1234")))
        .andExpect(content().string(containsString("222.22")));
  }

  @Test
  public void getTopUpCardTest() throws Exception{
    this.mockMvc.perform(get("/creditCard/topUp"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(authenticated());
  }

  @Test
  public void postTopUpCardTest() throws Exception {
    this.mockMvc.perform(post("/creditCard/topUp")
        .param("money", "222.22")
        .param("cardPassword", "1234password"))
        .andDo(print())
        .andExpect(content().string(containsString("Your card was successfully replenished")))
        .andExpect(status().isOk());
  }

  @Test
  public void getBoughtTours() throws Exception {
    this.mockMvc.perform(get("/bought-tours"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(authenticated())
        .andExpect(xpath("//*[@id='tours']").nodeCount(1));
  }



}
