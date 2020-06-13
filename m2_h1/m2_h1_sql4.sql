CREATE DATABASE lagou_edu  CHARACTER SET utf8;
USE lagou_edu ;

# 讲师表
CREATE TABLE lagou_teacher(
	t_id INT, -- 师ID 主键 int类型
	NAME VARCHAR(10),
	des VARCHAR(100),
	title CHAR -- 0 高级讲师', 1 '首席讲师

);
ALTER TABLE lagou_teacher ADD PRIMARY KEY(t_id) ;
	
# 课程分类表
CREATE TABLE lagou_subject(
	subject_id INT PRIMARY KEY , -- 课程分类ID 
	c_name VARCHAR(20), -- 课程名
	c_des VARCHAR(100), -- 课程分类描述 VARCHAR类型
	start_time DATETIME,
	update_time DATETIME

);

# 课程分类表

CREATE TABLE lagou_course(
	id INT PRIMARY KEY AUTO_INCREMENT,  -- 课程ID 主键 int类型
	teacher_id INT,
	course_id INT,
	course_name VARCHAR(20), -- 课程标题 VARCHAR类型 比如Java VUE PHP ......
	course_times INT, -- 总课时 
	c_checked BIGINT, --  浏览数量 
	c_status CHAR --  0 未发布(默认)  1 已发布
);

# ALTER TABLE 从表 ADD [CONSTRAINT] [外键约束名称] FOREIGN KEY (外键字段名) REFERENCES
# 主表(主 键字段名);

ALTER TABLE lagou_course ADD FOREIGN KEY (teacher_id ) REFERENCES lagou_teacher(t_id);
ALTER TABLE lagou_course ADD FOREIGN KEY (course_id ) REFERENCES lagou_subject(subject_id);

-- 向讲师表插入两条数据
INSERT INTO lagou_teacher VALUES (1, '刘德华', '毕业于清华大学，主攻前端技术,授课风格生动活泼,深受学员喜爱', '0');
INSERT INTO lagou_teacher VALUES (2, '郭富城', '毕业于北京大学，多年的IT经验，研发多项Java课题,授课经验丰富', '1');

-- 向课程分类表中插入两条数据
INSERT INTO lagou_subject VALUES (1, '后端开发', '后端Java PHP Python', '2020-03-27 00:44:04', '2020-03-27 00:44:04');
INSERT INTO lagou_subject VALUES (2, '前端开发', 'VUE angularJS', '2020-02-27 10:00:04', '2020-02-27 18:44:04');


-- 向课程表中插入两条数据
-- 插入Java课程
INSERT INTO lagou_course VALUES (1,1,1 ,'Java', 300,250000, '1');
-- 插入VUE课程
INSERT INTO lagou_course VALUES (2,2,2, 'VUE', 400,200000,'1');


# 查询需求
# 查询刘德华老师所教的课程属于哪个课程分类

SELECT 
	ls.subject_id,
	ls.c_name,
	ls.c_des,
	ls.start_time,
	ls.update_time
FROM 
	lagou_subject ls,
	lagou_course lc
WHERE 
	ls.subject_id = lc.`course_id` AND lc.`teacher_id` = ( SELECT t_id FROM lagou_teacher WHERE NAME= '刘德华')
