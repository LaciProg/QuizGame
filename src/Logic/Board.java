package Logic;

import GUI.Fields.Field;
import GUI.Table;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static java.lang.Math.abs;



@SuppressWarnings("StatementWithEmptyBody")
public class Board implements Serializable {
    private volatile int roll=0;
    public static volatile int level = 0;
    public static volatile int field = 0;
    private volatile boolean extraMove = false;
    private volatile boolean extraMovePlus = false;
    private volatile boolean endGame = false;
    public void setExtraMovePlus(boolean b) {
       extraMovePlus = b;
    }
    public boolean getEndGame() {
        return endGame;
    }
    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }
    public void setExtraMove(boolean extraMove) { this.extraMove = extraMove; }

    private Turn turn = Turn.NO;

    public void setTurn(Turn turn) { this.turn = turn; }

    private final ArrayList<Player> finishedPlayers = new ArrayList<>();
    public ArrayList<Player> getFinishedPlayers() { return finishedPlayers; }
    private final ArrayList<Player> players = new ArrayList<>();

    public ArrayList<Player> getPlayerList(){ return players; }
    private final ArrayList<ArrayList<Field>> fields = new ArrayList<>();
    private final Table table;
    private int currentPlayer = 0;
    public void setRoll(int roll) {
        this.roll = roll;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    private final ArrayList<ArrayList<String>> questions = new ArrayList<>();

    private final ArrayList<File> pictures = new ArrayList<>();
    public ArrayList<File> getPictures() {
        return pictures;
    }
    public void reshufflePictures(){
        pictures.addAll(usedPictures);
        Collections.shuffle(pictures);
        usedPictures.clear();
    }
    private final ArrayList<File> usedPictures = new ArrayList<>();

    public void reshuffleQuestions(int difficulty){
        questions.get(difficulty-1).addAll(usedQuestions.get(difficulty-1));
        Collections.shuffle(questions.get(difficulty-1));
        usedQuestions.get(difficulty-1).clear();
    }

    public ArrayList<ArrayList<String>> getQuestions() {
        return questions;
    }
    private final ArrayList<ArrayList<String>> usedQuestions = new ArrayList<>();
    {
        usedQuestions.add(new ArrayList<>());
        usedQuestions.add(new ArrayList<>());
        usedQuestions.add(new ArrayList<>());
    }
    public void addUsedQuestion(String question, int difficulty){
        usedQuestions.get(difficulty-1).add(question);
    }
    public void addUsedPicture(File picture){
        usedPictures.add(picture);
    }

    public Board(ArrayList<Player> players, ArrayList<ArrayList<String>> questions, ArrayList<File> pictures) {
        this.players.addAll(players);
        table = new Table(fields, players, this);
        this.questions.addAll(questions);
        this.pictures.addAll(pictures);
    }


    public int roll() {
        roll = (int) (Math.random() * 6) + 1;
        return roll;
    }
    public void next() {
        if (currentPlayer == players.size() - 1) {
            currentPlayer = 0;
        }
        else {
            currentPlayer++;
        }

    }

    synchronized public void move() throws InterruptedException {

        while (players.get(currentPlayer).getFinished()) {next();}
        playerMoveHandle();
        while (turn == Turn.NO) {}
        if (turn == Turn.NEXT){nextTurn();}
        else if(turn == Turn.LEVELUP){
            players.get(currentPlayer).setLevel(players.get(currentPlayer).getLevel()+1);
            players.get(currentPlayer).setField(1);
            nextTurn();
        }
        else if(turn == Turn.END){}

        turn = Turn.NO;
        try {
            saveData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void playerMoveHandle() throws InterruptedException {
        moveInit();
        diceRoll();
        playerMove();
        setChanges();
    }

    private void nextTurn() {
        if(players.get(currentPlayer).equals(players.get(players.size()-1))){
            inGameScoreBoard();
        }
        next();
    }

    private void setChanges() throws InterruptedException {
        fields.get(level-1).get(field-1).action(this);

        fields.get(players.get(currentPlayer).getLevel()-1).get(players.get(currentPlayer).getField()-1).setDefault();
        players.get(currentPlayer).setField(field);
        players.get(currentPlayer).setLevel(level);
        wait(1000);
        fields.get(players.get(currentPlayer).getLevel()-1).get(players.get(currentPlayer).getField()-1).setDefault();
    }

    private void moveInit() {
        table.defaultInstruction();
        table.setCurrentPlayer(players.get(currentPlayer).getName());
        fields.get(players.get(currentPlayer).getLevel() - 1).get(players.get(currentPlayer).getField() - 1).setPlayerOnField(players.get(currentPlayer));
        level = field = 0;
    }

    private void playerMove() {
        players.get(currentPlayer).setMovement(roll);
        if (!extraMove || extraMovePlus) {
            while (!(players.get(currentPlayer).getLevel() == level) || !(abs(players.get(currentPlayer).getField() - field) == players.get(currentPlayer).getMovement())) {  }
        }
        else{
            while (!(players.get(currentPlayer).getLevel() == level) || !(field-(players.get(currentPlayer).getField()) == players.get(currentPlayer).getMovement())) {  }
        }
        extraMove = extraMovePlus = false;
        players.get(currentPlayer).setMovement(0);
    }

    private void diceRoll() {
        if (!extraMove) {
            roll = 0;
            table.rollEnabled();
            while (roll == 0) {
                Thread.onSpinWait();
            }
        }
    }

    private void saveData() throws IOException {
        FileWriter fileWriter = new FileWriter("AutoSave.txt");
        for (int i = 0; i != players.size(); i++) {
            fileWriter.write(players.get(i).toString());
        }
        fileWriter.close();
    }

    private void inGameScoreBoard(){
        String message = "";
        ArrayList<Player> sortedPlayers = new ArrayList<>(players);
        Comparator<Player> comparator = new LevelComparator();
        sortedPlayers.sort(comparator);
        Collections.reverse(sortedPlayers);
        if(!players.isEmpty())
            message ="<html>" + sortedPlayers.get(0).getName() + " " + sortedPlayers.get(0).getLevel() + " " + sortedPlayers.get(0).getField()+ "<br>";
        if(players.size()>1)
            message += sortedPlayers.get(1).getName() + " " + sortedPlayers.get(1).getLevel() + " " + sortedPlayers.get(1).getField()+ "<br>";
        if(players.size()>2)
            message += sortedPlayers.get(2).getName() + " " + sortedPlayers.get(2).getLevel() + " " + sortedPlayers.get(2).getField();
        message += "</html>";
        JOptionPane.showMessageDialog(null, message, "Top 3", JOptionPane.INFORMATION_MESSAGE);
    }


}
