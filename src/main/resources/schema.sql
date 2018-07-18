CREATE SEQUENCE user_id_seq;

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

CREATE SEQUENCE customer_id_seq;

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

CREATE SEQUENCE account_id_seq;

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
