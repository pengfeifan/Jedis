package com.github.redis.test;

import redis.clients.jedis.Jedis;

public class TestPing {

	public static void main(String[] args) {
		//redis-cli -h 192.168.63.135 -p 6379
		Jedis jedis=new Jedis("192.168.63.135",6379);
		System.out.println(jedis.ping());
	}
}
