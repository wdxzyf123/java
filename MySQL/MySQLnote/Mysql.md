#                                                     1. Mysql

现成网页版<https://blog.csdn.net/weixin_43591980/article/details/109526825>

```mysql
net start mysql
mysql -u root -p123456
-- 在DOS命令行窗口进入 安装目录\mysql\bin
-- 可设置环境变量，设置了环境变量，可以在任意目录打开！
update user set password=password('123456')where user='root'; 修改密码
flush privileges; 刷新数据库
show databases; 显示所有数据库
use dbname；打开某个数据库
show tables; 显示数据库mysql中所有的表
describe user; 显示表mysql数据库中user表的列信息
create database name; 创建数据库
use databasename; 选择数据库

exit; 退出Mysql
? 命令关键词 : 寻求帮助
-- 表示注释

```

## CRUD增删改查

* DDL 数据库定义语言
* DML 数据库操作语言
* DQL 数据库查询语言
* DCL 数据库控制语言

# 2、操作数据库

## 2.1、操作数据库中的表 操作数据库中的表的数据

mysql不区分大小写

1、 创建数据库

```mysql
CREATE DATABASE IF NOT EXISTS westos;
```

2、 删除数据库

```mysql
DROP DATABASE IF EXISTS westos;
```

3、 使用数据库

```mysql
USE school
USE `user` -- 如果字段里有特殊字符用``圈起来
```

4、查看数据库

```mysql
show databases; 显示所有数据库
```

## 2.2 、数据库的列类型

> 数值

* tinyint  十分小的数据 一个字节
* smallint 较小的数据两个字节
* mediumint 三个字节
* **int 标准的整型 四个字节**
* big 较大的数据 八个字节
* float 单精度浮点数 4个字节
* double 双精度浮点数 8 个字节
* decimal 字符串形式的浮点数 金融计算用

> 字符串

* char 一个字节 0~255

* **varchar 可变字符串 0~65535 常用的变量**

* tinytext 微型文本 0~2^8-1  够写一篇博客了

* **text 文本串 0~2^16-1 保存大文本**

  > 时间日期

* data YYY-MM-DD

* time HH:MM:ss

* **datatime YYY-MM-DD HH:MM:ss**

* **timestamp 时间戳 1970.1.1到现在的毫秒数**

> null

* 没有值，未知

## 2.3 、数据库的字段属性

==Unsigned:==

* 无符号整数
* 生命了该列不能声明为负数

==zerofill==

* 0填充
* 不足的位数用0 填充 int(3) , 5   >  005

==自增==

* 自动在上一条记录的基础上 +1（默认）
* 通常用来设计唯一的主键~ index ，必须是整型
* 自已自定义主键自增的起始值和步长  4高级

 ==非空==

* NULL 和 not null
* 假设设置为not null ,如果不给他赋值就报错
* 亦然

==默认==

* 设置默认值

==拓展部份==

```mysql
/*
每一个表，都必须存在这五个字段，为了规范使用
id 主键
`version` 乐观锁
is_delete 伪删除
gmt_create 创建时间
gmt_update 修改时间
*/
```



## 2.4 、sql创建表

```mysql
 -- 目标：创建一个school数据库
 -- 创建学生表（列，字段）使用sql创建
 -- 学号int 登陆密码varchar（20） 姓名，性别varchar（2）
 -- 出生日期（datatime） ，家庭住址，email
 -- 表的名称和字段尽量用``括起来
 -- 字符串使用单引号''括起来
 -- 所有语句后面间隔加  ,  最后一个不用加
 CREATE TABLE IF NOT EXISTS `student2` (
	`id` INT(4) NOT NULL AUTO_INCREMENT COMMENT '学号',
	`name` VARCHAR(30) NOT NULL DEFAULT '匿名' COMMENT '姓名',
	`pwd` VARCHAR(20) NOT NULL DEFAULT '123456' COMMENT'密码',
	`sex` VARCHAR(2) NOT NULL DEFAULT '女' COMMENT'性别',
	`birthday` DATETIME DEFAULT NULL COMMENT '出生日期',
	`address` VARCHAR(100) DEFAULT NULL COMMENT'家庭住址',
	`email` VARCHAR(50) DEFAULT NULL COMMENT'邮箱',
	PRIMARY KEY(`id`) -- 设置主键
 ) ENGINE=INNODB DEFAULT CHARSET=UTF8 -- 引擎和字符集编码格式
 
 CREATE TABLE [IF NOT EXISTS] `表名`(
	`字段名` 列类型 [属性] [索引] [注释],
	`字段名` 列类型 [属性] [索引] [注释],
	......
	`字段名` 列类型 [属性] [索引] [注释]
 )[表类型][字符集设置][注释]
```

==常用命令==

```mysql
SHOW CREATE DATABASE school; -- 看创建数据库的语句
SHOW CREATE TABLE student; -- 查看创建数据表的语句
DESC student; -- 显示表的结构
```

==数据库引擎==

```mysql
/*
INNODB 默认使用
MYISAM 早些年使用
*/
```

|                              | MYISAM            | INNODB       |
| ---------------------------- | ----------------- | ------------ |
| 事务支持（一半成功也能上传） | 不支持            | 支持         |
| 数据行锁定                   | 不支持 支持表锁定 | 支持         |
| 外键约束                     | 不支持            | 支持         |
| 全文索引                     | 支持              | 不支持       |
| 表空间大小                   | 较小              | 较大 大约2倍 |

常规使用操作：

* MYISAM  节约空间 速度较快
* INNODB  安全性高，事物的处理，多表多用户操作

> 这两种引擎在物理空间上的位置 

所有的数据库文件本质上还是文件 都存放在data目录下

MySQL引擎在物理文件上的区别：

* innoDB在数据库表中只有一个*.frm文件和上级目录下的ibdata1文件
* MYISAM 对应的文件
  * *.frm - 表结构的定义文件
  * *.MYD 数据文件(data)
  * *.MYI 索引文件(index)

## 2.5 、修改、删除表

```mysql
-- 修改表名: ALTER TABLE 旧的表名 RENAME AS 新的表名
ALTER TABLE student RENAME AS teacher
-- 增加表的字段 ALTER TABLE 表名 ADD 字段名 列属性
ALTER TABLE student ADD age INT(11)
-- 修改表的字段（重命名，修改约束）
ALTER TABLE student MODIFY age VARCHAR(11) -- 修改约束
ALTER TABLE student CHANGE age age1 INT(1) -- 字段重命名
-- 删除表中的字段
ALTER TABLE student DROP age1
-- 删除表
DROP TABLE IF EXISTS student
```

# 3、MySQL数据管理

## 3.1、外键（了解）

![image-20210813170220837](D:\Anew_home\java\MySQL\MySQLnote\Mysql.assets\image-20210813170220837.png)

>方式一：在创建表的时候，增加约束  较为麻烦 一般不用

```mysql
/*
 学生表的gradeid字段取引用年级表的gradeid:
 1.定义外键KEY
 2.给这个外键添加约束（执行引用）
*/
CREATE TABLE `grade`(
	`gradeid` INT(10) NOT NULL AUTO_INCREMENT COMMENT '年纪id',
	`gradename` VARCHAR(50) NOT NULL COMMENT '年纪名称',
) ENGINE=INNODB DEFAULT CHARSET=utf8

 CREATE TABLE IF NOT EXISTS `student`(
	`id` INT(4) NOT NULL AUTO_INCREMENT COMMENT '学号',
	`name` VARCHAR(30) NOT NULL DEFAULT '匿名' COMMENT '姓名',
	`pwd` VARCHAR(20) NOT NULL DEFAULT '123456' COMMENT '密码',
	`sex` VARCHAR(2) NOT NULL DEFAULT '女' COMMENT '性别',
	`birthday` DATETIME DEFAULT NULL COMMENT '出生日期',
	`gradeid` INT(10) NOT NULL COMMENT '学生的年级',
	`address` VARCHAR(100) DEFAULT NULL COMMENT '家庭住址',
	`email` VARCHAR(50) DEFAULT NULL COMMENT '邮箱',
	PRIMARY KEY(`id`),
	KEY `FK_gradeid` (`gradeid`),
	CONSTRAINT `FK_gradeid` FOREIGN_KEY_CHECKS (`gradeid`) REFERENCES `grade` (`gradeid`)
 ) ENGINE=INNODB DEFAULT CHARSET=UTF8

```

https://www.w3school.com.cn/sql/sql_foreignkey.asp这里是详细的介绍

