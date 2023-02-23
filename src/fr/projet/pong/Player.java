package fr.projet.pong;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;

public class Player extends Rectangle {


    private int lastKey;
    private Score score;

    public Player (int positionY) {
        this.y = positionY;
        this.width = Constants.PLAYER_WIDTH;
        this.height = Constants.PLAYER_HEIGHT;
        this.score = new Score();
    }

    public void update(int direction) {
        this.y += direction;
    }
   
    public void draw(Graphics2D g2d, int x, Color color) {
        this.x = x;
        g2d.setColor(color);
        g2d.fillRect(x, this.y, this.width, this.height);
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setLastKey(int downKey) {
        this.lastKey = downKey;
    }

    public int getLastKey() {
        return lastKey;
    }

    public void score() {
        this.score.increase();
    }

    public int getScore() {
        return score.getScore();
    }
}