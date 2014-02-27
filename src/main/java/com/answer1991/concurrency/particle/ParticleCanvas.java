package com.answer1991.concurrency.particle;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;

public class ParticleCanvas extends Canvas {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6400429967923777683L;
	
	private Particle[] particles = new Particle[0];
	
	public ParticleCanvas(int size) {
		setSize(new Dimension(size, size));
	}

	public synchronized Particle[] getParticles() {
		return particles;
		
	}

	public synchronized void setParticles(Particle[] particles) {
		if(particles == null) {
			throw new IllegalArgumentException("particles cannot be null");
		}
		
		this.particles = particles;
	}
	
	@Override
	public void paint(Graphics g) {
		Particle[] ps = getParticles();
		
		for(int i = 0; i < 10; i++) {
			ps[i].draw(g);
		}
	}
	
}
