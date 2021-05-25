package ua.taras.kushmyruk;

import java.util.Collections;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ua.taras.kushmyruk.model.User;
import ua.taras.kushmyruk.model.UserRole;
import ua.taras.kushmyruk.repository.UserRepository;
import ua.taras.kushmyruk.service.impl.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
  @Autowired
  private UserServiceImpl userService;

  @MockBean
  private UserRepository userRepository;

  @Test
  public void addUserTest(){
    boolean isUserCreated = userService
        .addUser("user", "password", "email@gmail.com");

    Assert.assertTrue(isUserCreated);
  }


  @Test
  public void changePasswordTest(){
    User user = new User();
    user.setUsername("username");
    user.setPassword("password");
    user.setEmail("loter98@gmail.com");
    user.setActive(true);
    user.setRoles(Collections.singleton(UserRole.USER));

    String nothingChange = userService
        .changePassword(user, "password", "loter98@gmail.com");
    Assert.assertEquals("Nothing was changed", nothingChange);

    String passwordChange = userService
        .changePassword(user, "newPassword", "loter98@gmail.com");
    Assert.assertEquals("Password was successfully changed", passwordChange);

    String emailChange = userService
        .changePassword(user, "newPassword", "newEmail@gmail.com");
    System.out.println(emailChange);
    Assert.assertEquals("Email was successfully changed", emailChange);

    String passwordAndEmailChange = userService
        .changePassword(user, "newPassword2", "newEmail@gmail.com2");
    Assert.assertEquals("Password and email was successfully changed", passwordAndEmailChange);
  }

}