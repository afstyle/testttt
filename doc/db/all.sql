
-- 大章表
drop table if exists `chapter`;
create table `chapter` (
    `id` char(8) not null comment 'ID',
    `course_id` char(8) comment '课程ID',
    `name` varchar(50) comment '名称',
    primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='大章';

insert into `chapter` (id, course_id, name) values ('00000000', '0000000', '测试大章00');
insert into `chapter` (id, course_id, name) values ('00000001', '0000000', '测试大章01');
insert into `chapter` (id, course_id, name) values ('00000002', '0000000', '测试大章02');
insert into `chapter` (id, course_id, name) values ('00000003', '0000000', '测试大章03');
insert into `chapter` (id, course_id, name) values ('00000004', '0000000', '测试大章04');
insert into `chapter` (id, course_id, name) values ('00000005', '0000000', '测试大章05');
insert into `chapter` (id, course_id, name) values ('00000006', '0000000', '测试大章06');
insert into `chapter` (id, course_id, name) values ('00000007', '0000000', '测试大章07');
insert into `chapter` (id, course_id, name) values ('00000008', '0000000', '测试大章08');
insert into `chapter` (id, course_id, name) values ('00000009', '0000000', '测试大章09');
insert into `chapter` (id, course_id, name) values ('00000010', '0000000', '测试大章10');
insert into `chapter` (id, course_id, name) values ('00000011', '0000000', '测试大章11');
insert into `chapter` (id, course_id, name) values ('00000012', '0000000', '测试大章12');
insert into `chapter` (id, course_id, name) values ('00000013', '0000000', '测试大章13');



-- 测试表

drop table if exists `test`;
create table `test` (
    `id` char(8) not null default '' comment 'id',
    `name` varchar(50) comment '名称',
    primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='测试表';

insert into `test` (id, name) values (1, '测试1');
insert into `test` (id, name) values (2, '测试2');
