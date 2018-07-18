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
  (
    SELECT user_id_seq.nextval
    FROM dual),

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
  (
    SELECT customer_id_seq.nextval
    FROM dual),

  'USER_01_FIRST',
  'USER_01_LAST',
  'PLACE HOLDER'
);
