package com.github.redis.test;

import redis.clients.jedis.Jedis;

public class TestMasterSlave {

	public static void main(String[] args) throws InterruptedException {
		Jedis jedisMaster=new Jedis("192.168.63.135",6379);
		Jedis jedisSlave=new Jedis("192.168.63.135",6380);
		jedisSlave.slaveof("192.168.63.135",6379);
		jedisMaster.set("hi", "world");
		String result=jedisSlave.get("hi");
		Thread.sleep(1000*10);
		System.out.println(result);
	}
}
