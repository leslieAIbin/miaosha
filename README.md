## 概述

![image-20210517222559242](http://test-1874253.oss-cn-beijing.aliyuncs.com/img/image-20210517222559242.png)

# miaosha


技术栈： springboot,redis,rabbitmq,jmeter,mybatis等



### 项目框架搭建

- SpringBoot环境搭建
- 集成Thymeleaf、Result结果封装
- 集成Mybtis + Druid
- 集成Jedis + Redis安装 + 通用缓存Key封装

### 实现登录功能

- 数据库设计
- 明文密码两次MD5处理
- JSR303参数检验+全局异常处理器
- 分布式Session

### 实现秒杀功能

- 数据库设计
- 商品列表页
- 商品详情页
- 订单详情页

### JMeter压测

- JMeter入门
- 自定义变量模拟多用户
- JMeter命令行使用
- Springboot 打War包

### 页面优化技术

- 页面缓存 + URL缓存 + 对象缓存
- 页面静态化，前后端分离
- 静态资源优化
- CDN优化

### 接口优化

- Redis预减库存减少数据库访问
- 内存标记减少Redis访问
- RabbitMQ队列缓冲，异步下单，增强用户体验
- RabbitMQ安装与Springboot继承
- 访问Nginx水平扩展
- 压测

### 安全优化

- 秒杀接口地址隐藏
- 数学公式验证码
- 接口防刷
