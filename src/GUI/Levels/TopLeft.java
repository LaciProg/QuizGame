package GUI.Levels;

import GUI.Fields.*;
import GUI.Table;
import Logic.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;


public class TopLeft extends JPanel {
    NormalField field1;
    NormalField field2;
    NormalField field3;
    KeyField keyField1;
    KeyField keyField2;
    KeyField keyField3;
    KeyField keyField4;
    DiceField diceField1;
    DiceField diceField2;
    QuestionField questionField1;
    QuestionField questionField2;
    QuestionField questionField3;
    PictureField pictureField;
    StartField startField;
    ExitField exitField;
    public TopLeft(ArrayList<ArrayList<Field>> fields, ArrayList<Player> players, Table table) {
        setLayout(null);
        setFields(players, table);
        setInfo();
        addFields(fields);
        setBackground();
    }

    private void setFields(ArrayList<Player> players, Table table) {
        setNormalFields(players, table);
        setKeyFields(players, table);
        randomizeKeys(players);
        setDiceFields(players, table);
        setQuestionFields(players, table);
        setPictureFields(players, table);
        setStartField(players, table);
        setExitField(players, table);
    }

    private void setBackground() {
        JLabel backGround=new JLabel();
        backGround.setIcon(new ImageIcon("Resources/TopLeft.png"));
        backGround.setHorizontalAlignment(SwingConstants.CENTER);
        backGround.setVerticalAlignment(SwingConstants.CENTER);
        backGround.setBounds(0,0,770,400);
        add(backGround);
    }

    private void addFields(ArrayList<ArrayList<Field>> fields) {
        ArrayList<Field> f =new ArrayList<>();
        f.add(startField);
        f.add(diceField1);
        f.add(field1);
        f.add(questionField1);
        f.add(keyField1);
        f.add(field2);
        f.add(questionField2);
        f.add(keyField2);
        f.add(pictureField);
        f.add(keyField3);
        f.add(field3);
        f.add(diceField2);
        f.add(questionField3);
        f.add(keyField4);
        f.add(exitField);
        fields.add(f);
    }

    private void setInfo() {
        JLabel info = new JLabel();
        info.setBackground(Color.WHITE);
        info.setForeground(Color.BLACK);
        info.setText("Mozdony");
        info.setBounds(10,10,160, 30);
        info.setFont(new Font("Arial", Font.BOLD, 30));
        this.add(info);
    }

    private void setExitField(ArrayList<Player> players, Table table) {
        exitField = new ExitField(230, 160, 50, 50, 15, 1, Color.WHITE, players, table);
        this.add(exitField);
    }

    private void setStartField(ArrayList<Player> players, Table table) {
        startField = new StartField(20, 55, 100, 100, 1, 1, Color.WHITE, players, table);
        this.add(startField);
    }

    private void setPictureFields(ArrayList<Player> players, Table table) {
        pictureField = new PictureField(670, 170, 50, 50, 9, 1, Color.WHITE, players, table);
        this.add(pictureField);
    }

    private void setQuestionFields(ArrayList<Player> players, Table table) {
        questionField1= new QuestionField(160, 295, 50, 50, 4,1, Color.WHITE, players, table, 1);
        questionField2= new QuestionField(480, 250, 50, 50, 7,1, Color.WHITE, players, table, 1);
        questionField3= new QuestionField(400, 25, 50, 50, 13,1, Color.WHITE, players, table, 1);
        this.add(questionField1);
        this.add(questionField2);
        this.add(questionField3);
    }

    private void setDiceFields(ArrayList<Player> players, Table table) {
        diceField1 = new DiceField(45, 190, 50, 50, 2,1, Color.WHITE, players, table);
        diceField2 = new DiceField(500, 20, 50, 50, 12,1, Color.WHITE, players, table);
        this.add(diceField1);
        this.add(diceField2);
    }

    private void randomizeKeys(ArrayList<Player> players) {
        ArrayList<Integer> keys = new ArrayList<>();
        for (int i = 0; i< players.size(); i++){
            keys.add(i);
        }
        Collections.shuffle(keys);
        for (int i = 0; i< players.size(); i++){
            if(i%4==0) keyField1.setKeys(keys.get(i));
            else if (i%4==1) keyField2.setKeys(keys.get(i));
            else if (i%4==2) keyField3.setKeys(keys.get(i));
            else keyField4.setKeys(keys.get(i));
        }
    }

    private void setKeyFields(ArrayList<Player> players, Table table) {
        keyField1 = new KeyField(270, 260, 50, 50, 5,1, Color.WHITE, players, table);
        keyField2 = new KeyField(600, 235, 50, 50, 8,1, Color.WHITE, players, table);
        keyField3 = new KeyField(680, 90, 50, 50, 10,1, Color.WHITE, players, table);
        keyField4 = new KeyField(300, 70, 50, 50, 14,1, Color.WHITE, players, table);
        this.add(keyField1);
        this.add(keyField2);
        this.add(keyField3);
        this.add(keyField4);
    }

    private void setNormalFields(ArrayList<Player> players, Table table) {
        field1 = new NormalField(50, 280, 50, 50, 3,1, Color.WHITE, players, table);
        field2 = new NormalField(380, 220, 50, 50, 6,1, Color.WHITE, players, table);
        field3 = new NormalField(600, 40, 50, 50, 11,1, Color.WHITE, players, table);
        this.add(field1);
        this.add(field2);
        this.add(field3);
    }


}
