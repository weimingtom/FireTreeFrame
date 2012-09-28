package com.iteye.weimingtom.tween.test;

import java.awt.Color;
import java.awt.Graphics;

import com.iteye.weimingtom.firetree.MainFrame;
import com.iteye.weimingtom.tween.SimpleTweener;

public class MovingBox extends MainFrame {
	private static final long serialVersionUID = 1L;
	private static final boolean USE_TWEENER = false;
	
	private static final double TWEEN_TIME = 0.3;
	private int targetX;
	private int targetY;
	
	private SimpleTweener tween = new SimpleTweener
			//.EaseInCubic();
			.EaseOutCubic();
			//.EaseInOutCubic();
			//.EaseInOutQuad();
			//.EaseInOutBack(0); //bug
			//.EaseInOutBounce();
			//.EaseInOutCirc();
			//.EaseInOutElastic(Double.NaN, Double.NaN); //Bug
			//.EaseInOutExpo();
			//.EaseInOutQuart();
			//.EaseInOutQuint();
			//.EaseInOutSine();
	private float objx, objy;
	
	public MovingBox() {
		super("MovingBox", 800, 600, 60);
	}
	
	@Override
	protected void onInit() {
		
	}
	
	@Override
	protected void onDraw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, canvasWidth, canvasHeight);
		g.setColor(Color.BLACK);
		g.fillRect((int)objx, (int)objy, 100, 100);
	}
	
	@Override
	protected void onTick() {
		if (USE_TWEENER) {
			if (tween.update()) {
				objx = tween.currentX();
				objy = tween.currentY();
			}
		} else {
			objx = objx + (targetX - objx) / 10;
			objy = objy + (targetY - objy) / 10;
		}
	}

	@Override
	protected void onMouseClick(int x, int y) {
		targetX = x;
		targetY = y;
		if (USE_TWEENER) {
			double distance = Math.hypot(x - objx, y - objy);
			System.out.println("x = " + x + ", " + "y = " + y + ", " + "distence = " + distance);
			if (!tween.isTweening()) {
				tween.startTween(objx, x, objy, y, (long)(distance * TWEEN_TIME));
			} else {
				tween.startTween(objx, x, objy, y, (long)(distance * TWEEN_TIME));
			}
		}
	}
	
	public static final void main(String[] args) {
		new MovingBox().start();
	}
}
