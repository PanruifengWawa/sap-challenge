drop database if exists job;
create database job;

use job;

create table company(
    id serial primary key,
    user_name varchar(256) unique not null,
    password varchar(32) not null,
    name varchar(256) not null,
    phone varchar(32),
    address varchar(512),
    description text
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table token(
    id serial primary key,
    company_id bigint(20) unsigned not null,
    token_str varchar(64) not null unique,
    login_date date not null,
    foreign key(company_id) references company(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists job;
create table job(
    id serial primary key,
    position varchar(512) not null,
    company_id bigint(20) unsigned not null,
    description text,
    foreign key(company_id) references company(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists examination;
create table examination(
    id serial primary key,
    content text not null,
    job_id bigint(20) unsigned not null,
    foreign key(job_id) references job(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists job_seeker;
create table job_seeker(
    id serial primary key,
    access_id varchar(32) unique not null,
    score int not null default 0,
    finished_date date,
    choice text,
    examination_id bigint(20) unsigned not null,
    foreign key(examination_id) references examination(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
