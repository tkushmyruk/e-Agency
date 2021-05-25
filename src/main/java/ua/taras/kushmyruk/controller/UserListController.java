package ua.taras.kushmyruk.controller;

import java.util.Map;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.taras.kushmyruk.model.User;
import ua.taras.kushmyruk.model.UserRole;
import ua.taras.kushmyruk.service.UserListService;
import ua.taras.kushmyruk.service.impl.UserServiceImpl;

@Controller
public class UserListController {
  private final UserListService userListService;
  private final UserServiceImpl userService;

  public UserListController(UserListService userListService,
      UserServiceImpl userService) {
    this.userListService = userListService;
    this.userService = userService;
  }

  @GetMapping("/user-list")
  public String getUserList(Model model,
      @PageableDefault(sort = {"id"}, direction = Direction.DESC)Pageable pageable){
    model.addAttribute("page", userListService.getUserList(pageable));
    model.addAttribute("url", "/user-list");
    return "userList";
  }

  @GetMapping("/user-list/{username}")
  public String editUser(@PathVariable String username, Model model){
    model.addAttribute("user", userService.loadUserByUsername(username));
    model.addAttribute("roles", UserRole.values());
    return "userEdit";
  }

  @PostMapping("/user-list/{username}")
  public String saveUser(@PathVariable String username,
      @RequestParam("userId") User user,
      @RequestParam Map<String, String> form
  ){
    userListService.saveUser(username, user, form);
    return "redirect:/user-list";
  }

  @PostMapping("/user-block")
  public String blockUser(@RequestParam String username,
      Model model, @PageableDefault(sort = {"id"}, direction = Direction.DESC)Pageable pageable
      ){
    userListService.blockUser(username);
    model.addAttribute("page", userListService.getUserList(pageable));
    model.addAttribute("url", "/user-block");
    return "userList";
  }

  @PostMapping("/user-active")
  public String activeUser(@RequestParam String username,
      Model model, @PageableDefault(sort = {"id"}, direction = Direction.DESC)Pageable pageable){
    userListService.activeUser(username);
    model.addAttribute("page", userListService.getUserList(pageable));
    model.addAttribute("url", "/user-active");
    return "userList";
  }



}
