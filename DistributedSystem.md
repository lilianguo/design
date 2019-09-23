分布式系统：
1. 由很多台计算机组成一个整体，一个整体一致对外并且处理同一个请求
2. 内部的每台计算机都可以相互通信（rest/rpc）
3. 客户端到服务端的一次请求到响应结束会历经多台计算机

Hadoop
https://www.youtube.com/watch?v=_QkKw82ge6g 
Storage(Disks) 单机存储
* HDFS hadoop distributed file system
* HDFS 写数据：写到3个DataNode, 第一个是本机或离他最近的节点， 第二个是相同机架， 第三个是不同机架. 这三个DataNode由第二部的NameNode选出。（机架感知）

周期性合并edit log， 生成一个文件，叫 FSImage

secondary nameNode 的工作是 check point