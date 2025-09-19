insert into role (type) values ('USER');
insert into role (type) values ('ADMIN');

insert into team (name) values ('MyTeam');


insert into app_user (id, username, password, first_name, surname, email, role_id, team_id)
values ('0000',
        'appUser',
        '$2a$12$NRxLx0wmM96WNoUb99tdPeNxKhrmAEBPIu7jD2zKGjXorQrJYnxmm',
        'first1',
        'surname1',
        'appUser@email.com',
        1,
        1);


insert into app_user (id, username, password, first_name, surname, email, role_id, team_id)
values ('0001',
        'admin',
        '$2a$12$llP7q0XajD1Pt6Pgw25Ps.EVMzngGJmZYTQj78Hxei8vPgzzKU3w6',
        'first2',
        'surname2',
        'admin@email.com',
        2,
        1);

//Password = user123
//Password = admin123
//https://bcrypt-generator.com/