CREATE TABLE users (
                       id VARCHAR(255) PRIMARY KEY NOT NULL,
                       firstname VARCHAR(255) NOT NULL,
                       surname VARCHAR(255) NOT NULL,
                       role VARCHAR(255) NOT NULL CHECK (role IN ('Staff', 'Manager', 'Admin')),
                       team_id VARCHAR(255) -- Optional, depending on your business rules
);

CREATE TABLE leave_request (
                               id VARCHAR(255) PRIMARY KEY NOT NULL,
                               staff_id VARCHAR(255) NOT NULL,
                               start_date DATE NOT NULL,
                               end_date DATE NOT NULL,
                               leave_amount FLOAT NOT NULL,
                               status VARCHAR(255) NOT NULL CHECK (status IN ('Pending', 'Approved', 'Rejected', 'Cancelled')),
                               reason VARCHAR(255),
                               FOREIGN KEY (staff_id) REFERENCES users(id)
);

CREATE TABLE leave_approval (
                                id VARCHAR(255) PRIMARY KEY NOT NULL,
                                leave_id VARCHAR(255) NOT NULL,
                                approver_id VARCHAR(255) NOT NULL,
                                status VARCHAR(255) NOT NULL CHECK (status IN ('Pending', 'Approved', 'Rejected', 'Cancelled')),
                                reason VARCHAR(255),
                                approved_at DATE,
                                FOREIGN KEY (leave_id) REFERENCES leave_request(id),
                                FOREIGN KEY (approver_id) REFERENCES users(id)
);

CREATE TABLE leave_balance (
                               id VARCHAR(255) PRIMARY KEY NOT NULL,
                               staff_id VARCHAR(255) NOT NULL,
                               leave_year VARCHAR(255) NOT NULL,
                               balance FLOAT NOT NULL,
                               FOREIGN KEY (staff_id) REFERENCES users(id)
);

CREATE TABLE event_store (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             occurred_on DATE NOT NULL,
                             event_body VARCHAR(65000) NOT NULL,
                             event_type VARCHAR(50) NOT NULL
);
