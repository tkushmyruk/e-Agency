package ua.taras.kushmyruk.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import ua.taras.kushmyruk.model.CreditCard;
import ua.taras.kushmyruk.model.HotelStars;
import ua.taras.kushmyruk.model.RoomType;
import ua.taras.kushmyruk.model.Tour;
import ua.taras.kushmyruk.model.TourType;
import ua.taras.kushmyruk.model.User;
import ua.taras.kushmyruk.repository.CreditCardRepository;
import ua.taras.kushmyruk.repository.TourRepository;
import ua.taras.kushmyruk.repository.UserRepository;
import ua.taras.kushmyruk.service.TourService;
import ua.taras.kushmyruk.service.util.TourCountOfPeopleComparator;
import ua.taras.kushmyruk.service.util.TourHotelStarsComparator;
import ua.taras.kushmyruk.service.util.TourPriceComparator;

@Service
public class TourServiceImpl implements TourService {
  private static final Logger LOGGER = LoggerFactory.getLogger(TourServiceImpl.class);

  private final TourRepository tourRepository;
  private final UserRepository userRepository;
  private final CreditCardRepository creditCardRepository;

  public TourServiceImpl(TourRepository tourRepository,
      UserRepository userRepository,
      CreditCardRepository creditCardRepository) {
    this.tourRepository = tourRepository;
    this.userRepository = userRepository;
    this.creditCardRepository = creditCardRepository;
  }

  @Override
  public List<Tour> getAllTours() {
    return tourRepository.findAll();
  }

  @Override
  public List <Tour> getAllNotBoughtTours() {
    return tourRepository.findAllNotBoughtTours();

  }

  @Override
  public Tour getTour(String tourName) {
    return tourRepository.findByTourName(tourName);
  }

  @Override
  public boolean addTour(Tour tour, BindingResult bindingResult) {
    if (bindingResult.hasErrors()){
      return false;
    }
    Tour tourFromDb = tourRepository.findByTourName(tour.getTourName());

    if (tourFromDb != null) {
      return false;
    }
    LOGGER.info("Tour {} was saved", tour.getTourName());
    tourRepository.save(tour);
    return true;
  }

  @Override
  @Transactional
  public boolean buyTour(User user, String tourName) {
    User userFromDb = userRepository.findUserWithCreditCard(user.getId());
    Tour selectedTour = tourRepository.findByTourName(tourName);
    CreditCard creditCard = userFromDb.getCreditCard();
    if (creditCard == null || creditCard.getBalance() - Double.valueOf(selectedTour.getPrice()) < 0) {
      LOGGER.info("User {} dont have enough money to buy {}", user.getUsername(), tourName);
      return false;
    }
    creditCard.setBalance(creditCard.getBalance() - Double.valueOf(selectedTour.getPrice()));
    selectedTour.setUser(user);
    selectedTour.setTourStatus("Payed");
    creditCardRepository.save(creditCard);
    tourRepository.save(selectedTour);
    LOGGER.info("Tour {} was bought by {}", tourName, user.getUsername());
    return true;
  }

  @Override
  @Transactional
  public void redactTour(String tourName, int countOfPeople, String price,
      LocalDate startDate, LocalDate endDate, String departingFrom, String country, String locality,
      String hotelName, String tourStatus, String tourType, String roomType, String hotelStars,
      Optional<String> isAllInclusive, Optional<String> isHot) {
    Tour tour = tourRepository.findByTourName(tourName);
    tour.setTourName(tourName);
    tour.setCountOfPeople(countOfPeople);
    tour.setPrice(price);
    tour.setStartDate(startDate);
    tour.setEndDate(endDate);
    tour.setDepartingFrom(departingFrom);
    tour.setCountry(country);
    tour.setLocality(locality);
    tour.setTourStatus(tourStatus);
    tour.setHotelName(hotelName);
    tour.getTourType().clear();
    tour.getTourType().add(TourType.valueOf(tourType));
    tour.getRoomType().clear();
    tour.getRoomType().add(RoomType.valueOf(roomType));
    tour.getHotelStars().clear();
    tour.getHotelStars().add(HotelStars.valueOf(hotelStars));
    if (isAllInclusive.isPresent()) {
      tour.setAllInclusive(true);
    }
    if (isHot.isPresent()) {
      tour.setHot(true);
    }
    tourRepository.save(tour);
  }

  @Override
  public List<Tour> getSortedByTourType(Optional<String> tourType) {
    List<Tour> allTours = tourRepository.findAll();
    if (tourType.isPresent()) {
      return allTours.stream().filter(tour ->
          tour.getTourType().contains(TourType.valueOf(tourType.get())))
          .collect(Collectors.toList());
    }
    return allTours;
  }

  @Override
  public List<Tour> getSortedByHotelStars(Optional<String> direction) {
    List<Tour> allTours = tourRepository.findAll();
    TourHotelStarsComparator comparator = new TourHotelStarsComparator();
    if (direction.isPresent() && direction.get().equals("Asc")) {
      allTours.sort(comparator);
      return allTours;
    }
    if(direction.isPresent() && direction.get().equals("Desc")){
      allTours.sort(comparator);
      Collections.reverse(allTours);
      return allTours;
    }
    return allTours;
  }

  @Override
  public List<Tour> getSortedByPrice(Optional<String> direction) {
    List<Tour> allTours = tourRepository.findAllNotBoughtTours();
    TourPriceComparator comparator = new TourPriceComparator();
    if (direction.isPresent() && direction.get().equals("Asc")) {
      allTours.sort(comparator);
      return allTours;
    }
    if(direction.isPresent() && direction.get().equals("Desc")){
      allTours.sort(comparator);
      Collections.reverse(allTours);
      return allTours;
    }
    return allTours;
  }

  @Override
  public List<Tour> getSortedByCountOfPeople(Optional<String> direction) {
    List<Tour> allTours = tourRepository.findAllNotBoughtTours();
    TourCountOfPeopleComparator comparator = new TourCountOfPeopleComparator();
    if (direction.isPresent() && direction.get().equals("Asc")) {
      allTours.sort(comparator);
      return allTours;
    }
    if(direction.isPresent() && direction.get().equals("Desc")){
      allTours.sort(comparator);
      Collections.reverse(allTours);
      return allTours;
    }
    return allTours;
  }

  @Override
  @Transactional
  public void deleteTour(String touName) {
    Tour tourFromDb = tourRepository.findByTourName(touName);
    tourFromDb.setTourStatus("Canceled");
    tourRepository.save(tourFromDb);
  }

  @Override
  public List<Tour> getBoughtToursByUser(User user) {
    return userRepository.findUserWithBoughtTours(user.getId()).getBoughtTours();
  }

  @Override
  @Transactional
  public void returnTour(User user, String tourName) {
    Tour userTour = tourRepository.findByTourName(tourName);
    if(userTour != null) {
      List<Tour> toursList = userRepository.findUserWithBoughtTours(user.getId()).getBoughtTours();
      CreditCard creditCard = userRepository.findUserWithCreditCard(user.getId()).getCreditCard();
      creditCard.setBalance(creditCard.getBalance() + Double.valueOf(userTour.getPrice()));
      userTour.setTourStatus("Registered");
      for (int i = 0; i < toursList.size(); i++) {
        if (toursList.get(i).getTourName().equals(tourName)) {
          toursList.remove(i);
        }
      }
      tourRepository.save(userTour);
      userRepository.save(user);
      creditCardRepository.save(creditCard);
    }
  }
}

