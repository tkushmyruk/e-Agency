package ua.taras.kushmyruk.service.util;

import java.util.Comparator;
import ua.taras.kushmyruk.model.Tour;

public class TourPriceComparator implements Comparator<Tour> {
  @Override
  public int compare(Tour o1, Tour o2) {
    return (int) (Double.valueOf(o1.getPrice()) - Double.valueOf(o2.getPrice()));
  }
}
