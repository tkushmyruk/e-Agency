package ua.taras.kushmyruk.model;

import java.time.LocalDate;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Tour {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @NotBlank(message = "Tour name cannot be empty")
  private String tourName;
  @NotNull(message = "Field count of people cannot be empty")
  private Integer countOfPeople;
  @NotBlank(message = "Tour cannot be free")
  private String price;
  @NotNull(message = "Tour must have start date")
  @Future(message = "Start date cannot be in past")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate startDate;
  @NotNull(message = "Tour must in someday ends")
  @Future(message = "End date cannot be in past")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate endDate;
  @NotBlank(message = "Tour must have start point")
  private String departingFrom;
  @NotBlank(message = "Tour must have any country as a destination")
  private String country;
  @NotBlank(message = "Tour must have any locality as a destination")
  private String locality;
  @ElementCollection(targetClass = TourType.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "tour_type", joinColumns = @JoinColumn(name = "tour_id"))
  @Enumerated(EnumType.STRING)
  private Set<TourType> tourType;
  @ElementCollection(targetClass = RoomType.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "room_type", joinColumns = @JoinColumn(name = "tour_id"))
  @Enumerated(EnumType.STRING)
  private Set<RoomType> roomType;
  private String tourStatus;
  @ElementCollection(targetClass = HotelStars.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "hotel_stars", joinColumns = @JoinColumn(name = "tour_id"))
  @Enumerated(EnumType.STRING)
  private Set<HotelStars> hotelStars;
  @NotBlank(message = "Customer must somewhere to stay")
  private String hotelName;
  private boolean isAllInclusive;
  private boolean isHot;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTourName() {
    return tourName;
  }

  public void setTourName(String tourName) {
    this.tourName = tourName;
  }

  public int getCountOfPeople() {
    return countOfPeople;
  }

  public void setCountOfPeople(int countOfPeople) {
    this.countOfPeople = countOfPeople;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public String getDepartingFrom() {
    return departingFrom;
  }

  public void setDepartingFrom(String departingFrom) {
    this.departingFrom = departingFrom;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getLocality() {
    return locality;
  }

  public void setLocality(String locality) {
    this.locality = locality;
  }

  public Set<TourType> getTourType() {
    return tourType;
  }

  public void setTourType(Set<TourType> tourType) {
    this.tourType = tourType;
  }

  public Set<RoomType> getRoomType() {
    return roomType;
  }

  public void setRoomType(Set<RoomType> roomType) {
    this.roomType = roomType;
  }

  public boolean isAllInclusive() {
    return isAllInclusive;
  }

  public void setAllInclusive(boolean allInclusive) {
    isAllInclusive = allInclusive;
  }

  public boolean isHot() {
    return isHot;
  }

  public void setHot(boolean hot) {
    isHot = hot;
  }

  public String getTourStatus() {
    return tourStatus;
  }

  public void setTourStatus(String tourStatus) {
    this.tourStatus = tourStatus;
  }

  public Set<HotelStars> getHotelStars() {
    return hotelStars;
  }

  public void setHotelStars(Set<HotelStars> hotelStars) {
    this.hotelStars = hotelStars;
  }

  public String getHotelName() {
    return hotelName;
  }

  public void setHotelName(String hotelName) {
    this.hotelName = hotelName;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
