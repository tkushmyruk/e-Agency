package ua.taras.kushmyruk.service.impl;

import java.util.Collections;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.taras.kushmyruk.model.User;
import ua.taras.kushmyruk.model.UserRole;
import ua.taras.kushmyruk.repository.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username);
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public boolean addUser(String username, String password, String email) {
    User userFromDb = userRepository.findByUsername(username);
    if (userFromDb != null && username.isEmpty()) {
      return false;
    }
    User user = new User();
    user.setUsername(username);
    user.setPassword(password);
    user.setEmail(email);
    user.setActive(true);
    user.setRoles(Collections.singleton(UserRole.ADMIN));
    userRepository.save(user);
    return true;
  }

  public String changePassword(User user, String newPassword, String newEmail) {
    if (user.getPassword().equals(newPassword) && user.getEmail().equals(newEmail)) {
      return "Nothing was changed";
    }
    if (!user.getPassword().equals(newPassword) && user.getEmail().equals(newEmail)) {
      user.setPassword(newPassword);
      userRepository.save(user);
      return "Password was successfully changed";
    }
    if (user.getPassword().equals(newPassword) && !user.getEmail().equals(newEmail)) {
      user.setEmail(newEmail);
      userRepository.save(user);
      return "Email was successfully changed";
    }
    if (!user.getPassword().equals(newPassword) && !user.getEmail().equals(newEmail)) {
      user.setPassword(newPassword);
      user.setEmail(newEmail);
      userRepository.save(user);
      return "Password and email was successfully changed";
    }
    return "Error";
  }
}