package com.example.reward.support.redis;

import com.example.reward.utils.GsonHelper;
import java.lang.reflect.Type;
import javax.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisService {

	@Resource(name = "redisTemplate")
	private ListOperations<String, String> listOperations;

	@Resource(name = "redisTemplate")
	private ValueOperations<String, Object> stringRedisTemplate;

	private final RedisTemplate<String, String> redisTemplate;


	// 기본 key value
	public void set(String key, Object value) {
		stringRedisTemplate.set(key, value);
	}

	// 기본 key value
	public String get(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	public <T> T get(String key, Type deserializeType) {
		Object value = stringRedisTemplate.get(key);
		if (value == null) {
			return null;
		}

		return GsonHelper.fromJson(value.toString(), deserializeType);
	}

	public Long push(String key, String value) {
		return listOperations.rightPush(key, value);
	}

	public String getLastIndex(String key) {
		return listOperations.index(key, -1);
	}

	public Long increment(String key) {
		Long value = stringRedisTemplate.increment(key);
		return value;
	}

	public boolean isExist(String key) {
		return Boolean.TRUE.equals(redisTemplate.hasKey(key));
	}

	public boolean remove(String key) {
		return Boolean.TRUE.equals(redisTemplate.delete(key));
	}

	public long size(String key) {
		return listOperations.size(key) == null ? 0 : listOperations.size(key);
	}
}
