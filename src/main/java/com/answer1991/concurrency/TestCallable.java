package com.answer1991.concurrency;

import java.util.concurrent.Callable;

public class TestCallable implements Callable<String> {
	@Override
	public String call() throws Exception {
		
		return "hello world";
	}
}
