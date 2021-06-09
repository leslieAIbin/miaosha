## 概述

![image-20210517222559242](http://test-1874253.oss-cn-beijing.aliyuncs.com/img/image-20210517222559242.png)



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

  



## 项目搭建

### 集成thymeleaf

- 添加thymeleaf依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

- 配置文件

```properties
#thymeleaf
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
spring.thymeleaf.cache=false
spring.thymeleaf.servlet.content-type=text/html
spring.thymeleaf.enabled=true
spring.thymeleaf.encoding=UTF-8
spring.thymeleaf.mode=HTML5
```

- controller--DemoController

  ```java
  @Controller
  @RequestMapping("")
  public class DemoController {
  
      @RequestMapping("/")
      @ResponseBody
      String home() {
          return "Hello World!";
      }
  
      /**
       * REST api json 格式输出
       */
      @RequestMapping("/hello")
      @ResponseBody
      public Result<String> hello() {
          return Result.success("hello,imooc");
      }
  
      @RequestMapping("/helloError")
      @ResponseBody
      public Result<String> helloError() {
          return Result.error(CodeMsg.SERVER_ERROR);
      }
  
      /**
       * 集成 thymeleaf 做页面模板
       */
      @RequestMapping("/thymeleaf")
      public String thymeleaf(Model model) {
          model.addAttribute("name", "fly");
          return "hello";
      }
  
  }
  ```

- result--CodeMsg

  ```java
  public class CodeMsg {
     private int code;
     private String msg;
  
     /**
      * 分模块定义返回状态
      */
     
     //通用异常
     public static CodeMsg SUCCESS = new CodeMsg(0, "success");
     public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
     //登录模块 5002XX
     
     //商品模块 5003XX
     
     //订单模块 5004XX
     
     //秒杀模块 5005XX
  
     /**
      * 构造函数私有,禁止外部创建对象
      */
  
     private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
     }
  
     /**
      * 不生成 setter 方法,禁止外部修改属性
      */
     
     public int getCode() {
        return code;
     }
     public String getMsg() {
        return msg;
     }
  }
  ```

- result-Result

  ```java
  public class Result<T> {
     private int code;
     private String msg;
     private T data;
  
     public static <T> Result<T> success(T data){
        return new  Result<T>(data);
     }
  
     public static <T> Result<T> error(CodeMsg cm){
        return new  Result<T>(cm);
     }
  
     /**
      * 构造函数私有, 禁止外部创建对象
      */
     private Result(T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
     }
     
     private Result(CodeMsg cm) {
        if(cm == null) {
           return;
        }
        this.code = cm.getCode();
        this.msg = cm.getMsg();
     }
  
     /**
      * 不生成 setter 方法, 禁止外部修改属性
      */
  
     public int getCode() {
        return code;
     }
     public String getMsg() {
        return msg;
     }
     public T getData() {
        return data;
     }
  }
  ```

### 集成mybatis

- 添加pom依赖：mybatis-spring-boot-starter

```xml
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>1.3.1</version>
</dependency>
```

- 添加配置：mybatis.*

```properties
# mybatis
mybatis.type-aliases-package=com.leslie.domain
mybatis.configuration.map-underscore-to-camel-case=true
mybatis.configuration.default-fetch-size=100
mybatis.configuration.default-statement-timeout=3000
mybatis.mapperLocations = classpath:com/leslie/dao/*.xml
```

### 添加mysql客户端、druid连接依赖池

- 添加mysql依赖

  ```xml
  <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>druid</artifactId>
      <version>1.0.5</version>
  </dependency>
  <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
  </dependency>
  ```

- 添加druid依赖

```properties
# druid
spring.datasource.url=jdbc:mysql://localhost:3306/miaosha?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
spring.datasource.filters=stat
spring.datasource.maxActive=2
spring.datasource.initialSize=1
spring.datasource.maxWait=60000
spring.datasource.minIdle=1
spring.datasource.timeBetweenEvictionRunsMillis=60000
spring.datasource.minEvictableIdleTimeMillis=300000
spring.datasource.validationQuery=select 'x'
spring.datasource.testWhileIdle=true
spring.datasource.testOnBorrow=false
spring.datasource.testOnReturn=false
spring.datasource.poolPreparedStatements=true
spring.datasource.maxOpenPreparedStatements=20
```

- controller-SampleController

  ```java
  @Autowired
  UserService userService;
  
  @RequestMapping("/db/get")
  @ResponseBody
  public Result<User> dbGet(){
      User user = userService.getById(1);
      return Result.success(user);
  }
  ```

- dao-UserDao

  ```java
  @Mapper
  @Component
  public interface UserDao {
  
      @Select("select * from user where id = #{id}")
      public User getById(@Param("id") int id);
  
      @Insert("insert into user(id , name) values(#{id}, #{name})")
      public int insert(User user);
  }
  ```

- domain-User

  ```java
  @Data
  public class User {
      private int id;
      private String name;
  
  }
  ```

- Service-Uservice

  ```java
  @Service
  public class UserService {
      @Autowired
      UserDao userDao;
  
      public User getById(int id){
         return userDao.getById(id);
      }
  
      @Transactional
      public boolean tx(){
          User u1= new User();
          u1.setId(4);
          u1.setName("2222");
          userDao.insert(u1);
  
          User u2= new User();
          u2.setId(1);
          u2.setName("11111");
          userDao.insert(u2);
  
          return true;
      }
  }
  ```

### 集成redis

虚拟机

**安装路径一般为  /usr/local/**

下载安装：https://redis.io/

```python
# wget http://download.redis.io/releases/redis-6.0.8.tar.gz
# tar xzf redis-6.0.8.tar.gz
# cd redis-6.0.8
# make 
# make install  #将编译完成的文件添加到启动目录里面
```

执行完 **make** 命令后，redis-6.0.8 的 **src** 目录下会出现编译后的 redis 服务程序 redis-server，还有用于测试的客户端程序 redis-cli：

下面启动 redis 服务：

```python
# cd src
# ./redis-server
```

注意这种方式启动 redis 使用的是默认配置。也可以通过启动参数告诉 redis 使用指定配置文件使用下面命令启动。

```python
# cd src
# ./redis-server ../redis.conf
```

配置文件 ： redis.conf

更改参数：

```python
bind 0.0.0.0  # 允许任意服务器访问
daemonize yes # 允许后台执行
requirepass 123456   # 设置访问密码 
```

关闭服务 shoutdown save

配置文件：

~~~java
/usr/local/redis/redis.conf
/usr/local/redis/redis.log
/usr/local/redis/data
~~~

- 添加Jedis依赖

```xml
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
    <version>2.9.0</version>
</dependency>

```

- 添加Fastjson依赖

```xml
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.38</version>
</dependency>
```

- 配置

```properties
# redis
redis.host=192.168.150.129
redis.port=6379
redis.timeout=3
redis.password=123456
redis.poolMaxTotal=10
redis.poolMaxIdle=10
redis.poolMaxWait=3
```

- 测试连接  test

```java
@Test
public void testJedis() throws Exception{

    //创建一个连接Jedis对象，参数:host、port
    Jedis jedis=new Jedis("192.168.150.129",6379);
    //直接使用Jedis操作Redis，所有Jedis命令都对应一个方法
    jedis.auth("123456");
    jedis.set("test123","my first jedis test");
    String string = jedis.get("test123");
    System.out.println(string);
    //关闭连接
    jedis.close();
}
```

- redis-RedisConfig

```java
@Data
@Component
@ConfigurationProperties(prefix = "redis")  // 读取配置文件中redis开头的
public class RedisConfig {
    private String host;
    private int port;
    private int timeout; //秒
    private String password;
    private int poolMaxTotal;
    private int poolMaxIdle;
    private int poolMaxWait;//秒
}
```

- redis-Service

```java
@Service

public class RedisService {
    @Autowired
    JedisPool jedisPool;



    public <T> T get(String key, Class<T> clazz){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String str = jedis.get(key);
            T t = stringToBean(str,clazz);
            return t;
        }finally {
            returnToPool(jedis);
        }
    }

    private void returnToPool(Jedis jedis){
        if(jedis != null){
            jedis.close();
        }
    }

    private <T> T stringToBean(String str, Class<T> clazz){
        if(str == null || str.length() <= 0 || clazz == null){
            return null;
        }
        if(clazz == int.class || clazz == Integer.class){
            return (T)Integer.valueOf(str);
        }else if(clazz == String.class){
            return (T) str;
        }else if(clazz == long.class || clazz == Long.class){
            return  (T)Long.valueOf(str);
        } else {
            return JSON.toJavaObject(JSON.parseObject(str),clazz);
        }
    }

    private <T> String beanToString(T value){
        if (value == null){
            return null;
        }
        Class<?> clazz = value.getClass();
        if(clazz == int.class || clazz == Integer.class){
            return ""+value;
        }else if(clazz == String.class){
            return (String)value;

        }else if(clazz == long.class || clazz == Long.class){
            return ""+value;
        } else {
            return JSON.toJSONString(value);
        }
    }



    public <T> boolean set(String key, T value){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if(str == null || str.length() <= 0){
                return false;
            }
            jedis.set(key, str);
            return true;
        }finally {
            returnToPool(jedis);
        }
    }

}
```

- redis-RedisPoolFactory

```java
@Service
public class RedisPoolFactory {
    @Autowired
    RedisConfig redisConfig;

    @Bean
    public JedisPool JedisPoolFactory(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
        poolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
        poolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000);
        JedisPool jp = new JedisPool(poolConfig, redisConfig.getHost(), redisConfig.getPort(),
                redisConfig.getTimeout()*1000, redisConfig.getPassword(),0);
        return jp;
    }
}
```

**通用缓存Key封装**

![image-20210609112607972](http://test-1874253.oss-cn-beijing.aliyuncs.com/img/image-20210609112607972.png)

不同的类型用不同类型的Prefix存入到redis中

```java
boolean ret = redisService.set(Prefix + "key2","key2test");
```

