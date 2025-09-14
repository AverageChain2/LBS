


CREATE TABLE users (
                       id VARCHAR(255) PRIMARY KEY,
                       firstname VARCHAR(255),
                       surname VARCHAR(255),
                       role VARCHAR(255) CHECK (role IN ('Staff', 'Manager', 'Admin')),
                       team_id VARCHAR(255)
);

CREATE TABLE leave_request (
                              id VARCHAR(255) PRIMARY KEY,
                              staff_id VARCHAR(255),
                              start_date DATE,
                              end_date DATE,
                              leave_amount FLOAT,
                              FOREIGN KEY (staff_id) REFERENCES users(id)
);

CREATE TABLE leave_approval (
                               id VARCHAR(255) PRIMARY KEY,
                               leave_id VARCHAR(255),
                               approver_id VARCHAR(255),
                               status VARCHAR(255) CHECK (status IN ('Pending', 'Approved', 'Rejected', 'Cancelled')),
                               reason VARCHAR(255),
                               approved_at DATE,
                               FOREIGN KEY (leave_id) REFERENCES leave_request(id),
                               FOREIGN KEY (approver_id) REFERENCES users(id)
);

CREATE TABLE leave_balance (
                              id VARCHAR(255) PRIMARY KEY,
                              staff_id VARCHAR(255),
                              leaveYear VARCHAR(255),
                              balance FLOAT
);


CREATE TABLE event_store(
                            id int AUTO_INCREMENT PRIMARY KEY,
                            occurred_on DATE NOT NULL,
                            event_body VARCHAR(65000) NOT NULL,
                            event_type VARCHAR(50) NOT NULL
);