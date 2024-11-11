show databases;

drop database if exists surl_dev;
create database surl_dev;
use surl_dev;

show tables;

desc article;
desc `member`;

select * from article;
select * from `member`;

TRUNCATE table article;
TRUNCATE table `member`;

#############################

create database surl_dev;
drop database surl_dev;

select datname from pg_database;

SELECT tablename
FROM pg_catalog.pg_tables
WHERE schemaname != 'pg_catalog'
AND schemaname != 'information_schema';

create table post (
                      id bigserial not null,
                      primary key (id),
                      create_date timestamp(6),
                      modify_date timestamp(6),
                      title varchar(255)
);

-- V1
insert into post(id, create_date, modify_date, title)
values (default, NOW(), NOW(), '제목 1');

-- v2
insert into post
(create_date, modify_date, title)
values (NOW(), NOW(), '제목 1');

select P.*
from post AS P
where upper(P.title) like upper('%당근%') escape '\'
order by P.id desc
    limit 10 offset 0;

select * from article;
select * from member;
select * from surl;
