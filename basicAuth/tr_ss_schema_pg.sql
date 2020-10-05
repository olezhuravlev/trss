CREATE DATABASE ss
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

\connect ss;

CREATE SCHEMA tr_ss_schema;

create table tr_ss_schema.users
(
    username varchar(75)  not null primary key,
    password varchar(150) not null,
    enabled  boolean      not null
);

create table tr_ss_schema.authorities
(
    username  varchar(75) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key (username) references tr_ss_schema.users (username)
);

-- User: 'admin', password: 'admin'
insert into tr_ss_schema.users(username, password, enabled)
values ('admin', '$2a$04$dEMU/ByMXHaR/8jO10rzXOqOtC8FHrazgm835jPWfLZGROjrQNt0u', true);

insert into tr_ss_schema.authorities(username, authority)
values ('admin', 'ROLE_ADMIN');

-- User: 'user', password: 'user'
insert into tr_ss_schema.users(username, password, enabled)
values ('user', '$2a$04$EwQSrCBzFSP/css1FWhBSu9kaFLxPNuV53G4CWYmwWp6W3AF8Hs.O', true);

insert into tr_ss_schema.authorities(username, authority)
values ('user', 'ROLE_USER');

select * from tr_ss_schema.users;
select * from tr_ss_schema.authorities;
