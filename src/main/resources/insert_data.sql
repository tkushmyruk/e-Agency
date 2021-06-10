INSERT INTO usr (username, password,  email, is_active)
VALUES('admin', 'admin', 'admin@gmail.com', true),
('manager', 'manager', 'manager@gmail.com', true),
('pageableUser1', '123', 'pageable@gmail.com', true),
('pageableUser2', '123', 'pageable@gmail.com', true),
('pageableUser3', '123', 'pageable@gmail.com', true),
('pageableUser4', '123', 'pageable@gmail.com', true),
('pageableUser5', '123', 'pageable@gmail.com', true),
('pageableUser6', '123', 'pageable@gmail.com', true),
('pageableUser7', '123', 'pageable@gmail.com', true),
('pageableUser8', '123', 'pageable@gmail.com', true);

INSERT INTO user_role(username, role)
VALUES ('admin', 'ADMIN'),
('manager', 'MANAGER'),
('pageableUser1', 'USER'),
('pageableUser2', 'USER'),
('pageableUser3', 'USER'),
('pageableUser4', 'USER'),
('pageableUser5', 'USER'),
('pageableUser6', 'USER'),
('pageableUser7', 'USER'),
('pageableUser8', 'USER');