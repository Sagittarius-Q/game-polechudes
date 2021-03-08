create sequence history_seq start 1 increment 1;
create sequence question_seq start 1 increment 1;
create sequence user_seq start 1 increment 1;

create table game_history (
    id int8 not null,
     player_name varchar(255),
      primary key (id));

create table game_history_question_name (
    game_history_id int8 not null,
    question_name varchar(255));

create table question (
    id int8 not null,
    answer varchar(255),
    hint varchar(255),
    name varchar(255),
    primary key (id));

create table user_role (
    user_id int8 not null,
     roles varchar(255));

create table users (
    id int8 not null,
    email varchar(255),
    name varchar(255),
    password varchar(255),
    primary key (id));

alter table if exists game_history add constraint UK_7dool0rmuauwpejocqrai6nw1 unique (player_name);
alter table if exists question add constraint UK_ebxkfbn6q50fuc6n0qrrpvmh8 unique (name);
alter table if exists users add constraint UK_3g1j96g94xpk3lpxl2qbl985x unique (name);
alter table if exists game_history_question_name add constraint FKgtxjjnet43d8bv8nmetcv1r3x foreign key (game_history_id) references game_history;