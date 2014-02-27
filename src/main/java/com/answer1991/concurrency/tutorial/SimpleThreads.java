package com.answer1991.concurrency.tutorial;

public class SimpleThreads {
	public static void threadMessage(String msg) {
		String name = Thread.currentThread().getName();
		System.out.format("%s: %s%n", name, msg);
	}

	public static class MessageLoop implements Runnable {
		@Override
		public void run() {
			String[] msgList = { "msg1", "msg2", "msg3", "msg4" };

			try {
				for (int i = 0; i < msgList.length; i++) {
					Thread.sleep(4000);
					threadMessage(msgList[i]);
				}
			} catch (InterruptedException ex) {
				threadMessage("I wasn't finished");
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		long patience =  1000 * 10;
		
		threadMessage("start message pool");
		long startTime = System.currentTimeMillis();
		Thread t = new Thread(new MessageLoop());
		t.start();
		
		while(t.isAlive()) {
			threadMessage("still waiting..");
			
			t.join(1000);
			
			long now = System.currentTimeMillis();
			
			if((now - startTime) > patience && t.isAlive()) {
				threadMessage("tired of waiting");
				
				t.interrupt();
				
				t.join();
			}
		}
	}
}
