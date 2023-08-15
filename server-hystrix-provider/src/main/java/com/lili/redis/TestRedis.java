package com.lili.redis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class TestRedis {
    private Jedis jedis;

    private static Logger log = LogManager.getLogger(TestRedis.class);

    @Before
    public void setJedis() {
        //连接redis服务器(在这里是连接本地的)
        jedis = new Jedis("localhost", 6360, 100);
        //权限认证

//        jedis.auth("chenhaoxiang");
        log.debug("连接服务成功");
        log.debug("****************************连接end******************************************");
    }

    /**
     * Redis操作字符串
     */
    @Test
    public void testString() {
        log.debug("****************************STRing Strat******************************************");
        try {
            //添加数据
            jedis.expire("name", 10000);
            jedis.set("name", "chx"); //key为name放入value值为chx
            log.debug("拼接前:" + jedis.get("name"));//读取key为name的值

            //向key为name的值后面加上数据 ---拼接
            jedis.append("name", " is my name;");
            log.debug("拼接后:" + jedis.get("name"));

            //向key为name的值修改数据
            jedis.set("name", "lijunyu is my name;");
            log.debug("修改后:" + jedis.get("name"));

            //删除某个键值对
            jedis.del("name");
            log.debug("删除后:" + jedis.get("name"));

            //s设置多个键值对
            jedis.mset("name", "chenhaoxiang", "age", "20", "email", "chxpostbox@outlook.com");
            //用于将键的整数值递增1。如果键不存在，则在执行操作之前将其设置为0。 如果键包含错误类型的值或包含无法表示为整数的字符串，则会返回错误。此操作限于64位有符号整数。
            jedis.incr("age");

            log.debug(jedis.get("name") + " " + jedis.get("age") + " " + jedis.get("email"));
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            jedis.close();
        }
    }

    @Test
    public void testMap() {
        try {
            log.debug("****************************Map Strat******************************************");
            jedis.expire("user", 10);
            //添加数据
            Map<String, String> map = new HashMap<>(8);
            map.put("name", "chx");
            map.put("age", "100");
            map.put("email", "***@outlook.com");
            jedis.del("user");
            jedis.hmset("user", map);
            //取出user中的name，结果是一个泛型的List
            //第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key是可变参数
            List<String> list = jedis.hmget("user", "name", "age", "email");
            log.debug(list);
            map.put("name", "lijunyu");
            jedis.hmset("user", map);
            //第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key是可变参数
            list = jedis.hmget("user", "name", "age", "email");
            log.debug("修改后：" + list);

            //删除map中的某个键值
            jedis.hdel("user", "age");
            log.debug("age:" + jedis.hmget("user", "age")); //因为删除了，所以返回的是null
            log.debug("user的键中存放的值的个数:" + jedis.hlen("user")); //返回key为user的键中存放的值的个数2
            log.debug("是否存在key为user的记录:" + jedis.exists("user"));//是否存在key为user的记录 返回true
            log.debug("user对象中的所有key:" + jedis.hkeys("user"));//返回user对象中的所有key
            log.debug("user对象中的所有value:" + jedis.hvals("user"));//返回map对象中的所有value

            //拿到key，再通过迭代器得到值
            Iterator<String> iterator = jedis.hkeys("user").iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                log.debug(key + ":" + jedis.hmget("user", key));
            }
            jedis.del("user");
            log.debug("删除后是否存在key为user的记录:" + jedis.exists("user"));//是否存在key为user的记录
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            jedis.close();
        }

    }

    /**
     * jedis操作List
     */
    @Test
    public void testList() {
        try {
            log.debug("****************************List Strat******************************************");
            //移除javaFramwork所所有内容
            jedis.del("javaFramwork");
            //存放数据
            jedis.lpush("javaFramework", "spring");
            jedis.lpush("javaFramework", "springMVC");
            jedis.lpush("javaFramework", "mybatis");
            //取出所有数据,jedis.lrange是按范围取出
            //第一个是key，第二个是起始位置，第三个是结束位置
            log.debug("长度:" + jedis.llen("javaFramework"));
            //jedis.llen获取长度，-1表示取得所有
            log.debug("javaFramework:" + jedis.lrange("javaFramework", 0, -1));

            jedis.del("javaFramework");
            log.debug("删除后长度:" + jedis.llen("javaFramework"));
            log.debug(jedis.lrange("javaFramework", 0, -1));
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            jedis.close();
        }
    }

    /**
     * jedis操作Set
     */
    @Test
    public void testSet() {
        try {
            log.debug("****************************set Strat******************************************");
            //添加
            jedis.sadd("user", "chenhaoxiang");
            jedis.sadd("user", "hu");
            jedis.sadd("user", "chen");
            jedis.sadd("user", "xiyu");
            jedis.sadd("user", "chx");
            jedis.sadd("user", "are");
            log.debug("user中的value:" + jedis.smembers("user"));//获取所有加入user的value
            //移除user集合中的元素are
            jedis.srem("user", "are");
            log.debug("删除后user中的value:" + jedis.smembers("user"));//获取所有加入user的value
            log.debug("chx是否是user中的元素:" + jedis.sismember("user", "chx"));//判断chx是否是user集合中的元素
            log.debug("集合中的一个随机元素:" + jedis.srandmember("user"));//返回集合中的一个随机元素
            log.debug("user中元素的个数:" + jedis.scard("user"));
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            jedis.close();
        }
    }

    /**
     * 排序
     */
    @Test
    public void test() {
        try {
            log.debug("****************************Order Strat******************************************");
            jedis.del("number");//先删除数据，再进行测试
            jedis.rpush("number", "4");//将一个或多个值插入到列表的尾部(最右边)
            jedis.rpush("number", "5");
            jedis.rpush("number", "3");

            jedis.lpush("number", "9");//将一个或多个值插入到列表头部
            jedis.lpush("number", "1");
            jedis.lpush("number", "2");
            log.debug(jedis.lrange("number", 0, jedis.llen("number")));
            log.debug("排序:" + jedis.sort("number"));
            log.debug(jedis.lrange("number", 0, -1));//不改变原来的排序
            jedis.del("number");//测试完删除数据
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            jedis.close();
        }
    }
}
