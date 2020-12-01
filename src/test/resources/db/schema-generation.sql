CREATE SEQUENCE activity_inst_id_seq START 1 INCREMENT 1;
CREATE SEQUENCE building_inst_id_seq START 1 INCREMENT 1;
CREATE SEQUENCE material_inst_id_seq START 1 INCREMENT 1;
CREATE SEQUENCE report_inst_id_seq START 1 INCREMENT 1;
CREATE SEQUENCE app_user_inst_id_seq START 1 INCREMENT 1;

CREATE TABLE app_user (
    inst_id      BIGSERIAL PRIMARY KEY,
    user_name    VARCHAR(50),
    last_name    VARCHAR(50),
    email        VARCHAR(50) NOT NULL,
    email_backup VARCHAR(50) DEFAULT NULL,
    tn           VARCHAR(20) NOT NULL,
    tn_backup    VARCHAR(20) DEFAULT NULL
);

CREATE TABLE report (
    inst_id     BIGSERIAL PRIMARY KEY,
    user_id     BIGINT,
    report_name VARCHAR(50),
    price       DECIMAL,
    order_date  DATE,
    CONSTRAINT fk_report_user FOREIGN KEY (user_id)
        REFERENCES app_user (inst_id)
);

CREATE TABLE building (
    inst_id       BIGSERIAL PRIMARY KEY,
    report_id     BIGINT,
    building_name VARCHAR(50),
    is_active     BOOLEAN,

    CONSTRAINT fk_report_building FOREIGN KEY (report_id)
        REFERENCES report (inst_id)
);

CREATE TABLE activity (
    inst_id     BIGSERIAL PRIMARY KEY,
    building_id BIGINT,
    work_name   VARCHAR(20),
    measurement VARCHAR(20),
    price       DECIMAL,
    amount      DECIMAL,
    CONSTRAINT fk_building_activity FOREIGN KEY (building_id)
        REFERENCES building (inst_id)
);

CREATE TABLE material (
    inst_id       BIGSERIAL PRIMARY KEY,
    material_name VARCHAR(50),
    price         DECIMAL,
    supplier      VARCHAR(50),
    measurement   VARCHAR(50),
    balance       DECIMAL
);
