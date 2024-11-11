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

