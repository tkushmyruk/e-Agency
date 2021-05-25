package ua.taras.kushmyruk.service.util;

import java.util.Comparator;
import ua.taras.kushmyruk.model.Tour;

public class TourCountOfPeopleComparator implements Comparator<Tour> {

  @Override
  public int compare(Tour o1, Tour o2) {
    return o1.getCountOfPeople() - o2.getCountOfPeople();
  }
}
