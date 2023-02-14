package GUI.Levels;

import GUI.Fields.*;
import GUI.Table;
import Logic.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class BottomRight extends JPanel {

    NormalField field1;
    NormalField field2;
    NormalField field3;
    KeyField keyField1;
    KeyField keyField2;
    KeyField keyField3;
    KeyField keyField4;
    DiceField diceField1;
    ExtraMoveField extraMoveField;
    QuestionField questionField1;
    QuestionField questionField2;
    QuestionField questionField3;
    PictureField pictureField;
    ContinueField continueField;
    ExitField exitField;

    public BottomRight(ArrayList<ArrayList<Field>> fields, ArrayList<Player> players, Table table) {
        setLayout(null);

        //Color LIGHTBLUE=new Color(0xadd8e6);

        field1 = new NormalField(665, 70, 50, 50, 4,3, Color.WHITE, players, table);
        field2 = new NormalField(380, 40, 50, 50, 7,3, Color.WHITE, players, table);
        field3 = new NormalField(90, 180, 50, 50, 14,3, Color.WHITE, players, table);
        this.add(field1);
        this.add(field2);
        this.add(field3);

        keyField1 = new KeyField(600, 200, 50, 50, 2,3, Color.WHITE, players, table);
        keyField2 = new KeyField(590, 20, 50, 50, 5,3, Color.WHITE, players, table);
        keyField3 = new KeyField(220, 320, 50, 50, 11,3, Color.WHITE, players, table);
        keyField4 = new KeyField(30, 240, 50, 50, 13,3, Color.WHITE, players, table);
        this.add(keyField1);
        this.add(keyField2);
        this.add(keyField3);
        this.add(keyField4);

        ArrayList<Integer> keys = new ArrayList<>();
        for (int i=0; i<players.size(); i++){
            keys.add(i);
        }
        Collections.shuffle(keys);
        for (int i=0; i<players.size(); i++){
            if(i%4==0) keyField1.setKeys(keys.get(i));
            else if (i%4==1) keyField2.setKeys(keys.get(i));
            else if (i%4==2) keyField3.setKeys(keys.get(i));
            else keyField4.setKeys(keys.get(i));

        }



        diceField1 = new DiceField(370, 190, 50, 50, 9,3, Color.WHITE, players, table);
        extraMoveField = new ExtraMoveField(360, 120, 50, 50, 8,3, Color.WHITE, players, table);
        this.add(diceField1);
        this.add(extraMoveField);

        questionField1= new QuestionField(590, 130, 50, 50, 3,3, Color.WHITE, players, table, 2);
        questionField2= new QuestionField(480, 12, 50, 50, 6,3, Color.WHITE, players, table, 2);
        questionField3= new QuestionField(110, 320, 50, 50, 12,3, Color.WHITE, players, table, 3);
        this.add(questionField1);
        this.add(questionField2);
        this.add(questionField3);

        pictureField = new PictureField(300, 260, 50, 50, 10, 3, Color.WHITE, players, table);
        this.add(pictureField);

        continueField = new ContinueField(620, 270, 100, 100, 1, 3, Color.WHITE, players, table);
        this.add(continueField);

        exitField = new ExitField(160, 150, 50, 50, 15, 3, Color.WHITE, players, table);
        this.add(exitField);

        JLabel info = new JLabel();
        info.setForeground(Color.BLACK);
        info.setText("Jelző");
        info.setBounds(10,10,160, 30);
        info.setFont(new Font("Arial", Font.BOLD, 30));
        this.add(info);


        ArrayList<Field> f =new ArrayList<>();

        f.add(continueField);
        f.add(keyField1);
        f.add(questionField1);
        f.add(field1);
        f.add(keyField2);
        f.add(questionField2);
        f.add(field2);
        f.add(extraMoveField);
        f.add(diceField1);
        f.add(pictureField);
        f.add(keyField3);
        f.add(questionField3);
        f.add(keyField4);
        f.add(field3);
        f.add(exitField);

        fields.add(f);

        JLabel backGround=new JLabel();
        backGround.setIcon(new ImageIcon("Resources/BottomRight.png"));
        backGround.setHorizontalAlignment(SwingConstants.CENTER);
        backGround.setVerticalAlignment(SwingConstants.CENTER);

        backGround.setBounds(0,0,770,400);
        add(backGround);
    }
}
