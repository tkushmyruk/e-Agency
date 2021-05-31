package ua.taras.kushmyruk.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.taras.kushmyruk.model.User;
import ua.taras.kushmyruk.service.CreditCardService;
import ua.taras.kushmyruk.service.TourService;
import ua.taras.kushmyruk.service.impl.UserServiceImpl;

@Controller
public class AccountController {
  private final UserServiceImpl userService;
  private final CreditCardService creditCardService;
  private final TourService tourService;

  public AccountController(UserServiceImpl userService,
      CreditCardService creditCardService, TourService tourService) {
    this.userService = userService;
    this.creditCardService = creditCardService;
    this.tourService = tourService;
  }

  @GetMapping("/user-account")
  public String getAccount(@AuthenticationPrincipal User user, Model model){
    model.addAttribute("user", user);
    return "userAccount";
  }

  @GetMapping("/creditCard")
  public String getCreditCardPage(@AuthenticationPrincipal User user, Model model){
    model.addAttribute("creditCard", creditCardService.getCreditCard(user));
    return "creditCard";
  }

  @GetMapping("/creditCard/topUp")
  public String getTopUpPage(@AuthenticationPrincipal User user, Model model){
    model.addAttribute("creditCard", creditCardService.getCreditCard(user));
    return "topUpCreditCard";
  }

  @GetMapping("/bought-tours")
  public String getBoughtTours(@AuthenticationPrincipal User user, Model model){
    model.addAttribute("boughtTours", tourService.getBoughtToursByUser(user));
    return "boughtTours";
  }

  @PostMapping("/creditCard")
  public String addCard(@AuthenticationPrincipal User user,
      @RequestParam String cardNumber,
      @RequestParam String cardPassword,
      Model model){
    creditCardService.addCard(user, cardNumber, cardPassword);
    model.addAttribute("creditCard", creditCardService.getCreditCard(user));
    return "creditCard";
  }

  @PostMapping("/creditCard/topUp")
  public String topUpCard(@AuthenticationPrincipal User user,
      @RequestParam double money,
      @RequestParam String cardPassword,
      Model model){
    model.addAttribute("message", creditCardService.topUpCard(user, money, cardPassword));
    return "topUpCreditCard";
  }

  @PostMapping("/bought-tours")
  public String returnTour(@AuthenticationPrincipal User user,
      @RequestParam String tourName,
      Model model){
    tourService.returnTour(user, tourName);
   model.addAttribute("boughtTours", tourService.getBoughtToursByUser(user));
   return "boughtTours";
  }
}
