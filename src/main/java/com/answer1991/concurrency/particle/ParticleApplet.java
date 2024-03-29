package com.answer1991.concurrency.particle;

import java.applet.Applet;

public class ParticleApplet extends Applet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2333579153846593932L;

	protected Thread[] threads = null;

	protected final ParticleCanvas canvas = new ParticleCanvas(100);

	public void init() {
		add(canvas);
	}

	protected Thread makeThread(final Particle p) { // utility
		Runnable runloop = new Runnable() {
			public void run() {
				try {
					for (;;) {
						p.move();
						canvas.repaint();
						Thread.sleep(100); // 100msec is arbitrary
					}
				} catch (InterruptedException e) {
					return;
				}
			}
		};
		return new Thread(runloop);
	}

	public synchronized void start() {
		int n = 10; // just for demo
		if (threads == null) { // bypass if already started
			Particle[] particles = new Particle[n];
			for (int i = 0; i < n; ++i)
				particles[i] = new Particle(50, 50);
			canvas.setParticles(particles);
			threads = new Thread[n];
			for (int i = 0; i < n; ++i) {
				threads[i] = makeThread(particles[i]);
				threads[i].start();
			}
		}
	}

	public synchronized void stop() {
		if (threads != null) { // bypass if already stopped
			for (int i = 0; i < threads.length; ++i)
				threads[i].interrupt();
			threads = null;
		}
	}
}
