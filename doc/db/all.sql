-- 测试表 ----------------------------------------------

drop table if exists `test`;
create table `test`
(
    `id`   char(8) not null default '' comment 'id',
    `name` varchar(50) comment '名称',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4 comment ='测试表';

insert into `test` (id, name)
values (1, '测试1');
insert into `test` (id, name)
values (2, '测试2');


-- 大章表 ----------------------------------------------
drop table if exists `chapter`;
create table `chapter`
(
    `id`        char(8) not null comment 'ID',
    `course_id` char(8) comment '课程ID',
    `name`      varchar(50) comment '名称',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4 comment ='大章';

insert into `chapter` (id, course_id, name)
values ('00000000', '0000000', '测试大章00');
insert into `chapter` (id, course_id, name)
values ('00000001', '0000000', '测试大章01');
insert into `chapter` (id, course_id, name)
values ('00000002', '0000000', '测试大章02');
insert into `chapter` (id, course_id, name)
values ('00000003', '0000000', '测试大章03');
insert into `chapter` (id, course_id, name)
values ('00000004', '0000000', '测试大章04');
insert into `chapter` (id, course_id, name)
values ('00000005', '0000000', '测试大章05');
insert into `chapter` (id, course_id, name)
values ('00000006', '0000000', '测试大章06');
insert into `chapter` (id, course_id, name)
values ('00000007', '0000000', '测试大章07');
insert into `chapter` (id, course_id, name)
values ('00000008', '0000000', '测试大章08');
insert into `chapter` (id, course_id, name)
values ('00000009', '0000000', '测试大章09');
insert into `chapter` (id, course_id, name)
values ('00000010', '0000000', '测试大章10');
insert into `chapter` (id, course_id, name)
values ('00000011', '0000000', '测试大章11');
insert into `chapter` (id, course_id, name)
values ('00000012', '0000000', '测试大章12');
insert into `chapter` (id, course_id, name)
values ('00000013', '0000000', '测试大章13');


-- 小节 ----------------------------------------------
drop table if exists `section`;
create table `section`
(
    `id`         char(8)     not null default '' comment 'id',
    `title`      varchar(50) not null comment '标题',
    `course_id`  char(8) comment '课程|course.id',
    `chapter_id` char(8) comment '大章|chapter.id',
    `video`      varchar(200) comment '视频',
    `time`       int comment '时长|单位秒',
    `charge`     char(1) comment '收费|C 收费；F 免费',
    `sort`       int comment '顺序',
    `created_at` datetime(3) comment '创建时间',
    `updated_at` datetime(3) comment '修改时间',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4 comment ='小节';

alter table `section`
    add column (`vod` char(32) comment 'vod|阿里云vod');

insert into `section` (id, title, course_id, chapter_id, video, time, charge, sort, created_at, updated_at)
values ('00000001', '测试小节01', '00000001', '00000000', '', 500, 'f', 1, now(), now());


-- 课程 ----------------------------------------------
drop table if exists `course`;
create table `course`
(
    id         char(8)     not null default '' comment 'id',
    name       varchar(50) not null comment '名称',
    summary    varchar(2000) comment '概述',
    time       int                  default 0 comment '时长|单位秒',
    price      decimal(8, 2)        default 0.00 comment '价格（元）',
    image      varchar(100) comment '封面',
    level      char(1) comment '级别|枚举[CourseLevelEnum]：{ONE("1", "初级")|TWO("2", "中级")|THREE("3", "高级")}',
    charge     char(1) comment '收费|枚举[CourseChargeEnum]：{CHARGE("C", "收费")|FREE("F", "免费")}',
    status     char(1) comment '状态|枚举[CourseStatusEnum]：{PUBLISH("P", "发布")|DRAFT("D", "草稿")}',
    enroll     integer              default 0 comment '报名数',
    sort       int comment '顺序',
    created_at datetime(3) comment '创建时间',
    updated_at datetime(3) comment '修改时间',
    primary key (id)
) engine = innodb
  default charset = utf8mb4 comment ='课程';

insert into `course` (id, name, summary, time, price, image, level, charge, status, enroll, sort, created_at, updated_at)
values ('00000001', '测试课程01', '这是一门测试课程', 7200, 19.9, '', 1, 'C', 'P', 100, 0, now(), now());

alter table `course`
    add column (`teacher_id` char(8) comment '讲师|teacher.id');