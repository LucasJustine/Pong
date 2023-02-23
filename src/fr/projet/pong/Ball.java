package fr.project.pong;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {
    
    private float dx; // déplacement horizontal
    private float dy; // déplacement vertical
    private float speed;
    private float initialSpeed = 1f;
    private float posX;
    private float posY; 
    private int radius; // rayon de la balle
    private int width;
    private int height;
    private Rectangle ball;

    public Ball(int posX, int posY, int radius) {
        this.posX = posX;
        this.posY = posY;
        resetSpeed();
        this.ball = new Rectangle(posX, posY, radius, radius);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public float getDx() {
        return dx;
    }

    public float getDy() {
        return dy;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public void setDx(float d) {
        this.dx = d;
    }

    public void speedUp(float speed) {
        this.speed += speed;
    }

    public void move() {
        this.posX += dx * speed;
        this.posY += dy * speed;
        this.ball.x = (int)posX ;
        this.ball.y = (int)posY ;
    }

    public void setPosX(int x) {
        this.posX = x;
        this.ball.x = x;
    }

    public void setPosY(int y) {
        this.posY = y;
        this.ball.y = y;
    }

    public void coll() {
        this.dx = -dx;
    }
    public void draw(Graphics2D g2d) {
        g2d.fillOval((int)posX,(int)posY,this.radius, this.radius);
    }

    public Rectangle getBall() {
        return ball;
    }

    public float getX() {
        return posX;
    }

    public float getY() {
        return posY;
    }
    
    public void resetSpeed() {
        this.speed = initialSpeed;
    }
}
