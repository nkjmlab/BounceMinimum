package org.nkjmlab.game.bounce;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;

/**
 * ボールをパドルで打ち返すゲームBounce.
 * 
 * @author nkjm
 * 
 */
public class Bounce extends JFrame {

	private Ball ball;
	private Paddle paddle;

	// ゲームの進行を調整するための待ち時間(ms)
	private static final long INTERVAL = 8;

	public static void main(String[] args) throws InterruptedException {
		// ゲームの初期化をする.
		Bounce bounce = new Bounce(640, 480);
		// ゲームを開始する.
		bounce.start();
	}

	public Bounce(int rightEdge, int bottomEdge) {
		// ボールとパドルを作る．
		this.ball = new Ball(10, 10, rightEdge, bottomEdge);
		this.paddle = new Paddle(100, 10, rightEdge, bottomEdge);

		// paddleにキーボード入力のイベントが流れ込むように登録する．
		// この処理により，キーボードでボタンが押されたとき，
		// paddleのkeyPressed(KeyEvent e)メソッドが呼び出されるようになる．
		addKeyListener(paddle);

		// 画面の設定のおまじない
		setPreferredSize(new Dimension(rightEdge, bottomEdge));
		pack();
		setVisible(true);
	}

	private void start() throws InterruptedException {
		while (true) {
			// ボールとパドルの処理をする.
			ball.accelerate();
			ball.interact(paddle);
			ball.move();
			if (ball.isReachToButtom()) {
				System.out.println("Game Over!");
				break;
			}

			// 描画処理をする．画面をクリアしてから，ボールとパドルを書く．
			Graphics g = getContentPane().getGraphics();
			g.clearRect(0, 0, getWidth(), getHeight());
			ball.draw(g);
			paddle.draw(g);

			// ゲームの進行が早くなりすぎないように待つ．
			Thread.sleep(INTERVAL);
		}
	}
}