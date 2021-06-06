package ua.taras.kushmyruk.service.util;

import java.util.Comparator;
import ua.taras.kushmyruk.model.HotelStars;
import ua.taras.kushmyruk.model.Tour;

public class TourHotelStarsComparator implements Comparator<Tour> {

  @Override
  public int compare(Tour o1, Tour o2) {
    return starToInt(o1) - starToInt(o2);
  }

  private int starToInt(Tour tour){
    if(tour.getHotelStars().toString().equals(HotelStars.ONE.toString())){
      return 1;
    }
    if(tour.getHotelStars().toString().equals(HotelStars.TWO.toString())){
      return 2;
    }
    if(tour.getHotelStars().toString().equals(HotelStars.THREE.toString())){
      return 3;
    }
    if(tour.getHotelStars().toString().equals(HotelStars.FOUR.toString())){
      return 4;
    }
    if(tour.getHotelStars().toString().equals(HotelStars.FIVE.toString())){
      return 5;
    }
    return 0;
  }

}
