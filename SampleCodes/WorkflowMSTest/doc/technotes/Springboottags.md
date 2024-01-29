# Controller RestController
* Controller要么搭配 ResponseBody 要么搭配 thymeleaf
* RestController 是 Controller 加 ResponseBody
* https://blog.csdn.net/u010412719/article/details/69710480
* https://endok.hatenablog.com/entry/2016/06/06/035849
## RequestMapping, GetMapping
* GetMapping = RequestMapping with RequestMethod=GET
## Service, Repoository, Controller, Component
* Repository -- rethrow platform specific exceptions to be caught by springboot
* Controller -- request mapping
* Service -- biz logic , nothing special
## ResponseEntity RequestBody
* Controller 的返回值 
* RequestBody json 转类
## Configuration Bean
* 相当于Spring的XNL配置文件
* 可以有多个，每个都是配置文件， bean是配置项目
## ComponentScan
* 控制bean搜索的路径
## Repository
* JPA 有自动命名规则
* https://www.cnblogs.com/h-c-g/p/10980469.html
* https://blog.csdn.net/ming19951224/article/details/106308544
* https://www.baeldung.com/spring-data-jpa-query
* https://zhuanlan.zhihu.com/p/140216064
* https://medium.com/geekculture/spring-transactional-rollback-handling-741fcad043c6
# AOP
* https://juejin.cn/post/6844903766035005453
# Kafka
## Install
* https://colobu.com/2019/09/27/install-Kafka-on-Mac/
  * install zookeeper and start zookeeper
  * 修改 /usr/local/etc/kafka/server.properties, 找到 listeners=PLAINTEXT://:9092 那一行，把注释取消掉。
    然后修改为:```listeners=PLAINTEXT://localhost:9092```
  * 启动
    * 启动kafka ```brew services start kafka```
    * 临时启动
```
$ zkServer start
$ kafka-server-start /usr/local/etc/kafka/server.properties
```
## 概念
* topic : 队列名称
* partition : 最多一个消费者消费一个partition， 提高并行度，一个消费者可以消费多个分区
  * 分区，为了实现扩展性，一个topic可以分布在多个broker上，一个topic可以分为多个partition，每个partition都是一个有序的队列。partition中的每条消息都会被分配一个有序的id（offset）。kafka只保证同一个partition中的消息顺序，不保证一个topic的整体（多个partition之间）的顺序。生产者和消费者使用时可以指定topic中的具体partition。 
  * 在消费者对指定消息分区进行消费的过程中，需要定时地将分区消息的消费进度Offset记录到Zookeeper上，以便对该消费者进行重启或者其他消费者重新接管该消息分区的消息消费后，能够从之前的进度继续进行消息消费。Offset在Zookeeper中由一个专门节点进行记录，其节点路径为：
 ```
/consumers/[group_id]/offsets/[topic]/[broker_id-partition_id]
节点内容就是Offset的值。
 ```
* Broker : Kafka 服务器
* 消费者组：一个Topic可以对应多个Consumer Group。如果需要实现广播，只要每个Consumer有一个独立的Group就可以了， Consumer Group （CG）：若干个Consumer组成的集合。这是kafka用来实现一个topic消息的广播（发给所有的consumer）和单播（发给任意一个consumer）的手段。一个topic可以有多个CG。topic的消息会复制（不是真的复制，是概念上的）到所有的CG，但每个CG只会把消息发给该CG中的一个consumer。如果需要实现广播，只要每个consumer有一个独立的CG就可以了。要实现单播只要所有的consumer在同一个CG。用CG还可以将consumer进行自由的分组而不需要多次发送消息到不同的topic。每个CG有自己的Offset
* 去重机制
  * 单独去重机制，例如生成消息时，在消息中加入唯一标识符如主键id。写入时根据逐渐主键判断update还是insert。如果写redis，则每次根据主键id进行set即可，天然幂等性。或者使用redis作为缓冲，将id首先写入redis进行重复判断，然后在进行后续操作。https://segmentfault.com/a/1190000041263079
