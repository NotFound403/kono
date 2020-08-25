## 手把手从零打造自己的Spring Boot脚手架（施工中）

> 建议按照day01、day02这样的次序渐进式学习，配合文章更佳。

相关记录在我个人博客  [码农小胖哥的博客:https://www.felord.cn/](https://www.felord.cn/)

请多多关注公众号：码农小胖哥
 
 ![](./qr.jpg)
 
## 环境依赖

- **Java**环境：**Java 8**
- 编译环境：**Maven**
- 版本管理：**Git** 

## 技术栈

以 **Spring Boot 2.3.x.RELEASE** 为基础，涉及的框架和中间件有：

   + springboot 基础整合框架
   + servlet4  web 标准
   + undertow 或者tomcat   web 容器
   + spring cache  缓存抽象层
   + spring security  安全框架
   + json web token  安全框架**token**技术
   + mybatis plus 3  **ORM**增强
   + redis  缓存中间件
   + mysql   数据库
   + Minio 对象存储服务
   + mapstruct  bean转换器，编译期使用
   + lombok  bean简化工具
   + swagger2 文档（开发测试）
   + docker 容器技术
   
## 系列文章
- [从零搭建Spring Boot脚手架（1）：开篇以及技术选型](https://mp.weixin.qq.com/s/k0faB9xElGpCyLrJfGc7uQ)
- [从零搭建Spring Boot脚手架（2）：增加通用的功能](https://mp.weixin.qq.com/s/HKBF57Ut5EK9ccPeBxDZbA)   
- [从零搭建Spring Boot脚手架（3）：集成mybatis](https://mp.weixin.qq.com/s/fAawA2hNCzkB-rrt5ONjkw)
- [从零搭建Spring Boot脚手架（4）：手写Mybatis通用Mapper](https://mp.weixin.qq.com/s/QYr6itS6Y6WFZdBgSNH-5w)
- [从零搭建Spring Boot脚手架（5）：整合 Mybatis Plus](https://mp.weixin.qq.com/s/9uVsi9yfE0QheEKCUyGNPA)
- [从零搭建Spring Boot脚手架（6）：整合Redis作为缓存](https://mp.weixin.qq.com/s/7l_mzFqjFD8lDT0iQ6W6bQ)
- [从零搭建Spring Boot脚手架（7）：整合OSS作为文件服务器](https://mp.weixin.qq.com/s/3wlQl3l110q1RNDE0kfI-w)
## Docker 

Mysql:

```shell script
docker run --name mysql-service -v d:/mysql/data:/var/lib/mysql -p 3306:3306 -e TZ=Asia/Shanghai -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.7 --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci --default-time_zone="+8:00"
```
Redis AOF:

```shell script
docker run -d --privileged=true -p 6379:6379  -v d:/redis/data:/data --name redis-service redis:4.0.13 redis-server --appendonly yes
``` 

Minio :
```shell script

docker run --name oss-service -d  -p 9000:9000  -e "MINIO_ACCESS_KEY=minio_access_key"  -e "MINIO_SECRET_KEY=felord_cn_sec_key"   minio/minio  server /data
```


Elasticsearch:
```shell script
docker run --name es-service -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" -e ES_JAVA_OPTS="-Xms64m -Xmx128m"    -d elasticsearch
```



## CHANGELOG   
变更日志


### day01  
Git分支 day01

1. 项目结构构建
2. 集成 **统一返回体**、**统一异常处理**、**快速类型转换**，**参数校验** 能力

### day02

Git分支 day02
1. 集成Mybatis

### day03

Git分支 day03
1. 手动实现Mybatis通用Mapper，实验性，不会合并到主分支 

### day04

Git分支 day04
1. 整合Mybatis Plus以及代码生成器

### day05

Git分支 day05
1. 整合Redis作为缓存，使用Spring Cache作为缓存抽象层
2. 对Redis的一些配置进行个性化，包括JSON序列化、Java 8 时间支持、缓存TTL过期个性化

### day06

Git分支 day06

自定义了OSS相关的操作的Spring Boot Starter以组件化这些基础功能,地址 [oss-spring-boot](https://gitee.com/felord/oss-spring-boot.git),使用方法参考文章或者`README.md`
> 需要`mvn install`自行安装到你本地的Maven仓库或者远程Maven仓库。
