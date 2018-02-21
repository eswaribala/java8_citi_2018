package com.polaris.utility;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;

public class StampedLockDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService executor = Executors.newFixedThreadPool(2);
		StampedLock lock = new StampedLock();
          
		executor.submit(() -> {
		    long stamp = lock.tryOptimisticRead();
		    System.out.println(stamp);
		    try {
		        System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
		       try {
				Thread.sleep(1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		        System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
		        try {
					Thread. sleep(1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
		    } finally {
		        lock.unlock(stamp);
		    }
		});

		executor.submit(() -> {
		    long stamp = lock.writeLock();
		    try {
		        System.out.println("Write Lock acquired");
		        try {
					Thread.sleep(2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    } finally {
		        lock.unlock(stamp);
		        System.out.println("Write done");
		    }
		});

	}

}
