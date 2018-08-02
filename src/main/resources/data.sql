INSERT INTO user (
  user_id,
  first_name,
  middle_name,
  last_name,
  job_title,
  location_id,
  create_user_id
)
VALUES (
  1,
  'USER_01_FIRST',
  'USER_01_MIDDLE',
  'USER_01_LAST',
  'PLACE HOLDER',
  383,
  1
);

INSERT INTO customer (
  customer_id,
  first_name,
  last_name,
  create_user_id
)
VALUES (
  1,
  'Manuel',
  'Gutierrez',
  1
);INSERT INTO customer (
  customer_id,
  first_name,
  last_name,
  create_user_id
)
VALUES (
  2,
  'Manny',
  'Paquiado',
  1
);

INSERT INTO account (
  account_id,
  customer_id,
  first_name,
  last_name,
  create_user_id


)
VALUES (
  1,
  1,
  'Danny',
  'Trejo',
  1

);

INSERT INTO account (
  account_id,
  customer_id,
  first_name,
  last_name,
  create_user_id


)
VALUES (
  2,
  1,
  'George',
  'Lopez',
  1

);

INSERT INTO account (
  account_id,
  customer_id,
  first_name,
  last_name,
  create_user_id


)
VALUES (
  3,
  1,
  'Kevin',
  'Hart',
  1

);


INSERT INTO subscription (
  subscription_id,
  account_id,
  phone_number,
  first_name,
  last_name,
  create_user_id

)
VALUES (
  1,
  1,
  '6623155569',
  'Danny',
  'Trejo',
  1

);

INSERT INTO subscription (
  subscription_id,
  account_id,
  phone_number,
  first_name,
  last_name,
  create_user_id

)
VALUES (
  2,
  2,
  '6623152123',
  'George',
  'Lopez',
  1

);

INSERT INTO subscription (
  subscription_id,
  account_id,
  phone_number,
  first_name,
  last_name,
  create_user_id
)
VALUES (
  3,
  3,
  '6623154774',
  'Kevin',
  'Hart',
  1

);

INSERT INTO subscription (
  subscription_id,
  account_id,
  phone_number,
  first_name,
  last_name,
  create_user_id
)
VALUES (
  4,
  3,
  '6623154879',
  'Bill',
  'Hartly',
  1

);
