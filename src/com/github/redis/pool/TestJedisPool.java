package com.github.redis.pool;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class TestJedisPool {

	public static void main(String[] args) {
		JedisPool jedisPool=JedisPoolUtil.getJedisPoolInstance();
		
		Jedis jedis=null;
		try {
			jedis=jedisPool.getResource();
			jedis.set("redisKey", "success");
			Thread.sleep(1000);
			System.out.println(jedis.get("redisKey"));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JedisPoolUtil.release(jedisPool, jedis);
		}
	}
}
