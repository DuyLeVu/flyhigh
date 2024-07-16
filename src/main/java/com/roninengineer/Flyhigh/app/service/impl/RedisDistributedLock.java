package com.roninengineer.Flyhigh.app.service.impl;

import com.roninengineer.Flyhigh.app.service.DistributedLock;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisDistributedLock implements DistributedLock {
  private final RedisTemplate<String, Object> redisTemplate;

  @Override
  public boolean acquireLock(String lockKey, long timeout, TimeUnit unit) {
    return redisTemplate.opsForValue().setIfAbsent(lockKey, "locked", timeout, unit);
  }

  @Override
  public void releaseLock(String lockKey) {
    redisTemplate.delete(lockKey);
  }
}
