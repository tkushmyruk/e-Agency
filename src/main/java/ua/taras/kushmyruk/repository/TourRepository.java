package ua.taras.kushmyruk.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.taras.kushmyruk.model.Tour;

public interface TourRepository  extends JpaRepository<Tour, Long> {
  @Query("SELECT t FROM Tour t WHERE t.tourStatus = 'Registered'")
  Page <Tour> findAllNotBoughtTours(Pageable pageable);

  Tour findByTourName(String tourName);

}
