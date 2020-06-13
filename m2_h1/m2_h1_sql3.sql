USE lianxi01;
#学生表
CREATE TABLE student(
	id INT PRIMARY KEY AUTO_INCREMENT,
	NAME VARCHAR(20), -- 姓名
	city VARCHAR(10), -- 城市
	age INT -- 年龄
);

#老师表
CREATE TABLE teacher(
	id INT PRIMARY KEY AUTO_INCREMENT,
	NAME VARCHAR(20) -- 姓名
);

#课程表
CREATE TABLE course(
	id INT PRIMARY KEY AUTO_INCREMENT,
	NAME VARCHAR(20), -- 课程名
	teacher_id INT,  -- 外键 对应老师表 主键id
	FOREIGN KEY (teacher_id) REFERENCES teacher(id)
);

#学生与课程中间表
CREATE TABLE student_course(
	student_id INT, -- 外键 对应学生表主键
	course_id INT, -- 外键 对应课程表主键
	score INT,	-- 某学员 某科的 考试分数
	FOREIGN KEY (student_id) REFERENCES student(id),
	FOREIGN KEY (course_id) REFERENCES course(id)
);

INSERT INTO teacher VALUES(NULL,'关羽');
INSERT INTO teacher VALUES(NULL,'张飞');
INSERT INTO teacher VALUES(NULL,'赵云');

INSERT INTO student VALUES(NULL,'小王','北京',20);
INSERT INTO student VALUES(NULL,'小李','上海',18);
INSERT INTO student VALUES(NULL,'小周','北京',22);
INSERT INTO student VALUES(NULL,'小刘','北京',21);
INSERT INTO student VALUES(NULL,'小张','上海',22);
INSERT INTO student VALUES(NULL,'小赵','北京',17);
INSERT INTO student VALUES(NULL,'小蒋','上海',23);
INSERT INTO student VALUES(NULL,'小韩','北京',25);
INSERT INTO student VALUES(NULL,'小魏','上海',25);
INSERT INTO student VALUES(NULL,'小明','北京',20);

INSERT INTO course VALUES(NULL,'语文',1);
INSERT INTO course VALUES(NULL,'数学',1);
INSERT INTO course VALUES(NULL,'生物',2);
INSERT INTO course VALUES(NULL,'化学',2);
INSERT INTO course VALUES(NULL,'物理',2);
INSERT INTO course VALUES(NULL,'英语',3);

INSERT INTO student_course VALUES(1,1,80);
INSERT INTO student_course VALUES(1,2,90);
INSERT INTO student_course VALUES(1,3,85);
INSERT INTO student_course VALUES(1,4,78);

INSERT INTO student_course VALUES(2,2,53);
INSERT INTO student_course VALUES(2,3,77);
INSERT INTO student_course VALUES(2,5,80);
INSERT INTO student_course VALUES(3,1,71);
INSERT INTO student_course VALUES(3,2,70);
INSERT INTO student_course VALUES(3,4,80);
INSERT INTO student_course VALUES(3,5,65);
INSERT INTO student_course VALUES(3,6,75);
INSERT INTO student_course VALUES(4,2,90);
INSERT INTO student_course VALUES(4,3,80);
INSERT INTO student_course VALUES(4,4,70);
INSERT INTO student_course VALUES(4,6,95);
INSERT INTO student_course VALUES(5,1,60);
INSERT INTO student_course VALUES(5,2,70);
INSERT INTO student_course VALUES(5,5,80);
INSERT INTO student_course VALUES(5,6,69);
INSERT INTO student_course VALUES(6,1,76);
INSERT INTO student_course VALUES(6,2,88);
INSERT INTO student_course VALUES(6,3,87);
INSERT INTO student_course VALUES(7,4,80);
INSERT INTO student_course VALUES(8,2,71);
INSERT INTO student_course VALUES(8,3,58);
INSERT INTO student_course VALUES(8,5,68);
INSERT INTO student_course VALUES(9,2,88);
INSERT INTO student_course VALUES(10,1,77);
INSERT INTO student_course VALUES(10,2,76);
INSERT INTO student_course VALUES(10,3,80);
INSERT INTO student_course VALUES(10,4,85);
INSERT INTO student_course VALUES(10,5,83);


# 第一题
	-- 1、查询平均成绩大于70分的同学的学号,姓名,和平均成绩
	-- 1.1 分组查询每个学生的 学号,姓名,平均分
	-- 1.2 增加条件：平均成绩大于70
	SELECT 
		s.`NAME`,
		s.`id`,
		ss.avg_score
		
	FROM 
		student s,
		(
			SELECT 
				student_id,
				AVG(score) AS avg_score
			
			FROM 
				student_course
			
			GROUP BY 
				student_id
		) ss
	WHERE 
		s.id = ss.student_id AND ss.avg_score > 70

# 第二题
	-- 2. 查询所有同学的学号、姓名、选课数、总成绩
	-- 2.1 需要查询两张表 student表和 student_course表
	-- 2.2 需要使用 student_id 学号字段,进行分组
	-- 2.3 需要使用到 count函数 sum函数
	SELECT 
		s.`id`,
		s.`NAME`,
		ss.course_nums,
		ss.total_score
		
	FROM 
		student s,
		(
			SELECT 
				student_id,
				COUNT(1) AS course_nums,
				SUM(score) AS total_score
			
			FROM 
				student_course
			
			GROUP BY 
				student_id
		) ss
	WHERE 
		s.id = ss.student_id 

# 第三题
	-- 3. 查询学过赵云老师课程的同学的学号、姓名
	-- 3.1 查询赵云老师的id
	-- 3.2 根据老师ID,在课程表中查询所教的课程编号
	-- 3.3 将上面的子查询作为 where 后面的条件
	SELECT 
		id,
		NAME
	FROM student
	WHERE
		id IN (
		
		# (2)选择赵云课程的学生id
		SELECT student_id
		FROM student_course
		
		# (1) 赵云所教授的课程id
		WHERE course_id = (
			SELECT 
			id AS course_id
			FROM teacher
			WHERE NAME='赵云')
		)
	

	

		

# 第四题
	-- 4. 查询选课 少于三门学科的学员			
	-- 4.1 查询每个学生学了几门课 条件1：小于等于三门
	-- 4.2 查询 学号和姓名， 将4.1 作为临时表
	SELECT 
		s.id,
		s.name,
		ss1.course_nums
		
	FROM 
		student s,
		(
		SELECT 
			student_id,
			COUNT(1) AS course_nums
	
		FROM 
			student_course
		
		GROUP BY 
			student_id 
		HAVING course_nums <=3

		) ss1
	WHERE s.`id` = ss1.student_id
	
	
	



