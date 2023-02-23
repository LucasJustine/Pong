package fr.projet.pong;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.awt.RenderingHints;
import java.awt.BasicStroke;

public class Game extends JPanel implements Runnable{

    private Ball ball;
    private Thread animator;
    private Player player1;
    private Player player2;
    private BasicStroke stroke = new BasicStroke(10.f);

    public Game(){
        super();
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT));
        this.init();
        this.setFocusable(true);
        this.addKeyListener(new KeysListener(this));
    }
   
    private void init() {
        this.ball = new Ball(Constants.WINDOW_WIDTH /2, Constants.WINDOW_HEIGHT / 2, Constants.BALL_RADIUS);
        this.ball.setDx(randomNonZero());
        this.ball.setDy(randomNonZero());
        this.player1 = new Player((Constants.WINDOW_HEIGHT / 2) - 50);
        this.player2 = new Player((Constants.WINDOW_HEIGHT / 2) - 50);
    }

    public int randomNonZero() {
        int r = 0;
        Random rand = new Random();
        while (r == 0.0) {
            r = rand.nextInt(3) - 1 ;
        }
        return r;
    }

    @Override
    public void run() {
		double timePerFrame = 1000000000.0 / Constants.FPS_SET;
		double timePerUpdate = 1000000000.0 / Constants.UPS_SET;
		long previousTime = System.nanoTime();
		double deltaU = 0;
		double deltaF = 0;

		while (true) {
			long currentTime = System.nanoTime();

			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;

			previousTime = currentTime;

			if (deltaU >= 1) {
                this.update();
                this.ball.move();
                this.check();
				deltaU--;
			}

			if (deltaF >= 1) {
			    this.repaint();
				deltaF--;
			}
		}
    }   

    public void check() {
        this.checkPlayerColl();
        this.checkBallPlayerColl();
        this.checkBallColl();
    }

    private void checkBallColl() {
        if (this.ball.getY() <= 0 || this.ball.getY() + Constants.BALL_RADIUS >= this.getHeight())
            this.ball.setDy(-this.ball.getDy());
        this.checkPts();
    }

    private void checkPlayerColl() {
        if(this.player1.y <= 0) 
            this.player1.setY(0);
        if(this.player1.y >= this.getHeight() - player1.getHeight()) 
            this.player1.setY((int)(this.getHeight() - player1.getHeight()));
        if(this.player2.y <= 0) 
            this.player2.setY(0);
        if(this.player2.y >= this.getHeight() - player2.getHeight()) 
            this.player2.setY((int)(this.getHeight() - player2.getHeight()));
    }
   
    private void checkBallPlayerColl() {
        if(this.ball.getBall().intersects(this.player1)){
            this.ball.setDx((float) Math.abs(this.ball.getDx()));
            this.ball.speedUp(0.1f);
        }
        else if (this.ball.getBall().intersects(this.player2)){
            this.ball.setDx((float) -this.ball.getDx());
            this.ball.speedUp(0.1f);
        }
    }

    private void checkPts() {
        if(this.ball.getX() <= 0 || this.ball.getX() + Constants.BALL_RADIUS >= this.getWidth()) {
            if (this.ball.getX() <= 0) 
                this.player2.score();  
            else
                this.player1.score();  
            this.ball.resetSpeed();
            this.ball.setPosX(this.getWidth() / 2);
            this.ball.setPosY(this.getHeight() / 2); 
        }
        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setColor(Color.white);
        g2d.setStroke(stroke);
        g2d.drawLine(this.getWidth() /2, 0, this.getWidth() /2, this.getHeight());
        g2d.setFont(Constants.FONT);

        g2d.drawString( Integer.toString(this.player1.getScore()), 50, 50);
        g2d.drawString( Integer.toString(this.player2.getScore()), this.getWidth() - 50, 50);
        this.ball.draw(g2d);
        this.player1.draw(g2d, 0, Color.blue);
        this.player2.draw(g2d, this.getWidth() -  player2.width, Color.red);
        g.dispose();
    }

    private void update() {
        if(Constants.PLAYER1_UP && player1.getLastKey()  == Constants.Z_KEY)
            this.player1.update(-Constants.PLAYER_SPEED);
        else if(Constants.PLAYER1_DOWN && player1.getLastKey()  == Constants.S_KEY)
            this.player1.update(Constants.PLAYER_SPEED);
        if(Constants.PLAYER2_UP && player2.getLastKey()  == Constants.UP_KEY)
            this.player2.update(-Constants.PLAYER_SPEED);
        else if(Constants.PLAYER2_DOWN && player2.getLastKey() == Constants.DOWN_KEY)
            this.player2.update(Constants.PLAYER_SPEED);
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == Constants.Z_KEY) {
            Constants.PLAYER1_UP = true;
            this.player1.setLastKey(Constants.Z_KEY);
        }
        else if(e.getKeyCode() == Constants.S_KEY) {
            Constants.PLAYER1_DOWN = true;   
            this.player1.setLastKey(Constants.S_KEY);
        }
        if(e.getKeyCode() == Constants.UP_KEY) {
            Constants.PLAYER2_UP = true;
            this.player2.setLastKey(Constants.UP_KEY);
        }
        else if(e.getKeyCode() == Constants.DOWN_KEY) {
            Constants.PLAYER2_DOWN = true;
            this.player2.setLastKey(Constants.DOWN_KEY);
        }
            
    }

    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == Constants.Z_KEY) 
            Constants.PLAYER1_UP = false;
        if(e.getKeyCode() == Constants.S_KEY) 
            Constants.PLAYER1_DOWN = false;   
        if(e.getKeyCode() == Constants.UP_KEY) 
            Constants.PLAYER2_UP = false;
        if(e.getKeyCode() == Constants.DOWN_KEY) 
            Constants.PLAYER2_DOWN = false;
    }

    @Override
    public void addNotify() {
        super.addNotify();
        if (animator == null) {
            animator = new Thread(this);
            animator.start();
        }
    }

    @Override
    public void removeNotify() {
        super.removeNotify();
    }

}
