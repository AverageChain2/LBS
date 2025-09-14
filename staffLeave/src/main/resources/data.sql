
INSERT INTO users (id, firstname, surname, role, team_id) VALUES
                                                              ('680c749c-55d2-4dff-af88-6eec12c90384', 'Alice', 'Smith', 'Staff', 'T1'),
                                                              ('fda4b2ea-4211-4ffd-9ae5-c0bf805403b2', 'Bob', 'Johnson', 'Manager', 'T1');

INSERT INTO leave_request (id, staff_id, start_date, end_date, leave_amount, status, reason) VALUES
    ('b524f71c-0bb5-46bc-9575-d3f55eb9dd0e', '680c749c-55d2-4dff-af88-6eec12c90384', '2025-09-01', '2025-09-05', 5.0, 'Pending', NULL);



INSERT INTO leave_balance (id, staff_id, leaveYear, balance) VALUES
    ('f2acfe57-9718-4858-b3de-68ddc0ec1126', '680c749c-55d2-4dff-af88-6eec12c90384', '2025', 15.0);
