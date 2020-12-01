-- Users
INSERT INTO app_user (inst_id, user_name, last_name, email, tn)
VALUES (1, 'Andy', 'Valevsky', 'a.valevsky@gmail.com', '+380(67)234-67-96'),
       (2, 'Sergiy', 'Konavalec', 'angry.serj@gmail.com', '+380(50)862-01-36'),
       (3, 'Dmitry', 'Liashenko', 'pokemon@ucb.com.ua', '+380(97)475-24-64');

ALTER SEQUENCE app_user_inst_id_seq RESTART WITH 4;


-- Reports
INSERT INTO report (inst_id, user_id, report_name, price, order_date)
VALUES (1, 1, 'Report1.1', 40000.0, '2020-01-17'),
       (2, 1, 'Report1.2', 60000.0, '2020-03-21'),
       (3, 1, 'Report1.3', 320000.0, '2020-08-04'),
       (4, 2, 'Report2.1', 80000.0, '2020-02-17');

ALTER SEQUENCE report_inst_id_seq RESTART WITH 5;


-- Buildings
INSERT INTO building (inst_id, report_id, building_name, is_active)
VALUES (1, 1, 'Building #1', TRUE),
       (2, 2, 'Building #2', TRUE),
       (3, 3, 'Building #3', TRUE),
       (4, 3, 'Building #4', TRUE),
       (5, 4, 'Building #5', TRUE);

ALTER SEQUENCE building_inst_id_seq RESTART WITH 6;


-- Activities
INSERT INTO activity (inst_id, building_id, work_name, measurement, price, amount)
VALUES (1, 1, 'Cleaning', 'HOUR', 1600.0, 10),
       (2, 2, 'Priming', 'SQ_METER', 11000.0, 50),
       (3, 2, 'Dyeing', 'SQ_METER', 13000.0, 50),
       (4, 3, 'Priming', 'SQ_METER', 16760.0, 100),
       (5, 3, 'Dyeing', 'SQ_METER', 23450.0, 100),
       (6, 3, 'Mounting', 'METER', 6548.0, 4),
       (7, 3, 'Mounting', 'PIECE', 7654.0, 3),
       (8, 4, 'Priming', 'SQ_METER', 4750.0, 7),
       (9, 4, 'Dyeing', 'METER', 7657.0, 7),
       (10, 5, 'Priming', 'SQ_METER', 5700.0, 10),
       (11, 5, 'Dyeing', 'METER', 2500.0, 10);

ALTER SEQUENCE activity_inst_id_seq RESTART WITH 12;


-- Materials
INSERT INTO material (inst_id, material_name, price, supplier, measurement, balance)
VALUES (1, 'Concrete', 12.55, 'Supplier 1', 'KILOGRAM', 20),
       (2, 'Laminate #4', 25.00, 'Supplier 10', 'SQ_METER', 10),
       (3, 'Material to delete', 5.00, 'Supplier 3', 'SQ_METER', 10);

ALTER SEQUENCE material_inst_id_seq RESTART WITH 4;