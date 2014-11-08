CREATE TABLE ROLES (
  id serial primary key,
  role varchar(40) NOT NULL UNIQUE
);

-- ALTER TABLE ROLES ALTER COLUMN role SET NOT NULL;
-- ALTER TABLE ROLES ADD CONSTRAINT UNIQUE(role);

CREATE TABLE USER_ROLE (
  user_id integer,
  role_id integer
);

ALTER TABLE USER_ROLE ADD CONSTRAINT user_role_uk UNIQUE(user_id, role_id);

ALTER TABLE USER_ROLE ADD FOREIGN KEY (user_id) REFERENCES USERS (id);

ALTER TABLE USER_ROLE ADD FOREIGN KEY (role_id) REFERENCES ROLES (id);

ALTER TABLE USERS ADD COLUMN password_hash varchar(128);
