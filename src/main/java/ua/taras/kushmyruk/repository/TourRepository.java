package ua.taras.kushmyruk.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.taras.kushmyruk.model.Tour;
import ua.taras.kushmyruk.model.User;

public interface TourRepository  extends JpaRepository<Tour, Long> {
  @Query("SELECT t FROM Tour t WHERE t.tourStatus = 'Registered'")
  List<Tour> findAllNotBoughtTours();

  @Query("SELECT t FROM Tour t WHERE t.tourStatus = 'Payed' AND t.user = :user ")
  List<Tour> findAllUserTours(@Param("user")User user);

  Tour findByTourName(String tourName);

}
