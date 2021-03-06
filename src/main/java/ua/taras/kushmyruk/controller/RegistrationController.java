package ua.taras.kushmyruk.controller;

import java.util.Map;
import javax.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.taras.kushmyruk.model.User;
import ua.taras.kushmyruk.service.impl.UserServiceImpl;

@Controller
public class RegistrationController {

  private UserServiceImpl userService;

  public RegistrationController(UserServiceImpl userService) {
    this.userService = userService;
  }

  @GetMapping("/login")
  public String getLoginPage(){
    return "login";
  }

  @GetMapping("/registration")
  public String getRegistrationPage(){
    return "registration";
  }

  @GetMapping("/change-password")
  public String getChangePasswordPage(@AuthenticationPrincipal User user,
      Model model){
    model.addAttribute("user", user);
    return "changePassword";
  }

  @PostMapping("/registration")
  public String addUser(@Valid User user,
      BindingResult bindingResult,
      Model model
  ){
      Map<String, String> errorsMap = ControllerUtil.getErrors(bindingResult);
      model.mergeAttributes(errorsMap);
    return userService.addUser(user, bindingResult) ? "redirect:/login" : "registration";
  }

  @PostMapping("/change-password")
  public String changePassword(@AuthenticationPrincipal User user,
  @RequestParam String password,
  @RequestParam String email,
  Model model){
    model.addAttribute("message", userService.changePassword(user, password, email));
    model.addAttribute("user", user);
    return "changePassword";
  }

}
