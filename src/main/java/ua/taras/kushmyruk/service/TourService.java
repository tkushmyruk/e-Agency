package ua.taras.kushmyruk.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import ua.taras.kushmyruk.model.Tour;
import ua.taras.kushmyruk.model.User;

public interface TourService {

  List<Tour> getAllTours();

  Page<Tour> getAllNotBoughtTours(User user, Pageable pageable);

  Tour getTour(String tourName);

  boolean addTour (Tour tour, BindingResult bindingResult);

  boolean buyTour(User user, String tourName);

  void redactTour(String tourName, int countOfPeople, String price, LocalDate startDate,
      LocalDate endDate, String departingFrom, String country, String locality, String hotelName, String tourStatus,
      String tourType, String roomType, String hotelStars, Optional<String> isAllInclusive, Optional<String> isHot);

  List<Tour> getSortedByTourType(Optional<String>tourType);

  List<Tour> getSortedByHotelStars(Optional<String> direction);

  List<Tour> getSortedByPrice(Optional<String> direction);

  List<Tour> getSortedByCountOfPeople(Optional<String> direction);

  void deleteTour(String touName);

  List<Tour> getBoughtToursByUser(User user);

  void returnTour(User user, String tourName);

}
