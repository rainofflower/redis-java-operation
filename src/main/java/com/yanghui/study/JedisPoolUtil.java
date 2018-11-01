package com.yanghui.study;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

//����ģʽ
public class JedisPoolUtil {
	private JedisPoolUtil(){}
	
	private static volatile JedisPool jedisPool = null;
	
	public static JedisPool getInstance(){
		if(null == jedisPool){
			synchronized (JedisPoolUtil.class) {
				if(null == jedisPool){
					JedisPoolConfig poolConfig = new JedisPoolConfig();
					poolConfig.setMaxIdle(32);
					poolConfig.setMaxWaitMillis(100*1000);
					poolConfig.setTestOnBorrow(true);
					
					jedisPool = new JedisPool(poolConfig, "192.168.141.123",6379);
				}
			}
		}
		return jedisPool;
	}
	
	public static void release(JedisPool jedisPool, Jedis jedis){
		if(null != jedis){
			//jedisPool.returnResourceObject(jedis);
			jedis.close();
		}
	}
}
