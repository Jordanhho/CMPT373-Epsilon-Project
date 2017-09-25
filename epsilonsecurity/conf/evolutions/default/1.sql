# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table garbage (
  key                           integer not null,
  number                        integer,
  string                        varchar(255),
  constraint pk_garbage primary key (key)
);


# --- !Downs

drop table if exists garbage;

