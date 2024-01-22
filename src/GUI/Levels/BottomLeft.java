package GUI.Levels;

import GUI.Fields.*;
import GUI.Table;
import Logic.Player;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class BottomLeft extends JPanel {

    NormalField field1;
    KeyField keyField1;
    KeyField keyField2;
    KeyField keyField3;
    KeyField keyField4;
    ExtraMoveField extraMoveField;
    QuestionField questionField1;
    QuestionField questionField2;
    QuestionField questionField3;
    PictureField pictureField;
    ContinueField continueField;
    FinishField finishField;

    public BottomLeft(ArrayList<ArrayList<Field>> fields, ArrayList<Player> players, Table table) {
        setLayout(null);
        setFields(players, table);
        setInfo();
        addFields(fields);
        setBackground();
    }

    private void setBackground() {
        JLabel backGround=new JLabel();
        backGround.setIcon(new ImageIcon("Resources/BottomLeft.png"));
        backGround.setHorizontalAlignment(SwingConstants.CENTER);
        backGround.setVerticalAlignment(SwingConstants.CENTER);
        backGround.setBounds(0,0,770,390);
        add(backGround);
    }

    private void setInfo() {
        JLabel info = new JLabel();
        info.setForeground(Color.WHITE);
        info.setText("Indulás");
        info.setBounds(10,10,170, 30);
        info.setFont(new Font("Arial", Font.BOLD, 30));
        this.add(info);
    }

    private void addFields(ArrayList<ArrayList<Field>> fields) {
        ArrayList<Field> f =new ArrayList<>();
        f.add(continueField);
        f.add(questionField1);
        f.add(keyField1);
        f.add(keyField2);
        f.add(questionField2);
        f.add(extraMoveField);
        f.add(pictureField);
        f.add(keyField3);
        f.add(field1);
        f.add(keyField4);
        f.add(questionField3);
        f.add(finishField);
        fields.add(f);
    }

    private void setFields(ArrayList<Player> players, Table table) {
        setNormalFields(players, table);
        setKeyFields(players, table);
        randomizeKeys(players);
        setExtraMoveFields(players, table);
        setQuestionFields(players, table);
        setPictureFields(players, table);
        setContinueField(players, table);
        setFinishField(players, table);
    }

    private void setFinishField(ArrayList<Player> players, Table table) {
        finishField = new FinishField(290, 280, 70, 70, 12, 4, Color.WHITE, players, table);
        this.add(finishField);
    }

    private void setContinueField(ArrayList<Player> players, Table table) {
        continueField = new ContinueField(620, 12, 100, 100, 1, 4, Color.WHITE, players, table);
        this.add(continueField);
    }

    private void setPictureFields(ArrayList<Player> players, Table table) {
        pictureField = new PictureField(80, 80, 50, 50, 7, 4, Color.WHITE, players, table);
        this.add(pictureField);
    }

    private void setQuestionFields(ArrayList<Player> players, Table table) {
        questionField1= new QuestionField(550, 50, 50, 50, 2,4, Color.WHITE, players, table, 3);
        questionField2= new QuestionField(300, 40, 50, 50, 5,4, Color.WHITE, players, table, 3);
        questionField3= new QuestionField(200, 310, 50, 50, 11,4, Color.WHITE, players, table, 3);
        this.add(questionField1);
        this.add(questionField2);
        this.add(questionField3);
    }

    private void setExtraMoveFields(ArrayList<Player> players, Table table) {
        extraMoveField = new ExtraMoveField(180, 30, 50, 50, 6,4, Color.WHITE, players, table);
        this.add(extraMoveField);
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
        keyField1 = new KeyField(460, 40, 50, 50, 3,4, Color.WHITE, players, table);
        keyField2 = new KeyField(380, 70, 50, 50, 4,4, Color.WHITE, players, table);
        keyField3 = new KeyField(40, 160, 50, 50, 8,4, Color.WHITE, players, table);
        keyField4 = new KeyField(110, 300, 50, 50, 10,4, Color.WHITE, players, table);
        this.add(keyField1);
        this.add(keyField2);
        this.add(keyField3);
        this.add(keyField4);
    }

    private void setNormalFields(ArrayList<Player> players, Table table) {
        field1 = new NormalField(70, 230, 50, 50, 9,4, Color.WHITE, players, table);
        this.add(field1);
    }
}

