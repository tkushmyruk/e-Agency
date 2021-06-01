package ua.taras.kushmyruk.service;

import org.springframework.validation.BindingResult;
import ua.taras.kushmyruk.model.CreditCard;
import ua.taras.kushmyruk.model.User;

public interface CreditCardService {

  void addCard(User user, CreditCard creditCard, BindingResult bindingResult);

  String topUpCard(User user, double money, String cardPassword);

  CreditCard getCreditCard (User user);

}