```mysql
CREATE TABLE `grade`(
	`gradeid` INT(10) NOT NULL AUTO_INCREMENT COMMENT '年级id',
	`gradename` VARCHAR(50) NOT NULL COMMENT '年级名称',
	PRIMARY KEY(`gradeid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8
-- 创建表的时候添加外键
CREATE TABLE IF NOT EXISTS `student`(
	`id` INT(4) NOT NULL AUTO_INCREMENT COMMENT '学号',
	`name` VARCHAR(30) NOT NULL DEFAULT '匿名' COMMENT '姓名',
	`pwd` VARCHAR(20) NOT NULL DEFAULT '123456' COMMENT '密码',
	`sex` VARCHAR(2) NOT NULL DEFAULT '女' COMMENT '性别',
	`birthday` DATETIME DEFAULT NULL COMMENT '出生日期',
	`gradeid` INT(10) NOT NULL COMMENT '学生的年级',
	`address` VARCHAR(100) DEFAULT NULL COMMENT '家庭住址',
	`email` VARCHAR(50) DEFAULT NULL COMMENT '邮箱',
	PRIMARY KEY(`id`),
	CONSTRAINT fk_student FOREIGN KEY (`gradeid`) REFERENCES `grade`(`gradeid`)
 ) ENGINE=INNODB DEFAULT CHARSET=utf8
-- 创建表之后再添加外键
ALTER TABLE `student`
ADD CONSTRAINT fk_PerOrders FOREIGN KEY (`gradeid`) REFERENCES `grade`(`gradeid`);
```

==上面的外键方法一般不适用==

==最佳实践方法==

* 数据库就是单纯的表，只是用来存数据的，只有行（数据）和列（字段）
* 我们i昂使用多张表的数据，想使用外键就用程序去实现。

* 阿里java规范 强制不得使用外键和级联，一切外键概念必须在应用层解决
* 因为在删除或者升级的时候，考虑外键约束会十分痛苦。



## 3.2、 DML语言（全部记住）

**数据库存在的意义**：数据存储，数据管理

DML语言：数据库操作语言

* insert (插入语句)
* update
* delete



## 3.3、添加

> insert

```mysql
INSERT INTO 表名[(字段1,字段2,字段3,...)] VALUES('值1','值2','值3')
INSERT INTO `grade` (`gradename`) VALUES('大四')
INSERT INTO `grade` (`gradename`) VALUES('大四'), ('大三')
INSERT INTO `student` (`name`, `pwd`, `sex`) VALUES ('李四', 'aaa', '男生'), ('王子', 'sss', '男生')
--字段和值要一一对应，可以有多条对应
```



## 3.4、修改

> update         

```mysql
--语法
UPDATE 表名 SET colnum_name = value, [clonum_name = value,...] where[条件]
-- 修改学员名字， 带条件
-- 不带条件会全都改掉，非常危险
UPDATE `student` SET `name`='wsk' WHERE id BETWEEN 5 AND 6 
```



## 3.3、删除

> delete

```mysql
--语法
delete from 表名 where[条件]
-- 删除指定数据 
DELETE FROM `student` WHERE id = 5
-- 危险操作
DELETE FROM `student` 
```

> 完全清空数据库表   TRUNCATE

```mysql
-- 完全清空一个数据库表，但是表的结构和索引约束不会变
TRUNCATE TABLE '表明'
```

-- 和直接delete的区别
-- 都能删除删除数据，都不会删除表的结构
-- 不同：
-- TRUNCATE 重新设置 自增列 计数器归零  不影响事务

直接delete的影响，重启数据库，会出现的现象：

* innoDB 自增列会从1开始（因为存在内存中，断电即失）
* MyISAM 继续从上一个自增量开始（因为存在文件中，不会丢失）

# 4、==DQL查询数据（最重点）==

## 4.1、DQL

(Data Query LANGUAGE: 数据库查询语言)

* 所有的查询操作都用 Select

  ```mysql
  SELECT [ALL | DISTINCT]
  {* | table.* | [table.field1[as alias1][,table.field2[as alias2]][,...]]}
  FROM table_name [as table_alias]
    [left | right | inner join table_name2]  -- 联合查询
    [WHERE ...]  -- 指定结果需满足的条件
    [GROUP BY ...]  -- 指定结果按照哪几个字段来分组
    [HAVING]  -- 过滤分组的记录必须满足的次要条件
    [ORDER BY ...]  -- 指定查询记录按一个或多个条件排序
    [LIMIT {[offset,]row_count | row_countOFFSET offset}];
     -- 指定查询的记录从哪条至哪条
  
  ```

  

```mysql
CREATE DATABASE IF NOT EXISTS `school`;
-- 创建一个school数据库
USE `school`;-- 创建学生表
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`(
	`studentno` INT(4) NOT NULL COMMENT '学号',
    `loginpwd` VARCHAR(20) DEFAULT NULL,
    `studentname` VARCHAR(20) DEFAULT NULL COMMENT '学生姓名',
    `sex` TINYINT(1) DEFAULT NULL COMMENT '性别，0或1',
    `gradeid` INT(11) DEFAULT NULL COMMENT '年级编号',
    `phone` VARCHAR(50) NOT NULL COMMENT '联系电话，允许为空',
    `address` VARCHAR(255) NOT NULL COMMENT '地址，允许为空',
    `borndate` DATETIME DEFAULT NULL COMMENT '出生时间',
    `email` VARCHAR (50) NOT NULL COMMENT '邮箱账号允许为空',
    `identitycard` VARCHAR(18) DEFAULT NULL COMMENT '身份证号',
    PRIMARY KEY (`studentno`),
    UNIQUE KEY `identitycard`(`identitycard`),
    KEY `email` (`email`)
)ENGINE=MYISAM DEFAULT CHARSET=utf8;

-- 创建年级表
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade`(
	`gradeid` INT(11) NOT NULL AUTO_INCREMENT COMMENT '年级编号',
  `gradename` VARCHAR(50) NOT NULL COMMENT '年级名称',
    PRIMARY KEY (`gradeid`)
) ENGINE=INNODB AUTO_INCREMENT = 6 DEFAULT CHARSET = utf8;`grade`

-- 创建科目表
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject`(
	`subjectno`INT(11) NOT NULL AUTO_INCREMENT COMMENT '课程编号',
    `subjectname` VARCHAR(50) DEFAULT NULL COMMENT '课程名称',
    `classhour` INT(4) DEFAULT NULL COMMENT '学时',
    `gradeid` INT(4) DEFAULT NULL COMMENT '年级编号',
    PRIMARY KEY (`subjectno`)
)ENGINE = INNODB AUTO_INCREMENT = 19 DEFAULT CHARSET = utf8;

-- 创建成绩表
DROP TABLE IF EXISTS `result`;
CREATE TABLE `result`(
	`studentno` INT(4) NOT NULL COMMENT '学号',
    `subjectno` INT(4) NOT NULL COMMENT '课程编号',
    `examdate` DATETIME NOT NULL COMMENT '考试日期',
    `studentresult` INT (4) NOT NULL COMMENT '考试成绩',
    KEY `subjectno` (`subjectno`)
)ENGINE = INNODB DEFAULT CHARSET = utf8;


-- 插入学生数据 其余自行添加 这里只添加了2行
INSERT INTO `student` (`studentno`,`loginpwd`,`studentname`,`sex`,`gradeid`,`phone`,`address`,`borndate`,`email`,`identitycard`)
VALUES
(1000,'123456','张伟',0,2,'13800001234','北京朝阳','1980-1-1','text123@qq.com','123456198001011234'),
(1001,'123456','赵强',1,3,'13800002222','广东深圳','1990-1-1','text111@qq.com','123456199001011233');

-- 插入成绩数据  这里仅插入了一组，其余自行添加
INSERT INTO `result`(`studentno`,`subjectno`,`examdate`,`studentresult`)
VALUES
(1000,1,'2013-11-11 16:00:00',85),
(1000,2,'2013-11-12 16:00:00',70),
(1000,3,'2013-11-11 09:00:00',68),
(1000,4,'2013-11-13 16:00:00',98),
(1000,5,'2013-11-14 16:00:00',58);

-- 插入年级数据
INSERT INTO `grade` (`gradeid`,`gradename`) VALUES(1,'大一'),(2,'大二'),(3,'大三'),(4,'大四'),(5,'预科班');

-- 查询全表
SELECT * FROM `student`
-- 查询指定字段
SELECT `studentno`, `loginpwd` FROM `student`
-- 别名, 给结果取一个别名
SELECT `studentno` AS 学号, `studentname` AS 学生名字 FROM student AS s
-- 拼接字符串 concat(a,b)
SELECT CONCAT('姓名', `studentname`) AS 新名字 FROM student
```



