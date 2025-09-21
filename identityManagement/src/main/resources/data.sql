insert into role (type) values ('USER');
insert into role (type) values ('MANAGER');
insert into role (type) values ('ADMIN');
insert into team (name) values ('team1');



insert into app_user (id, username, password, first_name, surname, email, role_id, team_id)
values ('41440eb5-45ea-42b9-a799-c32c5627812r',
        'user',
        '$2a$12$NRxLx0wmM96WNoUb99tdPeNxKhrmAEBPIu7jD2zKGjXorQrJYnxmm',
        'first1',
        'surname1',
        'user@email.com',
        1,
        1);


insert into app_user (id, username, password, first_name, surname, email, role_id, team_id)
values ('61440eb5-45ea-42b9-a799-c32c5627812c',
        'admin',
        '$2a$12$llP7q0XajD1Pt6Pgw25Ps.EVMzngGJmZYTQj78Hxei8vPgzzKU3w6',
        'first2',
        'surname2',
        'admin@email.com',
        2,
        1);

create sequence event_store_sequence_id start with (select max(id) + 1 from event_store);


//Password = user123
//Password = admin123
//https://bcrypt-generator.com/

