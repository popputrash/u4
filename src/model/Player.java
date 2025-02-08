package model;

public class Player {
    private int score;
    private int crew;
    private boolean randomTurn;

    public Player() {
        this.score = 0;
        this.crew = 3;
        this.randomTurn = false;
    }

    public int getScore() {
        return score;
    }
    public void addScore() {
        score++;
    }

    public int getCrew() {
        return crew;
    }

    public void setCrew(int crew) {
        this.crew = crew;
    }

    public void setRandomTurn(boolean randomTurn) {
        this.randomTurn = randomTurn;
    }

    public boolean isRandomTurn() {
        return randomTurn;
    }
}