> 去重 distinct

```mysql
-- # 查看哪些同学参加了考试(学号) 去除重复项
SELECT * FROM result; -- 查看考试成绩
SELECT studentno FROM result; -- 查看哪些同学参加了考试
SELECT DISTINCT studentno FROM result; -- 了解:DISTINCT 去除重复项 , (默认是ALL)

```



>select 表达式

```mysql
-- selcet查询中可以使用表达式
SELECT @@auto_increment_increment; -- 查询自增步长
SELECT VERSION(); -- 查询版本号
SELECT 100*3-1 AS 计算结果; -- 表达式

-- 学员考试成绩集体提分一分查看
SELECT studentno,StudentResult+1 AS '提分后' FROM result;

```

## 4.2、where条件语句

> 逻辑操作符

![img](https://img-blog.csdnimg.cn/img_convert/8ff286961cff3b95a5d575f73f24b4c0.png)

> 模糊查询

![img](https://img-blog.csdnimg.cn/img_convert/e8d273d44b5470f4fc78518b19e45342.png)

```mysql
-- 模糊查询 between and \ like \ in \ null

-- =============================================
-- LIKE
-- =============================================
-- 查询姓刘的同学的学号及姓名
-- like结合使用的通配符 : % (代表0到任意个字符) _ (一个字符)
SELECT studentno,studentname FROM student
WHERE studentname LIKE '刘%';

-- 查询姓刘的同学,后面只有一个字的
SELECT studentno,studentname FROM student
WHERE studentname LIKE '刘_';

-- 查询姓刘的同学,后面只有两个字的
SELECT studentno,studentname FROM student
WHERE studentname LIKE '刘__';

-- 查询姓名中含有 嘉 字的
SELECT studentno,studentname FROM student
WHERE studentname LIKE '%嘉%';

-- 查询姓名中含有特殊字符的需要使用转义符号 '\'
-- 自定义转义符关键字: ESCAPE ':'

-- =============================================
-- IN
-- =============================================
-- 查询学号为1000,1001,1002的学生姓名
SELECT studentno,studentname FROM student
WHERE studentno IN (1000,1001,1002);

-- 查询地址在北京,南京,河南洛阳的学生
SELECT studentno,studentname,address FROM student
WHERE address IN ('北京','南京','河南洛阳');

-- =============================================
-- NULL 空
-- =============================================
-- 查询出生日期没有填写的同学
-- 不能直接写=NULL , 这是代表错误的 , 用 is null
SELECT studentname FROM student
WHERE BornDate IS NULL;

-- 查询出生日期填写的同学
SELECT studentname FROM student
WHERE BornDate IS NOT NULL;

-- 查询没有写家庭住址的同学(空字符串不等于null)
SELECT studentname FROM student
WHERE Address='' OR Address IS NULL;

```

## 4.3、 联表查询

> JOIN对比

![img](https://img-blog.csdnimg.cn/img_convert/80f1aea1708ce1e54f5163da3ca85f2a.png)

![img](https://img-blog.csdnimg.cn/img_convert/729e2b7e529b4431466bb7bb781d8efb.png)

```mysql
/*
连接查询
   如需要多张数据表的数据进行查询,则可通过连接运算符实现多个查询
内连接 inner join
   查询两个表中的结果集中的交集
外连接 outer join
   左外连接 left join
       (返回左表中的所有数据，以左表作为基准,右边表来一一匹配,匹配不上的,返回左表的记录,右表以NULL填充)
   右外连接 right join
       (返回右表中的所有数据，以右表作为基准,左边表来一一匹配,匹配不上的,返回右表的记录,左表以NULL填充)
       
join on 连接查询
where 等值查询

自连接
*/

-- 查询参加了考试的同学信息(学号,学生姓名,科目编号,分数)
SELECT * FROM student;
SELECT * FROM result;

/*思路:
(1):分析需求,确定查询的列来源于两个类,student result,连接查询
(2):确定使用哪种连接查询?(内连接)
*/
SELECT s.studentno,studentname,subjectno,StudentResult
FROM student s
INNER JOIN result r
ON r.studentno = s.studentno

-- 右连接(也可实现)
SELECT s.studentno,studentname,subjectno,StudentResult
FROM student s
RIGHT JOIN result r
ON r.studentno = s.studentno

-- 等值连接
SELECT s.studentno,studentname,subjectno,StudentResult
FROM student s , result r
WHERE r.studentno = s.studentno

-- 左连接 (查询了所有同学,不考试的也会查出来)
SELECT s.studentno,studentname,subjectno,StudentResult
FROM student s
LEFT JOIN result r
ON r.studentno = s.studentno

-- 查一下缺考的同学(左连接应用场景)
SELECT s.studentno,studentname,subjectno,StudentResult
FROM student s
LEFT JOIN result r
ON r.studentno = s.studentno
WHERE StudentResult IS NULL

-- 思考题:查询参加了考试的同学信息(学号,学生姓名,科目名,分数)
SELECT s.studentno,studentname,subjectname,StudentResult
FROM student s
INNER JOIN result r
ON r.studentno = s.studentno
INNER JOIN `subject` sub
ON sub.subjectno = r.subjectno

```

> 自链接  

自己的表和自己的表链接，核心：一张表拆分为两张一样的表即可

```mysql
/*
自连接
   数据表与自身进行连接

需求:从一个包含栏目ID , 栏目名称和父栏目ID的表中
    查询父栏目名称和其他子栏目名称
*/

-- 创建一个表
CREATE TABLE `category` (
`categoryid` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主题id',
`pid` INT(10) NOT NULL COMMENT '父id',
`categoryName` VARCHAR(50) NOT NULL COMMENT '主题名字',
PRIMARY KEY (`categoryid`)
) ENGINE=INNODB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8

-- 插入数据
INSERT INTO `category` (`categoryid`, `pid`, `categoryName`)
VALUES('2','1','信息技术'),
('3','1','软件开发'),
('4','3','数据库'),
('5','1','美术设计'),
('6','3','web开发'),
('7','5','ps技术'),
('8','2','办公信息');

-- 编写SQL语句,将栏目的父子关系呈现出来 (父栏目名称,子栏目名称)
-- 核心思想:把一张表看成两张一模一样的表,然后将这两张表连接查询(自连接)
SELECT a.categoryName AS '父栏目',b.categoryName AS '子栏目'
FROM category AS a,category AS b
WHERE a.`categoryid`=b.`pid`

-- 思考题:查询参加了考试的同学信息(学号,学生姓名,科目名,分数)
SELECT s.studentno,studentname,subjectname,StudentResult
FROM student s
INNER JOIN result r
ON r.studentno = s.studentno
INNER JOIN `subject` sub
ON sub.subjectno = r.subjectno

-- 查询学员及所属的年级(学号,学生姓名,年级名)
SELECT studentno AS 学号,studentname AS 学生姓名,gradename AS 年级名称
FROM student s
INNER JOIN grade g
ON s.`GradeId` = g.`GradeID`

-- 查询科目及所属的年级(科目名称,年级名称)
SELECT subjectname AS 科目名称,gradename AS 年级名称
FROM SUBJECT sub
INNER JOIN grade g
ON sub.gradeid = g.gradeid

-- 查询 数据库结构-1 的所有考试结果(学号 学生姓名 科目名称 成绩)
SELECT s.studentno,studentname,subjectname,StudentResult
FROM student s
INNER JOIN result r
ON r.studentno = s.studentno
INNER JOIN `subject` sub
ON r.subjectno = sub.subjectno
WHERE subjectname='数据库结构-1'

```

## 4.4、分页和排序

```mysql
/*============== 排序 ================
语法 : ORDER BY
   ORDER BY 语句用于根据指定的列对结果集进行排序。
   ORDER BY 语句默认按照ASC升序对记录进行排序。
   如果您希望按照降序对记录进行排序，可以使用 DESC 关键字。
   
*/

-- 查询 数据库结构-1 的所有考试结果(学号 学生姓名 科目名称 成绩)
-- 按成绩降序排序
SELECT s.studentno,studentname,subjectname,StudentResult
FROM student s
INNER JOIN result r
ON r.studentno = s.studentno
INNER JOIN `subject` sub
ON r.subjectno = sub.subjectno
WHERE subjectname='数据库结构-1'
ORDER BY StudentResult DESC

/*============== 分页 ================
语法 : SELECT * FROM table LIMIT [offset,] rows | rows OFFSET offset
好处 : (用户体验,网络传输,查询压力)

推导:
   第一页 : limit 0,5
   第二页 : limit 5,5
   第三页 : limit 10,5
   ......
   第N页 : limit (pageNo-1)*pageSzie,pageSzie
   [pageNo:页码,pageSize:单页面显示条数]
   
*/

-- 每页显示5条数据
SELECT s.studentno,studentname,subjectname,StudentResult
FROM student s
INNER JOIN result r
ON r.studentno = s.studentno
INNER JOIN `subject` sub
ON r.subjectno = sub.subjectno
WHERE subjectname='数据库结构-1'
ORDER BY StudentResult DESC , studentno
LIMIT 0,5

-- 查询 JAVA第一学年 课程成绩前10名并且分数大于80的学生信息(学号,姓名,课程名,分数)
SELECT s.studentno,studentname,subjectname,StudentResult
FROM student s
INNER JOIN result r
ON r.studentno = s.studentno
INNER JOIN `subject` sub
ON r.subjectno = sub.subjectno
WHERE subjectname='JAVA第一学年'
ORDER BY StudentResult DESC
LIMIT 0,10

-- 无限往下刷 瀑布流

```

> 子查询

```mysql
/*============== 子查询 ================
什么是子查询?
   在查询语句中的WHERE条件子句中,又嵌套了另一个查询语句
   嵌套查询可由多个子查询组成,求解的方式是由里及外;
   子查询返回的结果一般都是集合,故而建议使用IN关键字;
*/

-- 查询 数据库结构-1 的所有考试结果(学号,科目编号,成绩),并且成绩降序排列
-- 方法一:使用连接查询
SELECT studentno,r.subjectno,StudentResult
FROM result r
INNER JOIN `subject` sub
ON r.`SubjectNo`=sub.`SubjectNo`
WHERE subjectname = '数据库结构-1'
ORDER BY studentresult DESC;

-- 方法二:使用子查询(执行顺序:由里及外)
SELECT studentno,subjectno,StudentResult
FROM result
WHERE subjectno=(
   SELECT subjectno FROM `subject`
   WHERE subjectname = '数据库结构-1'
)
ORDER BY studentresult DESC;

-- 查询课程为 高等数学-2 且分数不小于80分的学生的学号和姓名
-- 方法一:使用连接查询
SELECT s.studentno,studentname
FROM student s
INNER JOIN result r
ON s.`StudentNo` = r.`StudentNo`
INNER JOIN `subject` sub
ON sub.`SubjectNo` = r.`SubjectNo`
WHERE subjectname = '高等数学-2' AND StudentResult>=80

-- 方法二:使用连接查询+子查询
-- 分数不小于80分的学生的学号和姓名
SELECT r.studentno,studentname FROM student s
INNER JOIN result r ON s.`StudentNo`=r.`StudentNo`
WHERE StudentResult>=80

-- 在上面SQL基础上,添加需求:课程为 高等数学-2
SELECT r.studentno,studentname FROM student s
INNER JOIN result r ON s.`StudentNo`=r.`StudentNo`
WHERE StudentResult>=80 AND subjectno=(
   SELECT subjectno FROM `subject`
   WHERE subjectname = '高等数学-2'
)

-- 方法三:使用子查询
-- 分步写简单sql语句,然后将其嵌套起来
SELECT studentno,studentname FROM student WHERE studentno IN(
   SELECT studentno FROM result WHERE StudentResult>=80 AND subjectno=(
       SELECT subjectno FROM `subject` WHERE subjectname = '高等数学-2'
  )
)

/*
练习题目:
   查 C语言-1 的前5名学生的成绩信息(学号,姓名,分数)
   使用子查询,查询郭靖同学所在的年级名称
*/

```



# 5、 MySQL函数

## 5.1、常用函数

```mysql
 SELECT ABS(-8);  /*绝对值*/
 SELECT CEILING(9.4); /*向上取整*/
 SELECT FLOOR(9.4);   /*向下取整*/
 SELECT RAND();  /*随机数,返回一个0-1之间的随机数*/
 SELECT SIGN(0); /*符号函数: 负数返回-1,正数返回1,0返回0*/

```



```mysql
 SELECT CHAR_LENGTH('狂神说坚持就能成功'); /*返回字符串包含的字符数*/
 SELECT CONCAT('我','爱','程序');  /*合并字符串,参数可以有多个*/
 SELECT INSERT('我爱编程helloworld',1,2,'超级热爱');  /*替换字符串,从某个位置开始替换某个长度*/
 SELECT LOWER('KuangShen'); /*小写*/
 SELECT UPPER('KuangShen'); /*大写*/
 SELECT LEFT('hello,world',5);   /*从左边截取*/
 SELECT RIGHT('hello,world',5);  /*从右边截取*/
 SELECT REPLACE('狂神说坚持就能成功','坚持','努力');  /*替换字符串*/
 SELECT SUBSTR('狂神说坚持就能成功',4,6); /*截取字符串,开始和长度*/
 SELECT REVERSE('狂神说坚持就能成功'); /*反转
 
 -- 查询姓周的同学,改成邹
 SELECT REPLACE(studentname,'周','邹') AS 新名字
 FROM student WHERE studentname LIKE '周%';

```

```mysql
 SELECT CURRENT_DATE();   /*获取当前日期*/
 SELECT CURDATE();   /*获取当前日期*/
 SELECT NOW();   /*获取当前日期和时间*/
 SELECT LOCALTIME();   /*获取当前日期和时间*/
 SELECT SYSDATE();   /*获取当前日期和时间*/
 
 -- 获取年月日,时分秒
 SELECT YEAR(NOW());
 SELECT MONTH(NOW());
 SELECT DAY(NOW());
 SELECT HOUR(NOW());
 SELECT MINUTE(NOW());
 SELECT SECOND(NOW());

```

```mysql
 SELECT VERSION();  /*版本*/
 SELECT USER();     /*用户*/
```

## 5.2、聚合函数 

![image-20210914201541645](D:\Anew_home\java\MySQL\MySQLnote\Mysql.assets\image-20210914201541645.png)

```mysql
 -- 聚合函数
 /*COUNT:非空的*/
 SELECT COUNT(studentname) FROM student;
 SELECT COUNT(*) FROM student;
 SELECT COUNT(1) FROM student;  /*推荐*/
 
 -- 从含义上讲，count(1) 与 count(*) 都表示对全部数据行的查询。
 -- count(字段) 会统计该字段在表中出现的次数，忽略字段为null 的情况。即不统计字段为null 的记录。
 -- count(*) 包括了所有的列，相当于行数，在统计结果的时候，包含字段为null 的记录；
 -- count(1) 用1代表代码行，在统计结果的时候，包含字段为null 的记录 。
 /*
 很多人认为count(1)执行的效率会比count(*)高，原因是count(*)会存在全表扫描，而count(1)可以针对一个字段进行查询。其实不然，count(1)和count(*)都会对全表进行扫描，统计所有记录的条数，包括那些为null的记录，因此，它们的效率可以说是相差无几。而count(字段)则与前两者不同，它会统计该字段不为null的记录条数。
 
 下面它们之间的一些对比：
 
 1）在表没有主键时，count(1)比count(*)快
 2）有主键时，主键作为计算条件，count(主键)效率最高；
 3）若表格只有一个字段，则count(*)效率较高。
 */
 
 SELECT SUM(StudentResult) AS 总和 FROM result;
 SELECT AVG(StudentResult) AS 平均分 FROM result;
 SELECT MAX(StudentResult) AS 最高分 FROM result;
 SELECT MIN(StudentResult) AS 最低分 FROM result;

```

## 5.3、MD5加密

> md5简介：
>
> MD5即Message-Digest Algorithm 5（信息-摘要算法5），用于确保信息传输完整一致。是计算机广泛使用的杂凑算法之一（又译摘要算法、哈希算法），主流编程语言普遍已有MD5实现。将数据（如汉字）运算为另一固定长度值，是杂凑算法的基础原理，MD5的前身有MD2、MD3和MD4。

新建一个表 testmd5

```mysql
 CREATE TABLE `testmd5` (
  `id` INT(4) NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  `pwd` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
 ) ENGINE=INNODB DEFAULT CHARSET=utf8

```

插入一些数据

```mysql
 INSERT INTO testmd5 VALUES(1,'kuangshen','123456'),(2,'qinjiang','456789')
```

如果我们要对pwd这一列数据进行加密，语法是：

```mysql
 update testmd5 set pwd = md5(pwd);
```

如果单独对某个用户(如kuangshen)的密码加密：

```mysql
 INSERT INTO testmd5 VALUES(3,'kuangshen2','123456')
 update testmd5 set pwd = md5(pwd) where name = 'kuangshen2';
```

插入新的数据自动加密

```mysql
 INSERT INTO testmd5 VALUES(4,'kuangshen3',md5('123456'));
```

查询登录用户信息（md5对比使用，查看用户输入加密后的密码进行比对）

```mysql
 SELECT * FROM testmd5 WHERE `name`='kuangshen' AND pwd=MD5('123456');
```



# 6、事务和索引

## 6.1、事务

> 什么是事务

- 事务就是将一组SQL语句放在同一批次内去执行
- 如果一个SQL语句出错,则该批次内的所有SQL都将被取消执行
- MySQL事务处理只支持InnoDB和BDB数据表类型

> 事务的ACID原则 百度 ACID <https://blog.csdn.net/dengjili/article/details/82468576>

**原子性(Atomic)**

- 整个事务中的所有操作，要么全部完成，要么全部不完成，不可能停滞在中间某个环节。事务在执行过程中发生错误，会被回滚（ROLLBACK）到事务开始前的状态，就像这个事务从来没有执行过一样。

**一致性(Consist)**

* 一个事务可以封装状态改变（除非它是一个只读的）。事务必须始终保持系统处于一致的状态，不管在任何给定的时间并发事务有多少。也就是说：如果事务是并发多个，系统也必须如同串行事务一样操作。其主要特征是保护性和不变性(Preserving an Invariant)，以转账案例为例，假设有五个账户，每个账户余额是100元，那么五个账户总额是500元，如果在这个5个账户之间同时发生多个转账，无论并发多少个，比如在A与B账户之间转账5元，在C与D账户之间转账10元，在B与E之间转账15元，五个账户总额也应该还是500元，这就是保护性和不变性。

**隔离性(Isolated)**

* 隔离状态执行事务，使它们好像是系统在给定时间内执行的唯一操作。如果有两个事务，运行在相同的时间内，执行相同的功能，事务的隔离性将确保每一事务在系统中认为只有该事务在使用系统。这种属性有时称为串行化，为了防止事务操作间的混淆，必须串行化或序列化请求，使得在同一时间仅有一个请求用于同一数据。

**持久性(Durable)**

* 在事务完成以后，该事务对数据库所作的更改便持久的保存在数据库之中，并不会被回滚。

> 基本语法

```mysql
-- 使用set语句来改变自动提交模式
SET autocommit = 0;   /*关闭*/
SET autocommit = 1;   /*开启*/

-- 注意:
--- 1.MySQL中默认是自动提交
--- 2.使用事务时应先关闭自动提交

-- 开始一个事务,标记事务的起始点
START TRANSACTION  

-- 提交一个事务给数据库
COMMIT

-- 将事务回滚,数据回到本次事务的初始状态
ROLLBACK

-- 还原MySQL数据库的自动提交
SET autocommit =1;

-- 保存点
SAVEPOINT 保存点名称 -- 设置一个事务保存点
ROLLBACK TO SAVEPOINT 保存点名称 -- 回滚到保存点
RELEASE SAVEPOINT 保存点名称 -- 删除保存点

```

> 测试

```mysql
/*
课堂测试题目

A在线买一款价格为500元商品,网上银行转账.
A的银行卡余额为2000,然后给商家B支付500.
商家B一开始的银行卡余额为10000

创建数据库shop和创建表account并插入2条数据
*/

CREATE DATABASE `shop`CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `shop`;

CREATE TABLE `account` (
`id` INT(11) NOT NULL AUTO_INCREMENT,
`name` VARCHAR(32) NOT NULL,
`cash` DECIMAL(9,2) NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8

INSERT INTO account (`name`,`cash`)
VALUES('A',2000.00),('B',10000.00)

-- 转账实现
SET autocommit = 0; -- 关闭自动提交
START TRANSACTION;  -- 开始一个事务,标记事务的起始点
UPDATE account SET cash=cash-500 WHERE `name`='A';
UPDATE account SET cash=cash+500 WHERE `name`='B';
COMMIT; -- 提交事务
# rollback;
SET autocommit = 1; -- 恢复自动提交

```

## 6.2、索引

> 索引的作用

- 提高查询速度
- 确保数据的唯一性
- 可以加速表和表之间的连接 , 实现表与表之间的参照完整性
- 使用分组和排序子句进行数据检索时 , 可以显著减少分组和排序的时间
- 全文检索字段进行搜索优化.

> 分类

- 主键索引 (Primary Key)
- 唯一索引 (Unique Key)
- 常规索引 (Index/ KeY)
- 全文索引 (FullText)

> 主键索引

主键 : 某一个属性组能唯一标识一条记录

特点 :

- 最常见的索引类型
- 确保数据记录的唯一性
- 确定特定数据记录在数据库中的位置

> 唯一索引

作用 : 避免同一个表中某数据列中的值重复

与主键索引的区别

- 主键索引只能有一个
- 唯一索引可能有多个

```mysql
CREATE TABLE `Grade`(
  `GradeID` INT(11) AUTO_INCREMENT PRIMARY KEY,
  `GradeName` VARCHAR(32) NOT NULL UNIQUE
   -- 或 UNIQUE KEY `GradeID` (`GradeID`)
)
```

> 常规索引

作用 : 快速定位特定数据

注意 :

- index 和 key 关键字都可以设置常规索引
- 应加在查询找条件的字段
- 不宜添加太多常规索引,影响数据的插入,删除和修改操作

```mysql
CREATE TABLE `result`(
   -- 省略一些代码
  INDEX/KEY `ind` (`studentNo`,`subjectNo`) -- 创建表时添加
)
-- 创建后添加
ALTER TABLE `result` ADD INDEX `ind`(`studentNo`,`subjectNo`);
```

> 全文索引

百度搜索：全文索引

作用 : 快速定位特定数据

注意 :

- 只能用于MyISAM类型的数据表
- 只能用于CHAR , VARCHAR , TEXT数据列类型
- 适合大型数据集

```mysql
/*
#方法一：创建表时
  　　CREATE TABLE 表名 (
               字段名1 数据类型 [完整性约束条件…],
               字段名2 数据类型 [完整性约束条件…],
               [UNIQUE | FULLTEXT | SPATIAL ]   INDEX | KEY
               [索引名] (字段名[(长度)] [ASC |DESC])
               );


#方法二：CREATE在已存在的表上创建索引
       CREATE [UNIQUE | FULLTEXT | SPATIAL ] INDEX 索引名
                    ON 表名 (字段名[(长度)] [ASC |DESC]) ;


#方法三：ALTER TABLE在已存在的表上创建索引
       ALTER TABLE 表名 ADD [UNIQUE | FULLTEXT | SPATIAL ] INDEX
                            索引名 (字段名[(长度)] [ASC |DESC]) ;
                           
                           
#删除索引：DROP INDEX 索引名 ON 表名字;
#删除主键索引: ALTER TABLE 表名 DROP PRIMARY KEY;


#显示索引信息: SHOW INDEX FROM student;
*/

/*增加全文索引*/
ALTER TABLE `school`.`student` ADD FULLTEXT INDEX `studentname` (`StudentName`);

/*EXPLAIN : 分析SQL语句执行性能*/
EXPLAIN SELECT * FROM student WHERE studentno='1000';

/*使用全文索引*/
-- 全文搜索通过 MATCH() 函数完成。
-- 搜索字符串作为 against() 的参数被给定。搜索以忽略字母大小写的方式执行。对于表中的每个记录行，MATCH() 返回一个相关性值。即，在搜索字符串与记录行在 MATCH() 列表中指定的列的文本之间的相似性尺度。
EXPLAIN SELECT *FROM student WHERE MATCH(studentname) AGAINST('love');

/*
开始之前，先说一下全文索引的版本、存储引擎、数据类型的支持情况

MySQL 5.6 以前的版本，只有 MyISAM 存储引擎支持全文索引；
MySQL 5.6 及以后的版本，MyISAM 和 InnoDB 存储引擎均支持全文索引;
只有字段的数据类型为 char、varchar、text 及其系列才可以建全文索引。
测试或使用全文索引时，要先看一下自己的 MySQL 版本、存储引擎和数据类型是否支持全文索引。
*/
```

> 拓展：测试索引

**建表app_user：**

```mysql
CREATE TABLE `app_user` (
`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
`name` varchar(50) DEFAULT '' COMMENT '用户昵称',
`email` varchar(50) NOT NULL COMMENT '用户邮箱',
`phone` varchar(20) DEFAULT '' COMMENT '手机号',
`gender` tinyint(4) unsigned DEFAULT '0' COMMENT '性别（0:男；1：女）',
`password` varchar(100) NOT NULL COMMENT '密码',
`age` tinyint(4) DEFAULT '0' COMMENT '年龄',
`create_time` datetime DEFAULT CURRENT_TIMESTAMP,
`update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATECURRENT_TIMESTAMP,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='app用户表'
```

**批量插入数据：100w**

```mysql
DROP FUNCTION IF EXISTS mock_data;
DELIMITER $$
CREATE FUNCTION mock_data()
RETURNS INT
BEGIN
DECLARE num INT DEFAULT 1000000;
DECLARE i INT DEFAULT 0;
WHILE i < num DO
  INSERT INTO app_user(`name`, `email`, `phone`, `gender`, `password`, `age`)
   VALUES(CONCAT('用户', i), '24736743@qq.com', CONCAT('18', FLOOR(RAND()*(999999999-100000000)+100000000)),FLOOR(RAND()*2),UUID(), FLOOR(RAND()*100));
  SET i = i + 1;
END WHILE;
RETURN i;
END;
SELECT mock_data();
```

**索引效率测试**

无索引

```mysql
SELECT * FROM app_user WHERE name = '用户9999'; -- 查看耗时
SELECT * FROM app_user WHERE name = '用户9999';
SELECT * FROM app_user WHERE name = '用户9999';

mysql> EXPLAIN SELECT * FROM app_user WHERE name = '用户9999'\G
*************************** 1. row ***************************
          id: 1
select_type: SIMPLE
       table: app_user
  partitions: NULL
        type: ALL
possible_keys: NULL
        key: NULL
    key_len: NULL
        ref: NULL
        rows: 992759
    filtered: 10.00
      Extra: Using where
1 row in set, 1 warning (0.00 sec)
```

> 创建索引

````mysql
CREATE INDEX idx_app_user_name ON app_user(name);
````

> 测试普通索引

```mysql
mysql> EXPLAIN SELECT * FROM app_user WHERE name = '用户9999'\G
*************************** 1. row ***************************
          id: 1
select_type: SIMPLE
       table: app_user
  partitions: NULL
        type: ref
possible_keys: idx_app_user_name
        key: idx_app_user_name
    key_len: 203
        ref: const
        rows: 1
    filtered: 100.00
      Extra: NULL
1 row in set, 1 warning (0.00 sec)

mysql> SELECT * FROM app_user WHERE name = '用户9999';
1 row in set (0.00 sec)

mysql> SELECT * FROM app_user WHERE name = '用户9999';
1 row in set (0.00 sec)

mysql> SELECT * FROM app_user WHERE name = '用户9999';
1 row in set (0.00 sec)
```

> 索引准则

- 索引不是越多越好
- 不要对经常变动的数据加索引
- 小数据量的表建议不要加索引
- 索引一般应加在查找条件的字段

> 索引的数据结构

```mysql
-- 我们可以在创建上述索引的时候，为其指定索引类型，分两类
hash类型的索引：查询单条快，范围查询慢
btree类型的索引：b+树，层数越多，数据量指数级增长（我们就用它，因为innodb默认支持它）

-- 不同的存储引擎支持的索引类型也不一样
InnoDB 支持事务，支持行级别锁定，支持 B-tree、Full-text 等索引，不支持 Hash 索引；
MyISAM 不支持事务，支持表级别锁定，支持 B-tree、Full-text 等索引，不支持 Hash 索引；
Memory 不支持事务，支持表级别锁定，支持 B-tree、Hash 等索引，不支持 Full-text 索引；
NDB 支持事务，支持行级别锁定，支持 Hash 索引，不支持 B-tree、Full-text 等索引；
Archive 不支持事务，支持表级别锁定，不支持 B-tree、Hash、Full-text 等索引；

```

[MySQL索引背后的数据结构及算法原理](http://blog.codinglabs.org/articles/theory-of-mysql-index.html)

# 7、权限管理和备份

## 7.1、用户管理

> 基本命令

```mysql
/* 用户和权限管理 */ ------------------
用户信息表：mysql.user

-- 刷新权限
FLUSH PRIVILEGES

-- 增加用户 CREATE USER kuangshen IDENTIFIED BY '123456'
CREATE USER 用户名 IDENTIFIED BY [PASSWORD] 密码(字符串)
  - 必须拥有mysql数据库的全局CREATE USER权限，或拥有INSERT权限。
  - 只能创建用户，不能赋予权限。
  - 用户名，注意引号：如 'user_name'@'192.168.1.1'
  - 密码也需引号，纯数字密码也要加引号
  - 要在纯文本中指定密码，需忽略PASSWORD关键词。要把密码指定为由PASSWORD()函数返回的混编值，需包含关键字PASSWORD

-- 重命名用户 RENAME USER kuangshen TO kuangshen2
RENAME USER old_user TO new_user

-- 设置密码
SET PASSWORD = PASSWORD('密码')    -- 为当前用户设置密码
SET PASSWORD FOR 用户名 = PASSWORD('密码')    -- 为指定用户设置密码

-- 删除用户 DROP USER kuangshen2
DROP USER 用户名

-- 分配权限/添加用户
GRANT 权限列表 ON 表名 TO 用户名 [IDENTIFIED BY [PASSWORD] 'password']
  - all privileges 表示所有权限(除了grant权限)
  - *.* 表示所有库的所有表
  - 库名.表名 表示某库下面的某表

-- 查看权限   SHOW GRANTS FOR root@localhost;
SHOW GRANTS FOR 用户名
   -- 查看当前用户权限
  SHOW GRANTS; 或 SHOW GRANTS FOR CURRENT_USER; 或 SHOW GRANTS FOR CURRENT_USER();

-- 撤消权限
REVOKE 权限列表 ON 表名 FROM 用户名
REVOKE ALL PRIVILEGES, GRANT OPTION FROM 用户名    -- 撤销所有权限

```

## 7.2、数据库备份

数据库备份必要性

- 保证重要数据不丢失
- 数据转移

MySQL数据库备份方法

- mysqldump备份工具
- 数据库管理工具,如SQLyog
- 直接拷贝数据库文件和相关配置文件

**mysqldump客户端**

作用 :

- 转储数据库
- 搜集数据库进行备份
- 将数据转移到另一个SQL服务器,不一定是MySQL服务器

> 命令行导出

```mysql
C:\Windows\system32>mysqldump -hlocalhost -uroot -p123456 school student >D:/a.sql
mysqldump: [Warning] Using a password on the command line interface can be insecure.
-- 导出
1. 导出一张表 -- mysqldump -uroot -p123456 school student >D:/a.sql
　　mysqldump -u用户名 -p密码 库名 表名 > 文件名(D:/a.sql)
2. 导出多张表 -- mysqldump -uroot -p123456 school student result >D:/a.sql
　　mysqldump -u用户名 -p密码 库名 表1 表2 表3 > 文件名(D:/a.sql)
3. 导出所有表 -- mysqldump -uroot -p123456 school >D:/a.sql
　　mysqldump -u用户名 -p密码 库名 > 文件名(D:/a.sql)
4. 导出一个库 -- mysqldump -uroot -p123456 -B school >D:/a.sql
　　mysqldump -u用户名 -p密码 -B 库名 > 文件名(D:/a.sql)

可以-w携带备份条件

-- 导入
1. 在登录mysql的情况下：-- source D:/a.sql
　　source 备份文件
2. 在不登录的情况下
　　mysql -u用户名 -p密码 库名 < 备份文件

```

# 8、数据库的规范设计

##  8.1、为什么需要数据库设计



**当数据库比较复杂**

**时我们需要设计数据库**

**糟糕的数据库设计 :**

- 数据冗余,存储空间浪费
- 数据更新和插入的异常
- 程序性能差

**良好的数据库设计 :**

- 节省数据的存储空间
- 能够保证数据的完整性
- 方便进行数据库应用系统的开发

**软件项目开发周期中数据库设计 :**

- 需求分析阶段: 分析客户的业务和数据处理需求
- 概要设计阶段:设计数据库的E-R模型图 , 确认需求信息的正确和完整.

**设计数据库步骤:(个人博客)**

* 收集信息，分析需求
  * 用户表（用户登录注销，用户的个人信息，写博客，创建分类）
  * 分类表（文章分类，谁创建的）
  * 文章表（文章的信息）
  * 友链表（友链信息）
  * 自定义表（系统信息，某个关键的字，或者一些主字段）key： value

* * 与该系统有关人员进行交流 , 座谈 , 充分了解用户需求 , 理解数据库需要完成的任务

* 标识实体[Entity]

* * 标识数据库要管理的关键对象或实体,实体一般是名词
* 标识每个实体需要存储的详细信息[Attribute]

* 标识实体之间的关系[Relationship]
  * 写博客：user --> blog
  * 创建分类： user --> category
  * 关注：user --> user
  * 友链：link
  * 评论：user --> user --> blog

## 8.2、三大范式

**问题 : 为什么需要数据规范化?**

不合规范的表设计会导致的问题：

- 信息重复
- 更新异常
- 插入异常
- - 无法正确表示信息
- 删除异常
- - 丢失有效信息

> 三大范式

**第一范式 (1st NF)**

第一范式的目标是确保每列的原子性(不可再分),如果每列都是不可再分的最小数据单元,则满足第一范式

**第二范式(2nd NF)**

第二范式（2NF）是在第一范式（1NF）的基础上建立起来的，即满足第二范式（2NF）必须先满足第一范式（1NF）。

第二范式要求每个表只描述一件事情。

**第三范式(3rd NF)**

如果一个关系满足第二范式,并且除了主键以外的其他列都不传递依赖于主键列,则满足第三范式.

第三范式需要确保数据表中的每一列数据都和主键直接相关，而不能间接相关。

**规范化和性能的关系**

为满足某种商业目标 , 数据库性能比规范化数据库更重要

在数据规范化的同时 , 要综合考虑数据库的性能

通过在给定的表中添加额外的字段,以大量减少需要从中搜索信息所需的时间

```mysql
### 三大范式

**问题 : 为什么需要数据规范化?**

不合规范的表设计会导致的问题：

- 信息重复

- 更新异常

- 插入异常

- - 无法正确表示信息

- 删除异常

- - 丢失有效信息

> 三大范式

**第一范式 (1st NF)**

第一范式的目标是确保每列的原子性,如果每列都是不可再分的最小数据单元,则满足第一范式

**第二范式(2nd NF)**

第二范式（2NF）是在第一范式（1NF）的基础上建立起来的，即满足第二范式（2NF）必须先满足第一范式（1NF）。

第二范式要求每个表只描述一件事情

**第三范式(3rd NF)**

如果一个关系满足第二范式,并且除了主键以外的其他列都不传递依赖于主键列,则满足第三范式.

第三范式需要确保数据表中的每一列数据都和主键直接相关，而不能间接相关。



**规范化和性能的关系**

为满足某种商业目标 , 数据库性能比规范化数据库更重要

在数据规范化的同时 , 要综合考虑数据库的性能

通过在给定的表中添加额外的字段,以大量减少需要从中搜索信息所需的时间

通过在给定的表中插入计算列,以方便查询

```

# 9、JDBC

## 9.1、数据库驱动

驱动：

![image-20210916115812421](D:\Anew_home\java\MySQL\MySQLnote\Mysql.assets\image-20210916115812421.png)

## 9,2、JDBC

SUN公司为了简化开发人员（对数据库的统一）操作，提供了一个（java操作数据库的）规范，俗称JDBC，这些规范的实现是由具体的厂商去做
对开发人员来说，我们只需要掌握JDBC接口的操作即可。

![image-20210916145735187](D:\Anew_home\java\MySQL\MySQLnote\Mysql.assets\image-20210916145735187.png)

java.sql
javax.sql
还需要导入一个数据库驱动包mysql-connector-java-5.1.47.jar

## 9.3、我的第一个JDBC程序

![image-20210916155647063](D:\Anew_home\java\MySQL\MySQLnote\Mysql.assets\image-20210916155647063.png)

> 创建测试数据库

```mysql
CREATE DATABASE jdbcStudy CHARACTER SET utf8 COLLATE utf8_general_ci;

USE jdbcStudy;

CREATE TABLE `users`(
id INT PRIMARY KEY,
NAME VARCHAR(40),
PASSWORD VARCHAR(40),
email VARCHAR(60),
birthday DATE
);

INSERT INTO `users`(id,NAME,PASSWORD,email,birthday)
VALUES(1,'zhansan','123456','zs@sina.com','1980-12-04'),
(2,'lisi','123456','lisi@sina.com','1981-12-04'),
(3,'wangwu','123456','wangwu@sina.com','1979-12-04')
```

1. 创建一个普通项目
2. 导入数据库驱动
3. 编写测试代码

```java
package com.kuang.lesson01;
//我的第一个JDBC程序

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcFirstDemo {
    public static void main(String[] args) throws Exception {
        //1. 加载驱动
        Class.forName("com.mysql.jdbc.Driver");//固定写法
        //2. 用户信息和url
        //useUnicode=true&characterEncoding=utf8&&useSSL=true
        String url ="jdbc:mysql://localhost:3306/jdbcstudy?useUnicode=true&characterEncoding=utf8&&useSSL=false";
        String name = "root";
        String password = "123456";

        //3. 连接成功，返回数据库对象  connection代表数据库
        Connection connection= DriverManager.getConnection(url,name,password);
        //4. 执行SQL的对象 statement 执行SQL的对象
        Statement statement = connection.createStatement();

        //5. 执行SQL的对象 去执行SQL   可能存在结果，查看返回结果
        String sql="SELECT * FROM users";
        ResultSet resultSet = statement.executeQuery(sql);//返回的结果集,结果集中封装了我们全部查询的结果
        while(resultSet.next()){
            System.out.println("id+"+resultSet.getObject("id"));
            System.out.println("name+"+resultSet.getObject("NAME"));
            System.out.println("password+"+resultSet.getObject("PASSWORD"));
            System.out.println("email+"+resultSet.getObject("email"));
            System.out.println("birthday+"+resultSet.getObject("birthday"));
        }
        //6. 释放连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}
```

步骤总结：
1.加载驱动

2.连接数据库 DriverManager

3.获取执行SQL的对象 Statement

4.获得返回的结果集

5.释放连接

> DriverManager

```java
//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
Class.forName("com.mysql.jdbc.Driver");//固定写法
Connection connection= DriverManager.getConnection(url,name,password);

//connection代表数据库
//数据库设置自动提交
//事务提交
//事务回滚
connection.rollback();
connection.commit();
connection.setAutoCommit();
```

> URL

```java
String url ="jdbc:mysql://localhost:3306/jdbcstudy?useUnicode=true&characterEncoding=utf8&&useSSL=false";

//mysql 默认3306
//协议://主机地址:端口号/数据库名？参数1&参数2&参数3

//Oracle   1521
//jdbc:oralce:thin:@localhost:1521:sid
```

> statement 执行SQL的对象 pPrepareStatement 执行SQL的对象

```java
String sql="SELECT * FROM users";//编写Sql

statement.executeQuery();
statement.execute();
statement.executeUpdate();//更新，插入，删除，返回一个受影响的行数
```

> ResultSet 查询的结果集，封装了所以的查询结果

```java
ResultSet resultSet = statement.executeQuery(sql);//返回的结果集,结果集中封装了我们全部查询的结果
        resultSet.getObject();//在不知道列类型下使用
        resultSet.getString();//如果知道则指定使用
        resultSet.getInt();
```

> 遍历，指针

```java
        resultSet.next(); //移动到下一个
        resultSet.afterLast();//移动到最后
        resultSet.beforeFirst();//移动到最前面
        resultSet.previous();//移动到前一行
        resultSet.absolute(row);//移动到指定行
```

> 释放内存

```java
//6. 释放连接
        resultSet.close();
        statement.close();
        connection.close();//耗资源
```

## 9.4、statement对象

**Jdbc中的statement对象用于向数据库发送SQL语句，想完成对数据库的增删改查，只需要通过这个对象向数据库发送增删改查语句即可。**

Statement对象的executeUpdate方法，用于向数据库发送增、删、改的sq|语句， executeUpdate执行完后， 将会返回一个整数(即增删改语句导致了数据库几行数据发生了变化)。

Statement.executeQuery方法用于向数据库发生查询语句，executeQuery方法返回代表查询结果的ResultSet对象。

>CRUD操作-create

使用executeUpdate(String sql)方法完成数据添加操作，示例操作：

```java
 Statement statement = connection.createStatement();
        String sql = "insert into user(...) values(...)";
        int num = statement.executeUpdate(sql);
        if(num>0){
            System.out.println("插入成功");
        
```

> CRUD操作-delete

使用executeUpdate(String sql)方法完成数据删除操作，示例操作：

```java
Statement statement = connection.createStatement();
        String sql = "delete from user where id =1";
        int num = statement.executeUpdate(sql);
        if(num>0){
            System.out.println("删除成功");
        }
```

>CURD操作-update

使用executeUpdate(String sql)方法完成数据修改操作，示例操作：

```java
Statement statement = connection.createStatement();
        String sql = "update user set name ='' where name = ''";
        int num = statement.executeUpdate(sql);
        if(num>0){
            System.out.println("修改成功");
        }
```

> CURD操作-read

使用executeUpdate(String sql)方法完成数据查询操作，示例操作：

```java
Statement statement = connection.createStatement();
        String sql = "select * from  user where id =1";
        ResultSet rs= statement.executeQuery(sql);
        if(rs.next()){
            System.out.println("");
        }
```

> 代码实现

1.提取工具类

先创建一个dp.properties

![image-20210916200714899](D:\Anew_home\java\MySQL\MySQLnote\Mysql.assets\image-20210916200714899.png)

```java
package com.kuang.lesson02.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils {
    private static String driver = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;
    static {
        try{
            InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(in);
            driver=properties.getProperty("driver");
            url=properties.getProperty("url");
            username=properties.getProperty("username");
            password=properties.getProperty("password");

            //1.驱动只用加载一次
            Class.forName(driver);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    //2.获取连接
    public static Connection getConnection() throws Exception{
        return DriverManager.getConnection(url, username, password);
    }
    //3.释放资源
    public static void release(Connection conn, Statement st, ResultSet rs) throws SQLException {

        if(rs!=null){
            rs.close();
        }
        if (st!=null){
            st.close();
        }
        if(conn!=null){
            conn.close();
        }

    }
}
```

2.编写增删改的方法，`exectueUpdate`

```java
package com.kuang.lesson02.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static com.kuang.lesson02.utils.JdbcUtils.*;

public class TestInnsert {
    public static void main(String[] args){
        Connection conn =null;
        Statement st = null;
        ResultSet rs =null;
        try {
             conn = getConnection();//获取连接
            st = conn.createStatement();//获取SQL执行对象
            String sql = "INSERT INTO users(id,`NAME`,`PASSWORD`,`email`,`birthday`)" +
                    "VALUES(5,'sanjin','123456','233223@qq.com','2020-01-01')";

            int i = st.executeUpdate(sql);
            if(i>0){
                System.out.println("插入成功");
            }
        JdbcUtils.release(conn,st,rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
```

3.查询 `executeQuery`

```java
package com.kuang.lesson02.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.kuang.lesson02.utils.JdbcUtils.*;

public class TestInnsert {
    public static void main(String[] args) throws SQLException {
        Connection conn =null;
        Statement st = null;
        ResultSet rs =null;



        try {
             conn = getConnection();//获取连接
            st = conn.createStatement();//获取SQL执行对象
            String sql = "select * from users";
            rs=st.executeQuery(sql);//查询完毕返回结果集

            while (rs.next()){
                System.out.println(rs.getString("NAME"));
            }
        JdbcUtils.release(conn,st,rs);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
    }

}
```

> SQL注入问题

sql存在漏洞，会被攻击导致数据泄露 **SQL会被拼接 or**

```java
package com.kuang.lesson02.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static com.kuang.lesson02.utils.JdbcUtils.getConnection;
public class SQL注入 {
    public static void main(String[] args) {
        //SQL注入
 login("' or '1=1","123456");
    }
    public static void login(String name,String password){
        Connection conn =null;
        Statement st = null;
        ResultSet rs =null;
        try {
            conn = getConnection();//获取连接
            st = conn.createStatement();//获取SQL执行对象
            String sql = "select * from users where `NAME`='"+ name +"'  AND `PASSWORD`='"+ password +"'" ;
            rs=st.executeQuery(sql);//查询完毕返回结果集

            while (rs.next()){
                System.out.println(rs.getString("NAME"));
            }
            JdbcUtils.release(conn,st,rs);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                JdbcUtils.release(conn,st,rs);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
```

## 9.5、PreparedStatement对象

PreparedStatement 可以防止SQL注入 ，效率更高，==**先预编译后传参，所以能防止SQL注入**==

1. 新增
2. 删除
3. 查询

```java
package com.kuang.lesson03;

import com.kuang.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
        Connection connection= null;
        PreparedStatement pstm=null;
        try {

            connection = JdbcUtils.getConnection();
            //区别
            //使用问好占位符代替参数
            String sql = "insert into users(id,`NAME`) values(?,?)";
            pstm = connection.prepareStatement(sql);//预编译sql，先写sql然后不执行
            //手动赋值
            pstm.setInt(1,8);
            pstm.setString(2,"SANJIN");

            //执行
            int i = pstm.executeUpdate();
            if (i>0){
                System.out.println("插入成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                JdbcUtils.release(connection,pstm,null);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
//防止SQL注入本质，传递字符 带有“ ”，转义字符会被转义
```

## 9.6、事务

**要么都成功，要么都失败**

> ACID原则

原子性：要么全部完成，要么都不完成

一致性：结果总数不变

隔离性：**多个进程互不干扰**

持久性：一旦提交不可逆，持久化到数据库了

隔离性的问题：

脏读： 一个事务读取了另一个没有提交的事务

不可重复读：在同一个事务内，重复读取表中的数据，表发生了改变(强调的是单独数据的修改)

虚读（幻读）：在一个事务内，读取到了别人插入的数据，导致前后读出来的结果不一致（强调的是数据集合的增减）

[隔离性问题讲解](https://www.cnblogs.com/yubaolee/p/10398633.html)

> 代码实现

1. 开启事务`conn.setAutoCommit(false);`
2. 一组业务执行完毕，提交事务
3. 可以在catch语句中显示的定义回滚，但是默认失败会回滚

```java
package com.kuang.lesson04;

import com.kuang.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Action {
    public static void main(String[] args) {

        Connection conn =null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);//关闭数据库的自动提交功能， 自动会开启事务
            //自动开启事务
            String sql = "update account set money = money-500 where id = 1";
            ps =conn.prepareStatement(sql);
            ps.executeUpdate();
            String sql2 = "update account set money = money-500 where id = 2";
            ps=conn.prepareStatement(sql2);
            ps.executeUpdate();

            //业务完毕，提交事务
            conn.commit();
            System.out.println("操作成功");
        } catch (Exception e) {
            try {
                //如果失败，则默认回滚
                conn.rollback();//如果失败，回滚
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            try {
                JdbcUtils.release(conn,ps,rs);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
```

 ## 9.7、数据库连接池





数据库连接–执行完毕–释放
连接–释放 十分浪费资源
**池化技术**： 准备一些预先的资源，过来就连接预先准备好的

ori： 开门。。。服务。。。关门

连接池： 开门。。。业务员。。。等待。。。服务。。。

常用连接数 100

最少连接数：100

最大连接数 ： 120 业务最高承载上限

排队等待，

等待超时：100ms

编写连接池，实现一个接口 DateSource

> 开源数据源实现(拿来即用)

DBCP

C3P0

Druid: 阿里巴巴

使用了这些数据库连接池之后，我们在项目开发中就不需要编写连接数据库的代码了

> DBCP

需要用到的jar包

dbcp.jar

> 结论

无论使用什么数据源，本质是不变的，DateSource接口不会变，方法就不会变

```properties
#连接设置
driverClassName=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/jdbcstudy?useUnicode=true&characterEncoding=utf8&useSSL=true
username=root
password=123456

#!-- 初始化连接 --
initialSize=10

#最大连接数量
maxActive=50

#!-- 最大空闲连接 --
maxIdle=20

#!-- 最小空闲连接 --
minIdle=5

#!-- 超时等待时间以毫秒为单位 6000毫秒/1000等于60秒 --
maxWait=60000
#JDBC驱动建立连接时附带的连接属性属性的格式必须为这样：【属性名=property;】
#注意：user 与 password 两个属性会被明确地传递，因此这里不需要包含他们。
connectionProperties=useUnicode=true;characterEncoding=UTF8

#指定由连接池所创建的连接的自动提交（auto-commit）状态。
defaultAutoCommit=true

#driver default 指定由连接池所创建的连接的只读（read-only）状态。
#如果没有设置该值，则“setReadOnly”方法将不被调用。（某些驱动并不支持只读模式，如：Informix）
defaultReadOnly=

#driver default 指定由连接池所创建的连接的事务级别（TransactionIsolation）。
#可用值为下列之一：（详情可见javadoc。）NONE,READ_UNCOMMITTED, READ_COMMITTED, REPEATABLE_READ, SERIALIZABLE
defaultTransactionIsolation=READ_UNCOMMITTED
```

数据库连接 -- 执行完毕-- 释放

连接-- 释放 十分浪费系统资源