### zookeeper
* ZooKeeper主要服务于分布式系统，可以用ZooKeeper来做：统一配置管理、统一命名服务、分布式锁、集群管理。
* ZooKeeper的数据结构，跟Unix文件系统非常类似，可以看做是一颗树，每个节点叫做ZNode。每一个节点可以通过路径来标识
* ZooKeeper还配合了监听器才能够做那么多事的
* ZooKeeper还配合了监听器才能够做那么多事的
* 统一命名服务的理解其实跟域名一样，是我们为这某一部分的资源给它取一个名字，别人通过这个名字就可以拿到对应的资源
* 分布式锁：拿到/locks节点下的所有子节点(id_000000,id_000001,id_000002)，判断自己创建的是不是最小的那个节点
* 集群状态：在ZooKeeper中创建临时节点即可各维护一个临时节点只要系统A挂了，那/groupMember/A这个节点就会删除，通过监听groupMember下的子节点，系统B和C就能够感知到系统A已经挂了
* https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247485115&idx=1&sn=5d269f40f820c82b460993669ca6242e&chksm=ebd747badca0ceac9953f82e08b1d1a49498ebd4af77ec5d628a0682bb9f0ac5ab347411f654&token=1741918942&lang=zh_CN#rd
#### Kafka and zookeeper
目前去除Zookeeper的Kafka代码KIP-500已经提交到trunk分支，并且计划在未来的2.8版本发布。
https://www.51cto.com/article/658581.html


## Kafka Environment Set up
* Create topic
```shell
$ kafka-topics --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic WorkflowTest

kuangyizhu@KuangyinoMacBook-Pro ~ % kafka-topics --list --bootstrap-server localhost:9092
WorkflowTest
__consumer_offsets
test
```
* Create consumer groups
```shell
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic WorkflowTest --group WorkflowTestGroup
kafka-consumer-groups  --list --bootstrap-server localhost:9092 # list
```
* show offsets
```shell
kafka-consumer-groups --bootstrap-server localhost:9092 --group WorkflowTestGroup --describe
```
* reset offset https://www.hadoopinrealworld.com/how-to-change-or-reset-consumer-offset-in-kafka/
* push message
```shell
kafka-console-producer --broker-list localhost:9092 --topic WorkflowTest
```
* consume message
```shell
kafka-console-consumer --bootstrap-server localhost:9092 --topic WorkflowTest --group WorkflowTestGroup --from-beginning
```
### Ack 机制
* ACK 是acknowledge 的缩写，意思就是确认，这里的指的是producer 需要接受到来自Broker 的ack 信息 https://juejin.cn/post/6937818838675226637
* 消费端消息丢失
  * 如果消费这边配置的是自动提交，万一消费到数据还没处理完，就自动提交offset了，但是此时你consumer直接宕机了，未处理完的数据丢失了，下次也消费不到了
  * https://blog.csdn.net/weixin_38399962/article/details/90057102
  * 如果在消费kafka的数据过程中，一直没有提交offset，那么在此程序运行的过程中它不会重复消费。但是如果重启之后，就会重复消费之前没有提交offset的数据。 
  * 如果在消费的过程中有几条或者一批数据数据没有提交offset，后面其他的消息消费后正常提交offset，那么服务端会更新为消费后最新的offset，不会重新消费，就算重启程序也不会重新消费。 
  * 消费者如果没有提交offset，程序不会阻塞或者重复消费，除非在消费到这个你不想提交offset的消息时你尝试重新初始化一个客户端消费者，即可再次消费这个未提交offset的数据。因为客户端也记录了当前消费者的offset信息，所以程序会在每次消费了数据之后，自己记录offset，而手动提交到服务端的offset与这个并没有关系，所以程序会继续往下消费。在你重新初始化客户端消费者之后，会从服务端得到最新的offset信息记录到本地。所以说如果当前的消费的消息没有提交offset，此时在你重新初始化消费者之后，可得到这条未提交消息的offset,从此位置开始消费。
  * https://dunwu.github.io/bigdata-tutorial/kafka/Kafka%E6%B6%88%E8%B4%B9%E8%80%85.html#_2-4-%E6%89%8B%E5%8A%A8%E6%8F%90%E4%BA%A4%E5%81%8F%E7%A7%BB%E9%87%8F
* 轮询时间， 尽量设置 ```max.poll.interval.ms```
# 前端
## CROS
* cross site 
* reading is fobidden but writing is oK
* forbidden by browser
* using allow-origin-access to let browser allow scripts from other sites to read the data  in response(header and body)