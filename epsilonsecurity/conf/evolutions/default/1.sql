# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table db_one_time_availability (
  id                            integer not null,
  user_id                       integer,
  time_start                    integer,
  time_end                      integer,
  constraint pk_db_one_time_availability primary key (id)
);

create table db_one_time_unavailability (
  id                            integer not null,
  user_id                       integer,
  time_start                    integer,
  time_end                      integer,
  constraint pk_db_one_time_unavailability primary key (id)
);

create table db_permission (
  id                            integer not null,
  name                          varchar(255),
  constraint pk_db_permission primary key (id)
);

create table db_recurring_availability (
  id                            integer not null,
  user_id                       integer,
  day                           timestamp,
  frequency                     integer,
  recur_time_start_block        integer,
  recur_time_end_block          integer,
  shift_time_start_block        integer,
  shift_time_end_block          integer,
  constraint pk_db_recurring_availability primary key (id)
);

create table db_role (
  id                            integer not null,
  name                          varchar(255),
  constraint pk_db_role primary key (id)
);

create table db_role_permission (
  id                            integer not null,
  role_id                       integer,
  permission_id                 integer not null,
  constraint pk_db_role_permission primary key (id)
);

create table db_shift (
  id                            integer not null,
  time_start                    integer,
  time_end                      integer,
  was_present                   integer(1) default 0 not null,
  constraint pk_db_shift primary key (id)
);

create table db_team (
  id                            integer not null,
  name                          varchar(255),
  constraint pk_db_team primary key (id)
);

create table db_user (
  id                            integer not null,
  role_id                       integer,
  contact_email                 varchar(255),
  sfu_email                     varchar(255),
  phone_number                  varchar(255),
  photo_url                     varchar(255),
  constraint pk_db_user primary key (id)
);

create table db_user_shift (
  id                            integer not null,
  user_id                       integer,
  shift_id                      integer,
  constraint pk_db_user_shift primary key (id)
);

create table db_user_team (
  id                            integer not null,
  team_id                       integer,
  user_id                       integer,
  constraint pk_db_user_team primary key (id)
);


# --- !Downs

drop table if exists db_one_time_availability;

drop table if exists db_one_time_unavailability;

drop table if exists db_permission;

drop table if exists db_recurring_availability;

drop table if exists db_role;

drop table if exists db_role_permission;

drop table if exists db_shift;

drop table if exists db_team;

drop table if exists db_user;

drop table if exists db_user_shift;

drop table if exists db_user_team;

