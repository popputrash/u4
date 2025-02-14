package model;

/**
 * Klass för att hantera olika spelare
 * @author Maximilian Andersen & Elias Bränsström
 */
public class Player {
    private int score;
    private int crew;
    private boolean randomTurn;
    private int turns;

    /**
     * Konstruktor, sätter upp initala värden
     */
    public Player() {
        this.score = 0;
        this.crew = 3;
        this.randomTurn = false;
        this.turns = 0;
    }

    /**
     * Getter för score
     * @return score
     * @author Maximilian Andersen & Elias Bränsström
     */
    public int getScore() {
        return score;
    }

    /**
     * Subtrahera score med i
     * @param i
     * @author Maximilian Andersen & Elias Bränsström
     */
    public void decScore(int i){
        score -= i;
        if(score < 0){
            score = 0;
        }
    }

    /**
     * Setter för score
     * @param score
     * @author Maximilian Andersen & Elias Bränsström
     */
    public void setScore(int score){
        this.score = score;
    }

    /**
     * Addera score
     * @param i
     * @author Maximilian Andersen & Elias Bränsström
     */
    public void addScore(int i) {
        score+= i;
    }

    /**
     * Getter för crew
     * @return
     * @author Maximilian Andersen & Elias Bränsström
     */
    public int getCrew() {
        return crew;
    }

    /**
     * Subtrahera crew
     * @author Maximilian Andersen & Elias Bränsström
     */
    public void decCrew(){crew--;}

    /**
     * Add crew
     * @author Maximilian Andersen & Elias Bränsström
     */
    public void addCrew() {
        this.crew++;
    }

    /**
     * Setter för random turn
     * @param randomTurn
     * @author Maximilian Andersen & Elias Bränsström
     */
    public void setRandomTurn(boolean randomTurn) {
        this.randomTurn = randomTurn;
    }

    /**
     * Getter för randomTurn
     * @return
     * @author Maximilian Andersen & Elias Bränsström
     */
    public boolean isRandomTurn() {
        return randomTurn;
    }

    /**
     * Getter för antal turns
     * @return
     */
    public int getTurns() {
        return turns;
    }

    public void addTurns(int turns) {
        this.turns += turns;
    }

    /**
     * setter för turns
     * @param turns
     * @author Maximilian Andersen & Elias Bränsström
     */
    public void setTurns(int turns) {
        this.turns = turns;
    }
}
