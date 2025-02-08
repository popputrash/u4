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
    public void decScore(int i){score -= i;}
    public void setScore(int score){
        this.score = score;
    }
    public void addScore(int i) {
        score+= i;
    }

    public int getCrew() {
        return crew;
    }
    public void decCrew(){crew--;}
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
