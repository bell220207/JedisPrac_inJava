package com.company.prac;
import java.util.Map;
import redis.clients.jedis.Jedis;

public class Main {
	public static void main(String[] args) {
		
		RedisMethod rm = new RedisMethod();
		Jedis jedis = new Jedis();
		
		// 1) Set 저장
		System.out.println(rm.redisSet("testKey", "testValue")); // result: ok
		
		// 2) Get 조회
		System.out.println(rm.redisGet("testKey")); // result: testValue
		
		// 3) Del 삭제
	//	jedis.del("testKey");
	//	System.out.println(rm.redisGet("testKey")); // result: null
		
		// 4) Hset 해쉬타입 저장
		System.out.println("result: "+ rm.redisHset("key1", "HashKey1", "value1")); // result: 1
		System.out.println("result: "+ rm.redisHset("key1", "HashKey1", "value1")); // result: 0 ==> 중복!
		System.out.println("result: "+ rm.redisHset("key1", "HashKey2", "value2")); // result: 1 ==> 다른 행으로 저장
		System.out.println("result: "+ rm.redisHset("key2", "HashKey3", "{\"ArrayValue1\",\"ArrayValue2\"}")); // result: 1
		System.out.println("result: "+ rm.redisHset("key3", "HashKey4", "{\"JSON_key\":\"JSON_value\"}")); // result: 1
		
		// 5) Hget 해쉬 타입 조회
		System.out.println(rm.redisHget("key1", "HashKey1")); // value1
		Map<String, String> map = jedis.hgetAll("key1"); // {HashKey2=value2, HashKey1=value1}
		System.out.println(map.get("HashKey1")); // value1
		System.out.println(map.get("HashKey2")); // value2
		System.out.println(rm.redisHget("key2", "HashKey3")); // result: {"ArrayValue1","ArrayValue2"}
		System.out.println(rm.redisHget("key3", "HashKey4")); // result: {"JSON_key":"JSON_value"}
		
		// 6) append 값 추가
		jedis.append("testKey", "appendTest");

		// 7) getJson
		System.out.println(rm.redisGetJson("key3", "HashKey4")); // {"JSON_key":"JSON_value"}
		
		// 8) setJson 나중에
		String str = "{" +
						"\"key1\"" + ":" + "\"value1\"" +"," +
						"\"key2\"" + ":" + "\"value2\"" +"," +
						"\"key3\"" + ":" + "\"value3\"" +"," +
						"\"key4\"" + ":" + "\"value4\""
					  + "}";
		System.out.println(str); // {"key1":"value1","key2":"value2","key3":"value3","key4":"value4"}
		System.out.println(rm.redisHsetJson("key4", "HashKey5", str));
		
		str = "["+ str + "," + str +"]";
		// [{"key1":"value1","key2":"value2","key3":"value3","key4":"value4"},{"key1":"value1","key2":"value2","key3":"value3","key4":"value4"}]
		
		System.out.println(rm.redisHsetJsonArray("key4", "HashKey6", str));
		System.out.println(rm.redisHgetArray("key4", "HashKey6"));

	}
}
