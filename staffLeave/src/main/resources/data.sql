
INSERT INTO users (id, firstname, surname, role, team) VALUES
                                                              ('41440eb5-45ea-42b9-a799-c32c5627812r', 'first1', 'surname1', 'STAFF', 'team1'),
                                                              ('61440eb5-45ea-42b9-a799-c32c5627812c', 'first2', 'surname2', 'ADMIN', 'team1');

INSERT INTO leave_request (id, staff_id, start_date, end_date, leave_amount, status, reason) VALUES
    ('b524f71c-0bb5-46bc-9575-d3f55eb9dd0e', '41440eb5-45ea-42b9-a799-c32c5627812r', '2025-09-01', '2025-09-05', 37.5, 'Pending', NULL);



INSERT INTO leave_balance (id, staff_id, leave_year, balance) VALUES
    ('f2acfe57-9718-4858-b3de-68ddc0ec1126', '41440eb5-45ea-42b9-a799-c32c5627812r', '2025', 210.0),
    ('61440eb5-45ea-42b9-a799-c32c5627812c', '61440eb5-45ea-42b9-a799-c32c5627812c', '2025', 210.0);


create sequence event_store_sequence_id start with (select max(id) + 1 from event_store);
