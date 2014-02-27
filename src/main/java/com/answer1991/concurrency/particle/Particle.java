package com.answer1991.concurrency.particle;

import java.awt.Graphics;
import java.util.Random;

public class Particle {
	private int x;
	private int y;
	private final Random rng = new Random();
	
	public Particle(int initialX, int initialY) {
		this.x = initialX;
		this.y = initialY;
	}
	
	public synchronized void move() {
		this.x = rng.nextInt(10) - 5;
		this.y = rng.nextInt(20) - 5;
	}
	
	public void draw(Graphics g) {
		int lx, ly;
		
		synchronized (this) {
			lx = x;
			ly = y;
		}
		
		g.drawRect(lx, ly, 10, 10);
	}
}
