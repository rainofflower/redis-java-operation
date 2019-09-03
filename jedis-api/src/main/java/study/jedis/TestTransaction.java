package study.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestTransaction {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.141.123",6379);
		
		Transaction transaction = jedis.multi();
		transaction.set("k5", "v55");
		//ִ������
		//transaction.exec();
		//��������
		transaction.discard();
	}
}
