package ua.taras.kushmyruk;

import java.util.Collections;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ua.taras.kushmyruk.model.User;
import ua.taras.kushmyruk.model.UserRole;
import ua.taras.kushmyruk.repository.CreditCardRepository;
import ua.taras.kushmyruk.repository.UserRepository;
import ua.taras.kushmyruk.service.CreditCardService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditCardServiceTest {

  @Autowired
  private CreditCardService creditCardService;

  @MockBean
  private  CreditCardRepository creditCardRepository;

  @MockBean
  private  UserRepository userRepository;

  @Test
  public void addCardTest(){
    User user = new User();
    user.setUsername("username");
    user.setPassword("password");
    user.setEmail("loter98@gmail.com");
    user.setActive(true);
    user.setRoles(Collections.singleton(UserRole.USER));
    creditCardService.addCard(user, "1222 2222 2222 2222", "1234");
  }

}
