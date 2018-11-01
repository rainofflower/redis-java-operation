package com.yanghui.study;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class TestKeyValue {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.141.123",6379);
		//key
		Set<String> keys = jedis.keys("*");
		for(Iterator<String> iterator = keys.iterator(); iterator.hasNext();){
			String key = iterator.next();
			System.out.println(key);
		}
		System.out.println("jedis.exists===>"+jedis.exists("k2"));
		System.out.println(jedis.ttl("k1"));
		//Value
		//String
		//jedis.set("k1", "v1");
		System.out.println(jedis.get("k1"));
		//jedis.append("k1", "myredis");
		System.out.println("k1===>"+jedis.get("k1"));
		//jedis.set("k4", "k4_redis");
		System.out.println("k4===>"+jedis.get("k4"));
		System.out.println("-----------------------");
		jedis.mset("str1","v1","str2","v2","str3","v3");
		System.out.println(jedis.mget("str1","str2","str3","k4"));
		System.out.println("-----------------------");
		//list
		System.out.println("-----------------list-----------------");
		//jedis.lpush("mylist", "v1","v2","v3","v4","v5");
		//System.out.println(jedis.lpop("mylist"));
		List<String> list = jedis.lrange("mylist", 0, -1);
		System.out.print("mylist===>");
		for (String string : list) {
			System.out.print(string+" ");
		}
		System.out.println();
		//jedis.rpush("mylist2", "v1","v2","v3","v4","v5");
		List<String> list2 = jedis.lrange("mylist2", 0, -1);
		System.out.print("mylist2===>");
		for (String string : list2) {
			System.out.print(string+" ");
		}
		System.out.println();
		//set
		System.out.println("-----------------set-----------------");
		jedis.sadd("orders","001");
		jedis.sadd("orders","002");
		jedis.sadd("orders","003");
		Set<String> set1 = jedis.smembers("orders");
		for (Iterator iterator = set1.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
		}
		jedis.srem("orders", "002");
		System.out.println(jedis.smembers("orders").size());
		//hash
		System.out.println("-----------------hash-----------------");
		//jedis.hset("user", "username", "lisi");
		System.out.println(jedis.hget("user", "username"));
		Map<String,String> map = new HashMap<String,String>();
		map.put("telphone", "15270018554");
		map.put("address", "ncu");
		map.put("email", "rainofflower.yh@qq.com");
		//jedis.hmset("info", map);
		List<String> result = jedis.hmget("info", "telphone","email");
		for (String string : result) {
			System.out.println(string);
		}
		System.out.println("------------------zset----------------");
		//zset
		jedis.zadd("zset01", 60d,"v1");
		jedis.zadd("zset01", 70d, "v2");
		jedis.zadd("zset01", 80d,"v3");
		jedis.zadd("zset01", 90d,"v4");
		Set<String> s1 = jedis.zrange("zset01", 0, -1);
		for (Iterator iterator = s1.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
		}
	}
}
