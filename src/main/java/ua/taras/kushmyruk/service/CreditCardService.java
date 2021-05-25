package ua.taras.kushmyruk.service;

import ua.taras.kushmyruk.model.CreditCard;
import ua.taras.kushmyruk.model.User;

public interface CreditCardService {

  void addCard(User user, String cardNumber, String cardPassword);

  String topUpCard(User user, double money, String cardPassword);

  CreditCard getCreditCard (User user);

}
