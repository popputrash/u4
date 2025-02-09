package model;

public class Player {
    private int score;
    private int crew;
    private boolean randomTurn;
    private int turns;

    public Player() {
        this.score = 0;
        this.crew = 3;
        this.randomTurn = false;
        this.turns = 0;
    }

    public int getScore() {
        return score;
    }
    public void decScore(int i){
        score -= i;
        if(score < 0){
            score = 0;
        }
    }
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
    public void addCrew() {
        this.crew++;
    }

    public void setRandomTurn(boolean randomTurn) {
        this.randomTurn = randomTurn;
    }

    public boolean isRandomTurn() {
        return randomTurn;
    }

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }
}
