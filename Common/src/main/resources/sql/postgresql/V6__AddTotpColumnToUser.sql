ALTER TABLE USERS ADD COLUMN totp_secret varchar(20);
ALTER TABLE USERS ADD COLUMN totp_secret_create_date timestamp with timezone;
