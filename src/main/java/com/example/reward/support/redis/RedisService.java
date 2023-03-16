package com.example.reward.support.redis;

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
		log.info("set :: key = [{}], value = [{}]", key, value);
		stringRedisTemplate.set(key, value);
	}

	// 기본 key value
	public String get(String key) {
		log.info("get :: key = [{}]", key);
		return redisTemplate.opsForValue().get(key);
	}

	public Long push(String key, String value) {
		log.info("push :: key = [{}], value = [{}]", key, value);
		return listOperations.rightPush(key, value);
	}

	public String getLastIndex(String key) {
		log.info("push :: key = [{}]", key);
		return listOperations.index(key, -1);
	}

	public Long increment(String key) {
		Long value = stringRedisTemplate.increment(key);
		log.info("INCREMENT :: key = [{}], value = [{}]", key, value);
		return value;
	}

	public boolean isExist(String key) {
		log.info("isExist :: key = [{}]", key);
		return Boolean.TRUE.equals(redisTemplate.hasKey(key));
	}

	public boolean remove(String key) {
		log.info("remove :: key = [{}]", key);
		return Boolean.TRUE.equals(redisTemplate.delete(key));
	}

	public long size(String key) {
		log.info("size :: key = [{}], value = [{}]", key, listOperations.size(key));
		return listOperations.size(key) == null ? 0 : listOperations.size(key);
	}
}
