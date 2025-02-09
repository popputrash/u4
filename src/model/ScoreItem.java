package model;
/**
 * Klass för ScoreItem
 * Hanterar namn och poäng.
 * @author Maximilian Andersen & Elias Bränsström
 */

public class ScoreItem {
    private int score;
    private String name;

    /**
     * Konstruktor
     * @param score
     * @param name
     * @author Maximilian Andersen & Elias Bränsström
     */
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
        return name + " " + score;
    }
}
