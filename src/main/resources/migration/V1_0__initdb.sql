create table schedule (
      id bigserial not null primary key ,
      aud_num int,
      time varchar(50),
      day_week int,
      teacher varchar(50),
      name_group varchar(50)
);
insert into schedule(aud_num, time, day_week, teacher, name_group)
values ('1409', '08:30', 5, 'teacher', '11-301');
