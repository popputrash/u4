package model;

/**
 * kanske man bara gör en klass för varje spelare?
 */

public class ScoreItem {
    private int score;
    private String name;

    public ScoreItem(int score, String name){
        this.name = name;
        this.score = score;
    }
    public ScoreItem (int score){
        this(score," ");
    }
    public int getScore() {
        return score;
    }
    public String getName() {
        return name;
    }

    //Ändra till något fint string format?

    public String toString() {
        return "score: " + score + ", name:" + name;
    }
}
