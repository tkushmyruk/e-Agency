package ua.taras.kushmyruk;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.taras.kushmyruk.controller.MainController;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerMvcTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private MainController mainController;

  @Test
  public void contextTest(){
    Assert.assertNotNull(mainController);
  }

  @Test
  public void mainPageTest() throws Exception {
    this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk());
  }

}
