INSERT INTO roles (id, code, name) VALUES ('25c7a1ac-191c-40a1-a71e-fd9c8fb90db0', 'ROLE_USER', 'User Role');

INSERT INTO permissions (id, code, name) VALUES ('15c7a1ac-191c-40a1-a71e-fd9c8fb90db0', 'VIEW_USERS', 'View Users');

INSERT INTO role_permissions (permission_id, role_id) VALUES ('15c7a1ac-191c-40a1-a71e-fd9c8fb90db0', '25c7a1ac-191c-40a1-a71e-fd9c8fb90db0');

INSERT INTO users (active, age, id, gender, name, password, username) VALUES (true, 33, '2eabd2f2-b41d-490b-9190-bac6ab4ce0b9', 'MALE', 'Baris', '$2a$10$FZGnQpWaefRDZnUh5ijtsuUOhQaD1mgO/RJ4hd3DAtzFCk2CHcIKS', 'baris');

INSERT INTO user_roles (role_id, user_id) VALUES ('25c7a1ac-191c-40a1-a71e-fd9c8fb90db0', '2eabd2f2-b41d-490b-9190-bac6ab4ce0b9');

INSERT INTO users (active, age, id, gender, name, password, username) VALUES (true, 20, '1eabd2f2-b41d-490b-9190-bac6ab4ce0b9', 'MALE', 'Akif', '$2a$10$FZGnQpWaefRDZnUh5ijtsuUOhQaD1mgO/RJ4hd3DAtzFCk2CHcIKS', 'akif');

INSERT INTO user_roles (role_id, user_id) VALUES ('25c7a1ac-191c-40a1-a71e-fd9c8fb90db0', '1eabd2f2-b41d-490b-9190-bac6ab4ce0b9');


INSERT INTO users (active, age, id, gender, name, password, username) VALUES (true, 20, '3eabd2f2-b41d-490b-9190-bac6ab4ce0b9', 'MALE', 'Ali Rıza', '$2a$10$FZGnQpWaefRDZnUh5ijtsuUOhQaD1mgO/RJ4hd3DAtzFCk2CHcIKS', 'aliriza');

INSERT INTO user_roles (role_id, user_id) VALUES ('25c7a1ac-191c-40a1-a71e-fd9c8fb90db0', '3eabd2f2-b41d-490b-9190-bac6ab4ce0b9');


INSERT INTO users (active, age, id, gender, name, password, username) VALUES (true, 20, '4eabd2f2-b41d-490b-9190-bac6ab4ce0b9', 'FEMALE', 'Esin', '$2a$10$FZGnQpWaefRDZnUh5ijtsuUOhQaD1mgO/RJ4hd3DAtzFCk2CHcIKS', 'esin');

INSERT INTO user_roles (role_id, user_id) VALUES ('25c7a1ac-191c-40a1-a71e-fd9c8fb90db0', '4eabd2f2-b41d-490b-9190-bac6ab4ce0b9');