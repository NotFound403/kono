## 手把手从零打造自己的Spring Boot脚手架（施工中）

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
   + spring data jpa (选)
   + redis  缓存中间件
   + mysql   数据库
   + mapstruct  bean转换器，编译期使用
   + lombok  bean简化工具
   + swagger2 文档（开发测试）
   + docker 容器技术
   
## 系列文章
- [从零搭建Spring Boot脚手架（1）：开篇以及技术选型](https://mp.weixin.qq.com/s/k0faB9xElGpCyLrJfGc7uQ)
- [从零搭建Spring Boot脚手架（2）：增加通用的功能](https://mp.weixin.qq.com/s/HKBF57Ut5EK9ccPeBxDZbA)   
- [从零搭建Spring Boot脚手架（3）：集成mybatis](https://mp.weixin.qq.com/s/fAawA2hNCzkB-rrt5ONjkw)
- [从零搭建Spring Boot脚手架（4）：手写Mybatis通用Mapper](https://mp.weixin.qq.com/s/QYr6itS6Y6WFZdBgSNH-5w)
## CHANGELOG   
变更日志


### day01  

1. 项目结构构建
2. 集成 **统一返回体**、**统一异常处理**、**快速类型转换**，**参数校验** 能力

### day02
1. 集成Mybatis

### day03

1. 手动实现Mybatis通用Mapper，实验性，不会合并到主分支

 