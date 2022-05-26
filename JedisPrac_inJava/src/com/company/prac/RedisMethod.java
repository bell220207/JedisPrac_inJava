package com.company.prac;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import redis.clients.jedis.Jedis;

public class RedisMethod {
		
		private static final String Redis_host = "127.0.0.1";
		private static final String Redis_port = "6379";
		
		// 플레인 저장
		public static String redisSet(String key, String value) {
			String ip = Redis_host;
			String port = Redis_port;
			Jedis jedis = new Jedis(ip, Integer.parseInt(port));
			jedis.connect();
			String result = jedis.set(key, value); // 성공 시, ok를 반환
			jedis.disconnect();
			return "result: "+result;
		}
		
		// 플레인 조회
		public static String redisGet(String key) {
			String ip = Redis_host;
			String port = Redis_port;
			Jedis jedis = new Jedis(ip, Integer.parseInt(port));
			String result = jedis.get(key); // value를 반환
			jedis.disconnect();
			return "result: "+result;
		}
		
		// 해쉬타입 저장
		public static Long redisHset(String table, String key, String value) {
			String ip = Redis_host;
			String port = Redis_port;
			Jedis jedis = new Jedis(ip, Integer.parseInt(port));
			long result = jedis.hset(table, key, value); // 성공 시 1, 실패 시 0 반환
			jedis.disconnect();
			return result;
		}

		// 해쉬타입 조회
		public static String redisHget(String table, String key) {
			String ip = Redis_host;
			String port = Redis_port;
			Jedis jedis = new Jedis(ip, Integer.parseInt(port));
			String result = jedis.hget(table, key); // value를 반환
			jedis.disconnect();
			return "result: "+result;
		}
		
		// 제이슨 형태로 저장 (해쉬)
		public static long redisHsetJson(String table, String key, String value) {
			String ip = Redis_host;
			String port = Redis_port;
			Jedis jedis = new Jedis(ip, Integer.parseInt(port));
			try {			
				JSONObject obj = new JSONObject(value);
			}catch(Exception e) {
				e.printStackTrace();
			}
			long result = jedis.hset(table, key, value);
			jedis.disconnect();
			return result;
		}

		// 제이슨 형태로 조회 (해쉬)
		public static JSONObject redisGetJson(String table, String key) {
			String ip = Redis_host;
			String port = Redis_port;
			Jedis jedis = new Jedis(ip, Integer.parseInt(port));
			String str = jedis.hget(table, key);
			JSONObject obj = null;
			try {
				obj = new JSONObject(str);
			}catch(Exception e) {
				e.printStackTrace();
			}
			jedis.disconnect();
			return obj;
		}		
		
		// 제이슨 배열 형태로 저장 (해쉬)
		public static long redisHsetJsonArray(String table, String key, String value) {
			String ip = Redis_host;
			String port = Redis_port;
			Jedis jedis = new Jedis(ip, Integer.parseInt(port));
			JSONArray array = null;
			try {
				array = new JSONArray(value);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			long result = jedis.hset(table, key, value);
			jedis.disconnect();
			return result;
		}
		
		// 제이슨 배열 형태로 조회 (해쉬)
		public static JSONArray redisHgetArray(String table, String key) {
			String ip = Redis_host;
			String port = Redis_port;
			Jedis jedis = new Jedis(ip, Integer.parseInt(port));
			String str = jedis.hget(table, key);
			JSONArray array = null;
			try {
				array = new JSONArray(str);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			jedis.disconnect();
			return array;
		}
}
