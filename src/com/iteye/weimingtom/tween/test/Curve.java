package com.iteye.weimingtom.tween.test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.iteye.weimingtom.firetree.MainFrame;
import com.iteye.weimingtom.tween.GeometricTweener;
import com.iteye.weimingtom.tween.SimpleTweener;

public class Curve extends MainFrame {
	private static final long serialVersionUID = 1L;
	
	private SimpleTweener[] TWEENERS = {
		new SimpleTweener.EaseNone(),
		new SimpleTweener.EaseInQuad(),
		new SimpleTweener.EaseOutQuad(),
		new SimpleTweener.EaseInOutQuad(),
		new SimpleTweener.EaseOutInQuad(), //bug
		new SimpleTweener.EaseInCubic(),
		new SimpleTweener.EaseOutCubic(),
		new SimpleTweener.EaseInOutCubic(),
		new SimpleTweener.EaseOutInCubic(),
		new SimpleTweener.EaseInQuart(),
		new SimpleTweener.EaseOutQuart(),
		new SimpleTweener.EaseInOutQuart(),
		new SimpleTweener.EaseOutInQuart(),
		new SimpleTweener.EaseInQuint(),
		new SimpleTweener.EaseOutQuint(),
		new SimpleTweener.EaseInOutQuint(),
		new SimpleTweener.EaseOutInQuint(),
		new SimpleTweener.EaseInSine(),
		new SimpleTweener.EaseOutSine(),
		new SimpleTweener.EaseInOutSine(),
		new SimpleTweener.EaseOutInSine(),
		new SimpleTweener.EaseInExpo(),
		new SimpleTweener.EaseOutExpo(),
		new SimpleTweener.EaseInOutExpo(),
		new SimpleTweener.EaseOutInExpo(),
		new SimpleTweener.EaseInCirc(),
		new SimpleTweener.EaseOutCirc(),
		new SimpleTweener.EaseInOutCirc(),
		new SimpleTweener.EaseOutInCirc(),
		new SimpleTweener.EaseInElastic(Double.NaN, Double.NaN), //bug?
		new SimpleTweener.EaseOutElastic(Double.NaN, Double.NaN), //bug
		new SimpleTweener.EaseInOutElastic(Double.NaN, Double.NaN), //bug mid
		new SimpleTweener.EaseOutInElastic(Double.NaN, Double.NaN), //bug
		new SimpleTweener.EaseInBack(Double.NaN),
		new SimpleTweener.EaseOutBack(Double.NaN),
		new SimpleTweener.EaseInOutBack(Double.NaN), //bug
		new SimpleTweener.EaseOutInBack(Double.NaN),
		new SimpleTweener.EaseInBounce(),
		new SimpleTweener.EaseOutBounce(),
		new SimpleTweener.EaseInOutBounce(),
		new SimpleTweener.EaseOutInBounce(),
		new GeometricTweener(0.33f, true),
	};
	
	private int currentTween = 0;
	private SimpleTweener tweener = TWEENERS[currentTween];
	
	public Curve() {
		super("Curve", 800, 600, 24);
	}

	@Override
	protected void onDraw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, canvasWidth, canvasHeight);
		
		int orgX = 50;
		int orgY = 500;
		int rangeT = 400;
		int y1 = 0, y2 = 0;
		int x1 = 0, x2 = 0;
		
		tweener.startTween(0, rangeT, 0, rangeT, rangeT);
		int[] nums = new int[rangeT + 1];
		for (int i = 0; i < rangeT + 1; i++) {
			nums[i] = (int)tweener.currentX();
			tweener.update();
		}
		int[] numsGeometric = new int[rangeT + 1];
		float lastNum = 0;
		for (int i = 0; i < rangeT + 1; i++) {
			numsGeometric[i] = (int)lastNum;
			lastNum += (rangeT - lastNum) / 3;
		}
		
		g.setColor(Color.BLACK);
		g.drawLine(orgX, orgY - rangeT, orgX, orgY);
		g.drawLine(orgX, orgY, orgX + rangeT, orgY);
		g.drawLine(orgX + rangeT, orgY, orgX + rangeT, orgY - rangeT);
		g.drawLine(orgX + rangeT, orgY - rangeT, orgX, orgY - rangeT);
		for (int t = 0; t < rangeT; t++) {
			x1 = t;
			x2 = t + 1;
			y1 = nums[t];
			y2 = nums[t + 1];
			x1 = orgX + x1;
			x2 = orgX + x2;
			y1 = orgY - y1;
			y2 = orgY - y2;
			g.drawLine(x1, y1, x2, y2);
		}
		
		g.setColor(Color.RED);
		for (int t = 0; t < rangeT; t++) {
			x1 = t;
			x2 = t + 1;
			y1 = numsGeometric[t];
			y2 = numsGeometric[t + 1];
			x1 = orgX + x1;
			x2 = orgX + x2;
			y1 = orgY - y1;
			y2 = orgY - y2;
			g.drawLine(x1, y1, x2, y2);
		}
		
		if (tweener != null) {
			String title = "(" + (currentTween + 1) + "/" + 
					TWEENERS.length + ")" +
					tweener.getClass().getSimpleName();
			setFrameTitle(title);
		}
	}
	
	@Override
	protected void onMouseClick(int x, int y) {
		currentTween++;
		if (currentTween > TWEENERS.length - 1) {
			currentTween = 0;
		}
		tweener = TWEENERS[currentTween];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_DOWN:
			currentTween--;
			if (currentTween < 0) {
				currentTween = TWEENERS.length - 1;
			}
			tweener = TWEENERS[currentTween];
			break;
			
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_UP:
			currentTween++;
			if (currentTween > TWEENERS.length - 1) {
				currentTween = 0;
			}
			tweener = TWEENERS[currentTween];
			break;
		}
	}

	public final static void main(String[] args) {
		new Curve().start();
	}
}
