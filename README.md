# vincent

参照网上大神的项目:https://github.com/JayTange/Jantent,做了一个相关的升级,从spring boot1.5升级到spring boot 2.0.6

在项目的搭建过程中遇到了以下相关问题:

  1.拦截器过时与静态资源被拦截
    在spring boot 2.0中的拦截器是实现 WebMvcConfigurer这个类的,但是在老版本中是继承自HandlerInterceptorAdapter的,
    且在spring boot2.0的时候,是默认拦截所有静态资源的,因此需要对此做相关配置(这一块,后面我可能会在博客中做一个相关的总结)
    
  2.在spring boot 1.5中的时候,ValueOperations<String,Object> valueOperations的注入只需要,在上面加上注解@Autowired,
  但是到了spring boot2.0的时候就需要换成@Resource,并且要加上 name属性,且指向redisTemplate,为什么要这样,大家可以看看这篇博文:
  https://www.cnblogs.com/tangyuanyuan/p/8067923.html
  
  3.还是和valueOperations有关,在使用valueOperations做缓存set的时候,因为升级到2.0了,所以会出现set之后的值会出现乱码,例如:
  "\xac\xed\x00\x05t\x00\x04pass" 
  
  如果出现这样的情况,是因为你没有设置序列化Key的实例化对象,在1.5的时候代码是这样写的:
  
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
        return rcm;
    }
    
   但是在2.0中删除了这个构造器(RedisCacheManager rcm = new RedisCacheManager(redisTemplate);)，同时也不可以通过之前的
   setDefaultExpiration方法设置默认的缓存过期时间等，在新版本中可以通过以下的两种方式构造一个RedisCacheManager：
   
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {

          //在2.0中删除了这个构造器，同时也不可以通过之前的setDefaultExpiration方法设置默认的缓存过期时间等，在新版本中可以通过以下的两种方式构造一个RedisCacheManager：
//        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);

//        RedisCacheManager cacheManager = RedisCacheManager.create(factory);
//        return cacheManager;

        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        //解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        // 配置序列化（解决乱码的问题）,过期时间30秒
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(30))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                .disableCachingNullValues();

        RedisCacheManager cacheManager = RedisCacheManager.builder(factory)
                .cacheDefaults(config)
                .build();
        return cacheManager;
    }
    
    
    以上就是我遇到的相关问题,还有更多问题,那就期待大家挖掘了.
    
    这个项目中,所包含的功能有:主页,后台管理(管理主页,发布文章,文章管理,分类管理,文件管理,友情链接,系统设置)
    除了以上的界面功能,还有翡戒面功能,如:日志记录,记录来访IP名单,每天定时备份数据库
    
    
    

    本项目用到的技术和框架

        项目构建： maven
        web框架：spring boot 2.0.6
        数据库ORM：mybatis
        数据库连接池：Druid
        分页插件：PageHelper
        数据库：mysql
        缓存NOSQL：redis
        前段模板：thymeleaf
        文章展示：使用commonmark，将markdown转成html页面

    后面笔者可能还做相关的前后端分离项目,敬请期待!

  
