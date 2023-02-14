package Logic;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    private final String name;
    private final Color color;
    private int level;
    private int field;
    private int movement;
    private boolean key=false;
    private boolean finished = false;
    public boolean getFinished() {
        return finished;
    }
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    private final ArrayList<Integer> questions = new ArrayList<>();
    {
        for (int i = 0; i < 4; i++) {
            questions.add(0);
        }
    }
    private final ArrayList<Integer> answers = new ArrayList<>();
    {
        for (int i = 0; i < 4; i++) {
            answers.add(0);
        }
    }

    @Override
    public String toString() {
        return "Játékos{" +
                "\nNév='" + name +
                "\nHely: " + level + ". szint " + field+ ". mező" +
                "\nKönnyű kérdések=" + questions.get(0) +
                "\nPontok=" + answers.get(0) +
                "\nKözepes kérdések=" + questions.get(1) +
                "\nPontok=" + answers.get(1) +
                "\nNehéz kérdések=" + questions.get(2) +
                "\nPontok=" + answers.get(2) +
                "\nKépek kérdések=" + questions.get(3) +
                "\nPontok=" + answers.get(3) +
                "\nSzumma: " + (answers.get(0) + answers.get(1) + answers.get(2) + answers.get(3))+
                "\n}\n";
    }

    public void addQuestionPoint(int difficulty) {
        questions.set(difficulty - 1, questions.get(difficulty - 1) + 1);
    }
    public void addAnswerPoint(int difficulty, int points) { answers.set(difficulty - 1, answers.get(difficulty-1)+ points); }

    public ArrayList<Integer> getQuestionPoints() {
        return questions;
    }
    public ArrayList<Integer> getAnswerPoints() {
        return answers;
    }

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
        this.level = 1;
        this.field = 1;
    }

    public Player(String name, Color color, int level, int field) {
        this.name = name;
        this.color = color;
        this.level = level;
        this.field = field;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public int getLevel() {
        return level;
    }

    public int getField() {
        return field;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setField(int field) {
        this.field = field;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public int getMovement() {
        return movement;
    }

    public void keyObtained(boolean key) {
        this.key = key;
    }

    public boolean isKeyObtained() {
        return key;
    }
}
