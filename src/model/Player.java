package model;

public class Player {
    private int score;
    private int crew;

    public Player() {
        this.score = 0;
        this.crew = 3;
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



}
