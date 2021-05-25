package ua.taras.kushmyruk.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.taras.kushmyruk.model.User;
import ua.taras.kushmyruk.model.UserRole;
import ua.taras.kushmyruk.repository.UserRepository;
import ua.taras.kushmyruk.service.UserListService;

@Service
public class UserListServiceImpl implements UserListService {
  private final UserRepository userRepository;

  public UserListServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Page<User> getUserList(Pageable pageable) {
    return userRepository.findAll(pageable);
  }

  @Override
  public void saveUser(String username, User user, Map<String, String> form) {
    user.setUsername(username);
    Set<String> roles = Arrays.stream(UserRole.values()).map(UserRole::name).collect(Collectors.toSet());
    user.getRoles().clear();
    for(String key : form.keySet()){
      if(roles.contains(key)){
        user.getRoles().add(UserRole.valueOf(key));
      }
    }
    userRepository.save(user);
  }

  @Override
  public void blockUser(String username){
    User user = userRepository.findByUsername(username);
    user.setActive(false);
    userRepository.save(user);
  }

  @Override
  public void activeUser(String username) {
    System.out.println(username);
    User user = userRepository.findByUsername(username);
    System.out.println(user);
    user.setActive(true);
    userRepository.save(user);
  }
}
