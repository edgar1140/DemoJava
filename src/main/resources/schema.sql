CREATE SEQUENCE user_id_seq
  START WITH 2;

CREATE TABLE user (
  user_id        INTEGER(50) NOT NULL PRIMARY KEY,
  first_name     VARCHAR(50) NOT NULL,
  middle_name    VARCHAR(50),
  last_name      VARCHAR(50) NOT NULL,
  job_title      VARCHAR(50),
  location_id    NUMBER(50),
  create_user_id INTEGER(50) NOT NULL,
  create_date    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  expire_user_id INTEGER(50),
  expire_date    TIMESTAMP
);

CREATE SEQUENCE customer_id_seq
  START WITH 3;

CREATE TABLE customer (
  customer_id    INTEGER(50) NOT NULL PRIMARY KEY,
  first_name     VARCHAR(50) NOT NULL,
  middle_name    VARCHAR(50),
  last_name      VARCHAR(50) NOT NULL,
  create_user_id INTEGER(50) NOT NULL,
  create_date    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  expire_user_id INTEGER(50),
  expire_date    TIMESTAMP
);

CREATE SEQUENCE account_id_seq
  START WITH 4;

CREATE TABLE account (
  account_id     INTEGER(50) NOT NULL PRIMARY KEY,
  customer_id    INTEGER(50) NOT NULL,
  first_name     VARCHAR(50) NOT NULL,
  middle_name    VARCHAR(50),
  last_name      VARCHAR(50) NOT NULL,
  balance        DECIMAL(20, 2),
  create_user_id INTEGER(50) NOT NULL,
  create_date    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  expire_user_id INTEGER(50),
  expire_date    TIMESTAMP
);

CREATE SEQUENCE subscription_id_seq
  START WITH 5;

CREATE TABLE subscription (
  subscription_id INTEGER(50) NOT NULL PRIMARY KEY,
  account_id      INTEGER(50) NOT NULL,
  phone_number    VARCHAR(20),
  serial_number   VARCHAR(20),
  plan_name        VARCHAR(20),
  first_name      VARCHAR(50) NOT NULL,
  middle_name     VARCHAR(50),
  last_name       VARCHAR(50) NOT NULL,
  active         BOOLEAN,
  create_user_id  INTEGER(50) NOT NULL,
  create_date     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  expire_user_id  INTEGER(50),
  expire_date     TIMESTAMP
);

