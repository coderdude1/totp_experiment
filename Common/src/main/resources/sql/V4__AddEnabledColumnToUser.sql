ALTER TABLE USERS ADD COLUMN enabled boolean default false;

UPDATE USERS set enabled=true;