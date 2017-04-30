package com.github.redis.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class TestRedisAPI {

	public static void main(String[] args) {
		// redis-cli -h 192.168.63.135 -p 6379
		Jedis jedis = new Jedis("192.168.63.135", 6379);
		System.out.println(jedis.ping());
		// key
		System.out.println("#KEY----------------------------------------");
		Set<String> keys = jedis.keys("*");
		for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			System.out.println(key);
		}

		System.out.println("jedis.exists=====>" + jedis.exists("k2"));
		System.out.println(jedis.ttl("k1"));
		// String
		System.out.println("#STRING----------------------------------------");
		jedis.append("k1", "myredis");
		System.out.println(jedis.get("k1"));
		jedis.set("k4", "k4_redis");
		System.out.println("-------------------");

		jedis.mset("str1", "v1", "str2", "v2", "str3", "v3");
		System.out.println(jedis.mget("str1", "str2", "str3"));
		// list
		System.out.println("#LIST----------------------------------------");
		jedis.lpush("mylist", "v1", "v2", "v3", "v4", "v5");
		List<String> list = jedis.lrange("mylist", 0, -1);
		for (String element : list) {
			System.out.println(element);
		}
		// set
		System.out.println("#SET----------------------------------------");
		jedis.sadd("orders", "jd001");
		jedis.sadd("orders", "jd002");
		jedis.sadd("orders", "jd003");
		Set<String> set1 = jedis.smembers("orders");
		for (Iterator iterator = set1.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			System.out.println(key);
		}
		jedis.srem("orders", "jd002");
		System.out.println(jedis.smembers("orders").size());
		// hash
		System.out.println("#HASH----------------------------------------");
		jedis.hset("hash1", "userName", "lisi");
		System.out.println(jedis.hget("hash1", "userName"));
		Map<String, String> map = new HashMap<String, String>();
		map.put("telphone", "10086");
		map.put("address", "shanghai");
		map.put("email", "abc@163.com");
		jedis.hmset("hash2", map);
		List<String> result = jedis.hmget("hash2", "telphone", "email");
		for (String element : result) {
			System.out.println(element);
		}
		// zset
		System.out.println("#ZSET----------------------------------------");
		jedis.zadd("zset01", 60d, "v1");
		jedis.zadd("zset01", 70d, "v2");
		jedis.zadd("zset01", 80d, "v3");
		jedis.zadd("zset01", 90d, "v4");

		Set<String> s1 = jedis.zrange("zset01", 0, -1);
		for (Iterator iterator = s1.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			System.out.println(key);
		}

	}
}
