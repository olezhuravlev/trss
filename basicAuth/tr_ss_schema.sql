# Database schema setup.

create table users
(
    username varchar(75)  not null primary key,
    password varchar(150) not null,
    enabled  boolean      not null
);

create table authorities
(
    username  varchar(75) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key (username) references users (username)
);

-- User: 'admin', password: 'admin'
insert into users(username, password, enabled)
values ('admin', '$2a$04$dEMU/ByMXHaR/8jO10rzXOqOtC8FHrazgm835jPWfLZGROjrQNt0u', true);

insert into authorities(username, authority)
values ('admin', 'ROLE_ADMIN');

-- User: 'user', password: 'user'
insert into users(username, password, enabled)
values ('user', '$2a$04$EwQSrCBzFSP/css1FWhBSu9kaFLxPNuV53G4CWYmwWp6W3AF8Hs.O', true);

insert into authorities(username, authority)
values ('user', 'ROLE_USER');
