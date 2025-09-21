CREATE TABLE role (
      id int auto_increment primary key,
      type varchar(45) not null
);

CREATE TABLE team (
      id int auto_increment primary key,
      name varchar(45) not null
);

CREATE TABLE app_user (
      id varchar(50) primary key,
      username varchar(50) unique,
      password varchar(60) not null,
      first_name varchar(40) not null,
      surname varchar(40) not null,
      email varchar(50) not null unique,
      role_id int not null,
      team_id int not null,
      FOREIGN KEY(role_id) REFERENCES role(id),
      FOREIGN KEY(team_id) REFERENCES team(id)

);

CREATE TABLE event_store(
                            id int AUTO_INCREMENT PRIMARY KEY,
                            occurred_on DATE NOT NULL,
                            event_body VARCHAR(65000) NOT NULL,
                            event_type VARCHAR(50) NOT NULL
);
