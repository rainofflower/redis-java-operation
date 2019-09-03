package study.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class TestPool {
	public static void main(String[] args) {
		JedisPool jedisPool = JedisPoolUtil.getInstance();
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set("name", "yanghui");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JedisPoolUtil.release(jedisPool, jedis);
		}
	}
}
