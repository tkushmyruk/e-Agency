package ua.taras.kushmyruk.service.impl;

import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import ua.taras.kushmyruk.model.User;
import ua.taras.kushmyruk.model.UserRole;
import ua.taras.kushmyruk.repository.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

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

  @Transactional
  public boolean addUser(User user, BindingResult bindingResult) {
    if (bindingResult != null && bindingResult.hasErrors()){
      LOGGER.info("User filled wrong data");
      return false;
    }
    User userFromDb = userRepository.findByUsername(user.getUsername());
    if (userFromDb != null) {
      LOGGER.info("User {} is exists", user.getUsername());
      return false;
    }
    user.setActive(true);
    user.setRoles(Collections.singleton(UserRole.ADMIN));
    userRepository.save(user);
    LOGGER.info("User {} successfully was saved ", user.getUsername());
    return true;
  }

  @Transactional
  public String changePassword(User user, String newPassword, String newEmail) {
    if (user.getPassword().equals(newPassword) && user.getEmail().equals(newEmail)) {
      LOGGER.info("For user {} nothing was changed ", user.getUsername());
      return "Nothing was changed";
    }
    if (!user.getPassword().equals(newPassword) && user.getEmail().equals(newEmail)) {
      user.setPassword(newPassword);
      userRepository.save(user);
      LOGGER.info("For user {} password was successfully changed ", user.getUsername());
      return "Password was successfully changed";
    }
    if (user.getPassword().equals(newPassword) && !user.getEmail().equals(newEmail)) {
      user.setEmail(newEmail);
      userRepository.save(user);
      LOGGER.info("For user {} email was successfully changed ", user.getUsername());
      return "Email was successfully changed";
    }
    if (!user.getPassword().equals(newPassword) && !user.getEmail().equals(newEmail)) {
      user.setPassword(newPassword);
      user.setEmail(newEmail);
      userRepository.save(user);
      LOGGER.info("For user {} password and email was successfully changed", user.getUsername());
      return "Password and email was successfully changed";
    }
    return "Error";
  }
}