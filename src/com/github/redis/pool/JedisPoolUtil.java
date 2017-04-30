package com.github.redis.pool;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
	
	//被volatile修饰的变量不会被本地线程缓存，对该变量的读写都是直接操作共享内存。
	private static volatile JedisPool jedisPool=null;
	
	private JedisPoolUtil(){}
	
	public static JedisPool getJedisPoolInstance(){
		
		if(null == jedisPool ){
			synchronized(JedisPoolUtil.class){
				JedisPoolConfig poolConfig=new JedisPoolConfig();
				poolConfig.setMaxActive(1000);
				poolConfig.setMaxIdle(32);
				poolConfig.setMaxWait(100*1000);
				poolConfig.setTestOnBorrow(true);
				jedisPool=new JedisPool(poolConfig,"192.168.63.135",6379);
			}
		}
		return jedisPool;
	}
	
	public static void release(JedisPool jedisPool,Jedis jedis){
		if(null !=jedis){
			jedisPool.returnResourceObject(jedis);
		}
	}
}
