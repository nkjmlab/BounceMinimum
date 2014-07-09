package org.nkjmlab.game.bounce;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * ボールを打ち返すパドル.
 * 
 * @author nkjm
 * 
 */
public class Paddle implements KeyListener {

	// パドルのx,y座標
	public int x = 0;
	public int y = 0;

	// パドルが存在できる左端のx座標がleftEdge，右端のx座標がrightEdgeである．
	private int leftEdge = 0;
	private int rightEdge;

	// パドルの幅と高さと色
	public int width;
	public int height;
	private Color color;

	public Paddle(int width, int height, int rightEdge, int bottomEdge) {
		this.width = width;
		this.height = height;
		this.color = Color.red;
		this.rightEdge = rightEdge;
		this.y = bottomEdge - height - 60;
		this.x = rightEdge / 2 - width / 2;
	}

	/**
	 * キーが押されたイベントを処理する．
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_RIGHT) {
			moveRight(5);
		} else if (key == KeyEvent.VK_LEFT) {
			moveLeft(5);
		}
	}

	private void moveRight(int size) {
		if (!isReachToRightEdge()) {
			x += size;
		}
	}

	private void moveLeft(int size) {
		if (!isReachToLeftEdge()) {
			x -= size;
		}
	}

	private boolean isReachToRightEdge() {
		return x > rightEdge - width;
	}

	private boolean isReachToLeftEdge() {
		return x < leftEdge;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Do nothing
	}

	/**
	 * パドルを描画する．
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setColor(this.color);
		g.fillRect(x, y, width, height);
	}

}