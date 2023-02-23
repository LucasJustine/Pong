package fr.projet.pong;

public class Score {
    
    private int score;

    public Score() {
        this.score = 0;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void increase() {
        this.score++;
    } 
}
