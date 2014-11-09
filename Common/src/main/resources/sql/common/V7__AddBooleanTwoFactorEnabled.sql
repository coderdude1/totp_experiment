alter table users add column two_factor_auth_enabled boolean default false;

update users set two_factor_auth_enabled=false;