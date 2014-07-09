package org.nkjmlab.game.bounce;

import java.awt.Color;
import java.awt.Graphics;

/***
 * パドルで打ち返すボール.
 * 
 * @author nkjm
 * 
 */
public class Ball {

	// ボールのx,y座標
	public int x = 0;
	public int y = 0;

	// ボールのx,y座標方向の加速度
	private int dx = 1;
	private int dy = 1;

	// x-y平面でボールが存在できる領域の左上座標が(left, top)であり，右下座標が(right, bottom)となる．
	private int left = 0;
	private int top = 0;
	private int right;
	private int bottom;

	// ボールの幅と高さ
	protected int width;
	protected int height;

	// ボールの色
	private Color color;

	public Ball(int width, int height, int rightEdge, int bottomEdge) {
		this.width = width;
		this.height = height;
		this.right = rightEdge;
		this.bottom = bottomEdge;
		this.color = Color.red;
	}

	/**
	 * ボールの加速度を変更する．
	 */
	public void accelerate() {
		if (isReachToButtom()) {
			// dy = -1;
		} else if (isReachToTop()) {
			dy = 1;
		} else if (isReachToLeftEdge()) {
			dx = 1;
		} else if (isReachToRightEdge()) {
			dx = -1;
		}
	}

	private boolean isReachToRightEdge() {
		return x > right - width;
	}

	private boolean isReachToLeftEdge() {
		return x < left;
	}

	private boolean isReachToTop() {
		return top >= y;
	}

	public boolean isReachToButtom() {
		return bottom <= y;
	}

	/**
	 * このオブジェクトとpaddleの衝突処理をする．
	 * 
	 * @param paddle
	 * @return
	 */

	public void interact(Paddle paddle) {
		if (isHit(paddle)) {
			dy = -1;
		}
	}

	private boolean isHit(Paddle paddle) {
		return paddle.x <= this.x && this.x <= paddle.x + paddle.width
				&& paddle.y <= this.y && this.y < paddle.y + paddle.height;
	}

	/**
	 * 加速度に応じてボールの位置を計算する．
	 */
	public void move() {
		x += dx;
		y += dy;
	}

	/**
	 * ボールを描画する．
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setColor(this.color);
		g.fillOval(x, y, width, height);
	}

}