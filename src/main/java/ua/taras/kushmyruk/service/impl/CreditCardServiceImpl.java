package ua.taras.kushmyruk.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.taras.kushmyruk.model.CreditCard;
import ua.taras.kushmyruk.model.User;
import ua.taras.kushmyruk.repository.CreditCardRepository;
import ua.taras.kushmyruk.repository.UserRepository;
import ua.taras.kushmyruk.service.CreditCardService;

@Service
public class CreditCardServiceImpl implements CreditCardService {
  private final CreditCardRepository creditCardRepository;
  private final UserRepository userRepository;

  public CreditCardServiceImpl(
      CreditCardRepository creditCardRepository,
      UserRepository userRepository) {
    this.creditCardRepository = creditCardRepository;
    this.userRepository = userRepository;
  }

  @Override
  @Transactional
  public void addCard(User user, String cardNumber, String cardPassword){
    CreditCard creditCard = new CreditCard();
    creditCard.setCardNumber(cardNumber);
    creditCard.setCardPassword(cardPassword);
    creditCard.setUser(user);
    creditCardRepository.save(creditCard);
  }
  @Override
  @Transactional
  public String topUpCard(User user, double money, String cardPassword){
    User userFromDb = userRepository.findUserWithCreditCard(user.getId());
    CreditCard creditCard = userFromDb.getCreditCard();
    if(creditCard.getCardPassword().equals(cardPassword)) {
      creditCard.setBalance(creditCard.getBalance() + money);
      creditCardRepository.save(creditCard);
      return "Your card was successfully replenished";
    }
    return "Password is incorrect. Please try Again";

  }
  @Override
  public CreditCard getCreditCard (User user){
    return userRepository.findUserWithCreditCard(user.getId()).getCreditCard();
  }
}
