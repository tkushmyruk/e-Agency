package ua.taras.kushmyruk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.taras.kushmyruk.model.User;

@Controller
public class MainController {
  private static final Logger logger = LoggerFactory.getLogger(MainController.class);

  @GetMapping("/")
  public String mainPage(@AuthenticationPrincipal User user, Model model){
    logger.info("Main page");
    model.addAttribute("user", user);
    return "main";
  }


}
