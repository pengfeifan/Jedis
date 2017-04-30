package com.github.redis.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestTransaction {

	public static void main(String[] args) {
		new TestTransaction().transMethod();
	}

	public boolean transMethod() {
		Jedis jedis = new Jedis("192.168.63.135", 6379);
		int balance;// 可用余额
		int debt;// 欠额
		int amtToSubtract = 10;// 实刷额度
		jedis.set("balance", "100");
		jedis.watch("balance");
		// jedis.set("balance","5");//此句不该出现，讲课方便。模拟其他程序已经修改了该条目
		balance = Integer.parseInt(jedis.get("balance"));
		if (balance < amtToSubtract) {
			jedis.unwatch();
			System.out.println("modify");
			return false;
		} else {
			System.out.println("***********transaction");
			Transaction transaction = jedis.multi();
			transaction.decrBy("balance", amtToSubtract);
			transaction.incrBy("debt", amtToSubtract);
			transaction.exec();
			balance = Integer.parseInt(jedis.get("balance"));
			debt = Integer.parseInt(jedis.get("debt"));

			System.out.println("*******" + balance);
			System.out.println("*******" + debt);
			return true;
		}
	}
}
