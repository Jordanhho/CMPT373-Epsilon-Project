# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table one_time_availability (
  id                            integer not null,
  user_id                       integer,
  constraint pk_one_time_availability primary key (id)
);

create table one_time_unavailibility (
  id                            integer not null,
  user_id                       integer,
  constraint pk_one_time_unavailibility primary key (id)
);

create table permission (
  id                            integer not null,
  name                          varchar(255),
  constraint pk_permission primary key (id)
);

create table recurring_availability (
  id                            integer not null,
  user_id                       integer,
  day                           timestamp,
  frequency                     integer,
  constraint pk_recurring_availability primary key (id)
);

create table role (
  id                            integer not null,
  name                          varchar(255),
  constraint pk_role primary key (id)
);

create table role_permission (
  id                            integer not null,
  role_id                       integer,
  permission_id                 integer not null,
  constraint pk_role_permission primary key (id)
);

create table shift (
  id                            integer not null,
  was_present                   integer(1) default 0 not null,
  constraint pk_shift primary key (id)
);

create table user (
  id                            integer not null,
  role_id                       integer,
  contact_email                 varchar(255),
  sfu_email                     varchar(255),
  phone_number                  varchar(255),
  photo_url                     varchar(255),
  constraint pk_user primary key (id)
);

create table user_shift (
  id                            integer not null,
  user_id                       integer,
  shift_id                      integer,
  constraint pk_user_shift primary key (id)
);


# --- !Downs

drop table if exists one_time_availability;

drop table if exists one_time_unavailibility;

drop table if exists permission;

drop table if exists recurring_availability;

drop table if exists role;

drop table if exists role_permission;

drop table if exists shift;

drop table if exists user;

drop table if exists user_shift;

