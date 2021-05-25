package ua.taras.kushmyruk;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import ua.taras.kushmyruk.controller.RegistrationController;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-user-after.sql"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class RegistrationControllerMvcTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private RegistrationController registrationController;

  @Test
  public void contextTest(){
    Assert.assertNotNull(registrationController);
  }

  @Test
  public void getLoginPageTest() throws Exception {
    this.mockMvc.perform(get("/login"))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void getRegistrationPageTest() throws Exception {
    this.mockMvc.perform(get("/registration"))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  @WithUserDetails("123")
  public void changePasswordTest() throws Exception{
    this.mockMvc.perform( post("/change-password")
        .param("password", "123").param("email", "loter98@gmail.com"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("Nothing was changed")));
  }

  @Test
  public void redirectToLoginPageIfNotAuthorizedTest() throws Exception {
    this.mockMvc.perform(get("/change-password"))
        .andDo(print())
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("http://localhost/login"));
  }

  @Test
  public void loginTest() throws Exception {
    this.mockMvc.perform(formLogin().user("123").password("123"))
    .andDo(print())
    .andExpect(status().is3xxRedirection())
    .andExpect(redirectedUrl("/"));
  }

  @Test
  public void badCredentials() throws Exception {
    this.mockMvc.perform(post("/login").param("username", "wrongPassword"))
        .andDo(print())
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/login?error=true"));
  }


}
