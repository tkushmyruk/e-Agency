package ua.taras.kushmyruk.service.impl;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.taras.kushmyruk.model.User;
import ua.taras.kushmyruk.model.UserRole;
import ua.taras.kushmyruk.repository.UserRepository;
import ua.taras.kushmyruk.service.UserListService;

@Service
public class UserListServiceImpl implements UserListService {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserListServiceImpl.class);
  private final UserRepository userRepository;

  public UserListServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Page<User> getUserList(Pageable pageable) {
    return userRepository.findAll(pageable);
  }

  @Override
  @Transactional
  public void saveUser(String username, User user, Map<String, String> form) {
    user.setUsername(username);
    Set<String> roles = Arrays.stream(UserRole.values()).map(UserRole::name).collect(Collectors.toSet());
    user.getRoles().clear();
    for(String key : form.keySet()){
      if(roles.contains(key)){
        user.getRoles().add(UserRole.valueOf(key));
      }
    }
    LOGGER.info("Changes for {} was successfully saved ", user.getUsername());
    userRepository.save(user);
  }

  @Override
  @Transactional
  public void blockUser(String username){
    User user = userRepository.findByUsername(username);
    user.setActive(false);
    LOGGER.info("User {} was blocked", username );
    userRepository.save(user);
  }

  @Override
  @Transactional
  public void activeUser(String username) {
    User user = userRepository.findByUsername(username);
    user.setActive(true);
    LOGGER.info("User {} was activated", username );
    userRepository.save(user);
  }
}
