package ua.taras.kushmyruk.service;

import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.taras.kushmyruk.model.User;

public interface UserListService {

  Page<User> getUserList(Pageable pageable);

  void saveUser(String username, User user, Map<String, String> form);

  void blockUser(String username);

  void activeUser(String username);
}
