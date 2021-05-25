DELETE FROM user_role;
DELETE FROM credit_card;
DELETE FROM usr;

INSERt INTO usr(id, username, password, active, email)
VALUES (1, '123','123',true,'loter98@gmail.com'),
(2, 'newUser', 'newPassword', true, 'some@gmail.com');

INSERT INTO user_role(user_id, roles)
VALUES (1, 'ADMIN'),
(2, 'USER');

INSERT INTO credit_card(id, balance, card_number, card_password, user_id)
VALUES(1, 222.22, '1234 4444 5555 1234', '1234password', 1),
(2, 300.00, '1111 2222 3333 4444', 'cardPassword', 2);