create table persistent_logins
(
    username  varchar(64)                         not null,
    series    varchar(64)                         not null
        primary key,
    token     varchar(64)                         not null,
    last_used timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
);

INSERT INTO mp.persistent_logins (username, series, token, last_used) VALUES ('lisa', '6PzBTO3X84KlbtUDFZsH1Q==', 'yzpk5XzS7smmf08FZrLJ6Q==', '2021-06-21 08:09:01');
create table users
(
    id       int auto_increment
        primary key,
    username varchar(20) null,
    password varchar(20) null
);

INSERT INTO mp.users (id, username, password) VALUES (1, 'lisa', '123');