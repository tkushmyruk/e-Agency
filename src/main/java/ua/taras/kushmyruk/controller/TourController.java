package ua.taras.kushmyruk.controller;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.taras.kushmyruk.model.Tour;
import ua.taras.kushmyruk.model.User;
import ua.taras.kushmyruk.service.TourService;

@Controller
public class TourController {
  private final TourService tourService;

  public TourController(TourService tourService) {
    this.tourService = tourService;
  }

  @GetMapping("/catalog")
  public String getCatalog(Model model){
    model.addAttribute("catalog", tourService.getAllNotBoughtTours());
    return "catalog";
  }

  @GetMapping("/tour/add")
  @PreAuthorize("hasAuthority('ADMIN')")
  public String getAddTourPage(){
    return "addTour";
  }

  @GetMapping("/tour/{tourName}")
  public String showTour(@PathVariable String tourName, Model model){
    model.addAttribute("tour", tourService.getTour(tourName));
    return "tourInfo";
  }

  @GetMapping("/tour-redact/{tourName}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public String redactTourPage(@PathVariable String tourName, Model model){
    System.out.println(tourService.getTour(tourName).getPrice());
    model.addAttribute("tour" ,tourService.getTour(tourName));
    return "tourRedact";
  }


  @PostMapping("/tour/add")
  @PreAuthorize("hasAuthority('ADMIN')")
  public String addNewTour(@Valid Tour tour,
      BindingResult bindingResult,
      Model model
      ){
    Map<String, String> errorsMap = ControllerUtil.getErrors(bindingResult);
    model.mergeAttributes(errorsMap);
    return tourService.addTour(tour, bindingResult) ? "redirect:/catalog" : "addTour";
  }

  @PostMapping("/tour/{tourName}")
  public String buyTour(@AuthenticationPrincipal User user,
      @PathVariable String tourName,
      Model model){
    model.addAttribute("tour", tourService.getTour(tourName));
   return tourService.buyTour(user, tourName)?"redirect:/catalog":"redirect:/creditCard";
  }

  @PostMapping("/tour-redact/{tourName}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public String tourRedact(@PathVariable String tourName,
      @RequestParam int countOfPeople,
      @RequestParam String price,
      @RequestParam String startDate,
      @RequestParam String endDate,
      @RequestParam String country,
      @RequestParam String departingFrom,
      @RequestParam String locality,
      @RequestParam String tourStatus,
      @RequestParam String tourType,
      @RequestParam String roomType,
      @RequestParam String hotelStars,
      @RequestParam String hotelName,
      @RequestParam Optional<String> isAllInclusive,
      @RequestParam Optional<String> isHot,
      Model model){
    model.addAttribute("tour", tourService.getTour(tourName));
    tourService.redactTour(tourName, countOfPeople, price, LocalDate.parse(startDate), LocalDate.parse(endDate), departingFrom, country,
        locality, hotelName, tourStatus, tourType, roomType, hotelStars, isAllInclusive, isHot);

    return "tourRedact";
  }

  @PostMapping("/tour-type-sort")
  public String sortByTourType(@RequestParam Optional<String> tourType, Model model){
    model.addAttribute("catalog", tourService.getSortedByTourType(tourType));
    return "catalog";
  }

  @PostMapping("/tour-hotel-stars-sort")
  public String sortByHotelStars(@RequestParam Optional<String> direction, Model model){
    model.addAttribute("catalog", tourService.getSortedByHotelStars(direction));
    return "catalog";
  }

  @PostMapping("/tour-price-sort")
  public String sortByPrice(@RequestParam Optional<String> direction, Model model){
    model.addAttribute("catalog", tourService.getSortedByPrice(direction));
    return "catalog";
  }
  @PostMapping("/tour-people-sort")
  public String sortByCountOfPeople(@RequestParam Optional<String> direction, Model model){
    model.addAttribute("catalog", tourService.getSortedByCountOfPeople(direction));
    return "catalog";
  }

  @PostMapping("/delete-tour/{tourName}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public String deleteTour(@PathVariable String tourName, Model model){
     tourService.deleteTour(tourName);
     model.addAttribute("catalog", tourService.getAllTours());
    return "catalog";
  }

}
