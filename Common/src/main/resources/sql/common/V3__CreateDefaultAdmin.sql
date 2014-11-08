--default password is tmp14now
insert into users(first_name, last_name, email, password_hash)
values('default', 'admin', 'drbugsmn@gmail.com', '$2a$10$ipMbW.Ij2VvkePc3TJTA3OcH8OYA0fxgf7Smg/cAeYhMlFCbaBbjW');

--create some default roles
insert into roles(role)
values ('ADMIN');

insert into roles(role)
values ('SITE_ADMIN');

insert into roles(role)
values ('LTD_ADMIN');

insert into roles(role)
values ('STD_USER');

insert into USER_ROLE(user_id, role_id)
select a.id, b.id
from users a, roles b
where a.email='drbugsmn@gmail.com'
and b.role='ADMIN';
