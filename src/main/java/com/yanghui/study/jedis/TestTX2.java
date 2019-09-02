package com.yanghui.study.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestTX2 {
	public boolean transMethod() throws InterruptedException{
		Jedis jedis = new Jedis("192.168.141.123",6379);
		int balance; //�������
		int debt;    //Ƿ��
		int amtToSubtract = 10;  //ʵˢ���
		jedis.watch("balance");
		//jedis.set("balance", "5"); //�����ģ�������߳��޸Ĺ�balance
		Thread.sleep(7000);
		balance = Integer.parseInt(jedis.get("balance"));
		if(balance < amtToSubtract){
			jedis.unwatch();
			System.out.println("modify");
			return false;
		}
		else{
			System.out.println("---------------transaction---------------");
			Transaction transaction = jedis.multi();
			transaction.decrBy("balance", amtToSubtract);
			transaction.incrBy("debt", amtToSubtract);
			transaction.exec();
			balance = Integer.parseInt(jedis.get("balance"));
			debt = Integer.parseInt(jedis.get("debt"));
			System.out.println("---"+balance);
			System.out.println("---"+debt);
			return true;
		}
	}
	public static void main(String[] args) throws InterruptedException {
		TestTX2 test = new TestTX2();
		boolean retValue = test.transMethod();
		System.out.println("main retValue---------:"+retValue);
	}
}
