DELETE FROM hotel_stars;
DELETE FROM tour_type;
DELETE FROM room_type;
DELETE FROM tour;

INSERT INTO tour(id, tour_name, count_of_people, price, start_date, end_date, departing_from
, country, locality, is_all_inclusive, is_hot, tour_status)
VALUES(1, 'tourName', 5, '222.22', '2012-02-12', '2013-03-13', 'Kiev', 'Greece', 'Aphins', true, true, 'Registered'),
(2, 'tourName2', 4, '333.22', '2014-02-22', '2015-03-19', 'Kiev', 'Greece', 'zamaru', true, true, 'Registered');

INSERT INTO tour(id, tour_name, count_of_people, price, start_date, end_date, departing_from
, country, locality, is_all_inclusive, is_hot, tour_status, user_id)
VALUES(3, 'tourName3', 5, '222.22', '2012-02-12', '2013-03-13',
 'Kiev', 'Greece', 'Aphins', true, true, 'Payed', 1);

INSERT INTO hotel_stars(tour_id, hotel_stars)
VALUES (1, 'THREE'),
(2, 'FIVE'),
(3, 'FOUR');

INSERT INTO room_type(tour_id, room_type)
VALUES(1, 'LUXE'),
(2, 'PRESIDENT'),
(3, 'STANDART');

INSERT INTO tour_type(tour_id, tour_type)
VALUES (1, 'SHOPPING'),
(2, 'REST'),
(3, 'EXCURSION');
