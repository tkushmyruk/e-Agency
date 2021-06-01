package ua.taras.kushmyruk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.taras.kushmyruk.model.CreditCard;

public interface CreditCardRepository  extends JpaRepository<CreditCard, Long> {
  CreditCard findByCardNumber(String cardNumber);
}
