package com.roninengineer.Flyhigh.app.service;

import java.util.concurrent.TimeUnit;

public interface DistributedLock {
  boolean acquireLock(String lockKey, long timeout, TimeUnit unit);

  void releaseLock(String lockKey);
}
