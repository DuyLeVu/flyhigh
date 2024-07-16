package com.roninengineer.Flyhigh.domain.booking.service.impl;

import com.roninengineer.Flyhigh.app.service.DistributedLock;
import com.roninengineer.Flyhigh.domain.booking.model.response.BookingResponse;
import com.roninengineer.Flyhigh.domain.booking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
  private final DistributedLock lock;

  @Override
  public BookingResponse booking() {
    var response = new BookingResponse();
    try {
      if (lock.acquireLock("key1", 15000, TimeUnit.MILLISECONDS)) {
        try {
          // impl business logic
          // Set bookingResponse
        } finally {
          lock.releaseLock("key1");
        }
      } else {
        // log.error("Failed to acquire lock. Resource is busy.");
      }
    } catch (Exception e) {
      // log.error("An error occurred during booking process", e);
    }
    return response;
  }
}
