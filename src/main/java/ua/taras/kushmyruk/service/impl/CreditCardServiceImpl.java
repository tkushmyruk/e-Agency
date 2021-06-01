package ua.taras.kushmyruk.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import ua.taras.kushmyruk.model.CreditCard;
import ua.taras.kushmyruk.model.User;
import ua.taras.kushmyruk.repository.CreditCardRepository;
import ua.taras.kushmyruk.repository.UserRepository;
import ua.taras.kushmyruk.service.CreditCardService;

@Service
public class CreditCardServiceImpl implements CreditCardService {
  private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardServiceImpl.class);
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
  public void addCard(User user, CreditCard creditCard, BindingResult bindingResult){
    if (bindingResult != null && bindingResult.hasErrors()){
      LOGGER.info("User {} filled wrong data", user.getUsername());
      return;
    }
    CreditCard cardFromDb = creditCardRepository.findByCardNumber(creditCard.getCardNumber());
    if(cardFromDb != null) {
      creditCard.setUser(user);
      creditCardRepository.save(creditCard);
      LOGGER.info("Credit card {} successfully added", user.getCreditCard().getCardNumber());
    }
  }
  @Override
  @Transactional
  public String topUpCard(User user, double money, String cardPassword){
    User userFromDb = userRepository.findUserWithCreditCard(user.getId());
    CreditCard creditCard = userFromDb.getCreditCard();
    if(creditCard.getCardPassword().equals(cardPassword)) {
      creditCard.setBalance(creditCard.getBalance() + money);
      creditCardRepository.save(creditCard);
      LOGGER.info("Card {} was successfully replenish on {}", creditCard.getCardNumber(), money );
      return "Your card was successfully replenished";
    }
    LOGGER.info("User {} filled wrong card password", user.getUsername());
    return "Password is incorrect. Please try Again";

  }
  @Override
  public CreditCard getCreditCard (User user){
    return userRepository.findUserWithCreditCard(user.getId()).getCreditCard();
  }
}
