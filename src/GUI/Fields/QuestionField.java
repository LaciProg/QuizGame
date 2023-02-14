package GUI.Fields;

import GUI.Table;
import Logic.Board;
import Logic.Player;
import Logic.Turn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class QuestionField extends Field {

    Table table;
    int difficulty;


    public QuestionField(int x, int y, int w, int h, Integer n, int l, Color c, ArrayList<Player> players, Table table, int difficulty){

        super(x, y, w, h, n, l, c , players, table);
        switch (difficulty){
            case 1 -> this.setText("?");
            case 2 -> this.setText("??");
            case 3 -> this.setText("???");
        }

        this.setFont(new Font("MV BOLI", Font.BOLD, 20));
        this.setOpaque(true);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.difficulty = difficulty;
        this.table = table;
    }

    @Override
    public void action(Board board) {

        setColor(players.get(board.getCurrentPlayer()).getColor());

        if(board.getQuestions().get(difficulty-1).isEmpty()){
            board.reshuffleQuestions(difficulty);
        }

        String question = board.getQuestions().get(difficulty-1).get(0);
        board.addUsedQuestion(question, difficulty);
        board.getQuestions().get(difficulty-1).remove(0);

        String[] questionParts = question.split("@");
        ArrayList<String> answers = new ArrayList<>();
        if(difficulty!=3) answers = new ArrayList<>(Arrays.asList(questionParts).subList(1, questionParts.length - 1));
        Collections.shuffle(answers);
        int given = -1;
        boolean correctHard = false;


        while(given==-1){
            switch (difficulty) {
                case 1 -> given=
                       JOptionPane.showOptionDialog(
                               null,
                               questionParts[0],
                               "Könnyű kérdés",
                               JOptionPane.YES_NO_OPTION,
                               JOptionPane.QUESTION_MESSAGE,
                               null,
                               new String[]{answers.get(0), answers.get(1)},
                               "default");
                case 2 -> given=
                        JOptionPane.showOptionDialog(
                                null,
                                questionParts[0],
                                "Közepes kérdés",
                                JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                new String[]{answers.get(0), answers.get(1), answers.get(2)},
                                "default");
                case 3 -> {given=
                        JOptionPane.showOptionDialog(
                                null,
                                questionParts[0],
                                "Nehéz kérdés",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                new String[]{"Helyes válasz", "Helytelen válasz"},
                                "default");
                    if(given==0) correctHard = true;
                }
            }
        }
        board.getPlayerList().get(board.getCurrentPlayer()).addQuestionPoint(difficulty);

        boolean success;
        if (difficulty == 3) {
            success = correctHard;
        } else {
            success = answers.get(given).equals(questionParts[questionParts.length - 1]);
        }

        if(success) {
            JOptionPane.showMessageDialog(null, "Helyes válasz! Lépj előre egy mezőt!");
            board.getPlayerList().get(board.getCurrentPlayer()).addAnswerPoint(difficulty, difficulty);
            board.setTurn(Turn.STAY);
            board.setRoll(1);
            board.setExtraMove(true);
        }
        else {
            JOptionPane.showMessageDialog(null, "Rossz válasz! Nem léphetsz előre!");
            board.setTurn(Turn.NEXT);
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        table.setInfo("Kérdések");
    }

}
