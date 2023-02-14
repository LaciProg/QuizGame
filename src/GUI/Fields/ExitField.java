package GUI.Fields;

import GUI.Table;
import Logic.Board;
import Logic.Player;
import Logic.Turn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ExitField extends Field {

    Table table;

    public ExitField(int x, int y, int w, int h, Integer n, int l, Color c, ArrayList<Player> players, Table table){

        super(x, y, w, h, n, l, c , players, table);
        this.setText("EXIT");
        this.setForeground(Color.BLACK);
        this.setFont(new Font("MV BOLI", Font.BOLD, 20));
        this.setOpaque(true);
        this.setHorizontalAlignment(JLabel.CENTER);

        this.table = table;

    }


    @Override
    public void action(Board board){
        setColor(players.get(board.getCurrentPlayer()).getColor());
        if(board.getPlayerList().get(board.getCurrentPlayer()).isKeyObtained()){
            JOptionPane.showMessageDialog(null, "Teljesítetted a szintet!");
            board.getPlayerList().get(board.getCurrentPlayer()).keyObtained(false);
            board.setTurn(Turn.LEVELUP);
        }
        else{
            board.setTurn(Turn.NEXT);
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        table.setInfo("<html>"+this.level+" szint<br/>"+" vége<html/>");
    }

}
