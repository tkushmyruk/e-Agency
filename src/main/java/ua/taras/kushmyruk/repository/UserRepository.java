package ua.taras.kushmyruk.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.taras.kushmyruk.model.User;

public interface UserRepository  extends JpaRepository<User, Long> {

  Page <User> findAll(Pageable pageable);

  User findByUsername(String username);

  @Query("SELECT u FROM User u LEFT JOIN FETCH u.creditCard WHERE u.id = :id")
  User findUserWithCreditCard(@Param("id") Long id);
}
