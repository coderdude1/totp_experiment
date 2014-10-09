CREATE TABLE users (
  id         serial primary key,
  first_name varchar(40),
  last_name  varchar(50),
  email      varchar(512)
);

ALTER TABLE users ALTER COLUMN first_name SET NOT NULL;
ALTER TABLE users ALTER COLUMN last_name SET NOT NULL;
ALTER TABLE users ALTER COLUMN email SET NOT NULL;