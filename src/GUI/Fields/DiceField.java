package GUI.Fields;


import GUI.Table;
import Logic.Board;
import Logic.Player;
import Logic.Turn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class DiceField extends Field {

    ImageIcon dice = new ImageIcon("Resources/dice.png");
    Table table;

    public DiceField(int x, int y, int w, int h, Integer n, int l, Color c, ArrayList<Player> players, Table table){


        super(x, y, w, h, n, l, c , players, table);
        this.setIcon(dice);
        this.setBounds(x, y, w, h);
        this.setText(n.toString());
        this.setForeground(Color.BLACK);
        this.setFont(new Font("MV BOLI", Font.BOLD, 25));
        this.table = table;

        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
    }

    @Override
    public void action(Board board){
        setColor(players.get(board.getCurrentPlayer()).getColor());
        this.setIcon(null);

        //board.setExtraRoll(true);
        board.setTurn(Turn.STAY);

    }
     @Override
     public void setDefault(){
         this.setIcon(dice);
     }

     @Override
     public synchronized void setPlayerOnField(Player p){
         this.setIcon(null);
         this.setBackground(p.getColor());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        table.setInfo("+1 dobás");
    }

}
