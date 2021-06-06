package ua.taras.kushmyruk.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.taras.kushmyruk.model.Tour;

public interface TourRepository  extends JpaRepository<Tour, Long> {
  @Query("SELECT t FROM Tour t WHERE t.tourStatus = 'Registered'")
  List<Tour> findAllNotBoughtTours();

  Tour findByTourName(String tourName);

}
