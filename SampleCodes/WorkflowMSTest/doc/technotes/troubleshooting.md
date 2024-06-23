# Network
## check which port does the application open
1. find the process id
`ps aux|grep java`
2. find the port
`netstat -anv | grep <port-number>`

## enable debug on ec2
* open a tcp port is not necessary
* start remote jvm using the following command
```shell
sudo java -Dserver.port=80 -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005  -jar WorkflowMSTest-1.0-SNAPSHOT.jar
```
* port forward on local using the following command
```shell
ssh -Nf -L 5005:localhost:5005 -i "test.pem" ec2-user@ec2-3-87-243-2.compute-1.amazonaws.com
```
* configure the intelliJ for the remote debug

# Mysql
## starting and stopping mysql on Mac
* sudo mysql.server start -- for start
* sudo mysql.server stop -- for stop
* sudo mysql.server restart -- for restart
## starting the mysql client
* mysql -u root
* create user 'test' identified by 'test';
* mysql> grant select on *.* to 'test';
* mysql> grant all privileges on *.* to 'test';
* create database WorkFlowTest
## Design of the database
| row_id | transaction_id | transaction_data | status | is_delete | error_msg |
| ------ | -------------- | ---------------- | ------ | --------- | ----------|
| primary|hash idx        |                  |btree|btree|

```
create table transaction (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    transaction_id varchar(36),
    transaction_data json,
    status TINYINT UNSIGNED DEFAULT 0,
    is_delete TINYINT(1) UNSIGNED DEFAULT 0,
    error_msg varchar(255),
    PRIMARY KEY(id)
);

create index idx_transId on transaction (transaction_id) using HASH;
create index idx_status on transaction (status) using BTREE;
create index idx_deleteFlag on transaction (is_delete) using BTREE;

show index from transaction ¥G;

insert into transactransaction (transaction_id, transaction_data) values (uuid(), "{\"test\":\"test\"}");
```
### status
| Status Name | status code | Next Status |
| ----------- | ----------- | ------------|
| received|0|success, error|
|success|1||
|error|2||

### is_delete
| Status Name | status code | Next Status |
| ----------- | ----------- | ------------|
| true|1|false|
|false|0|true|

### Datetime format pitfall
MySQL sever 本身运行环境有时区配置，now() 之类的内置函数，或者 dt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP 之类自动设置的时间依赖的是 MySQL server 本身的时区配置；

MySQL client 一般是应用程序，当通过对应语言的 mysql-driver 连接 MySQL 时，在写入、读取、范围查询时对于 DATETIME 字段实际发送过去的值是字符串类型的，比如 2021-12-04 14:20:12，也是没有时区信息的，应用程序往往会根据配置来解析成对应时区的时间；

所以使用 DATETIME 字段几乎就必须保证所有相关的 client 和 server 始终使用一个固定时区配置，只要有不同就可能会出现问题。

比如：

client 使用的时区和 server 使用的时区不一致，那么 sever 的 NOW() 或者 DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP 得到的值被 client 读到之后发现差了几个小时
client1 和 client2 同时使用一个 server，但是 client1、client2 的时区配置不一样，或者 client1 原本配置了时区 Asia/Shanghai 后来变成了 UTC，那么读写老数据的时候可能发现范围查询有问题、更新不成功（按时间大小做限定条件更新）、时间范围查询丢数据……等等的诡异问题

https://cliiip.com/posts/mysql-datetime-timezone-pitfall/

* Server timezone
```
show variables like "%time_zone%"
```

# EC2 MySQL Configuration
https://docs.aws.amazon.com/AmazonRDS/latest/UserGuide/CHAP_GettingStarted.CreatingConnecting.MySQL.html

To install mySQL client, using the following command in aws
```
sudo dnf install mariadb105
```
