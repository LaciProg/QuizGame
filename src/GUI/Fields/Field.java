package GUI.Fields;


//import Game.Player;

import GUI.Table;
import Logic.Board;
import Logic.Player;
import Logic.Turn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Field extends JLabel implements MouseListener, Serializable {

    int fieldNumber;
    int level;
    Color color;
    ArrayList<Player> players =new ArrayList<>();
    Table table;

    protected Field(int x, int y, int w, int h, int n, int l, Color c, ArrayList<Player> players, Table table){
        this.addMouseListener(this);
        this.setBounds(x, y, w, h);
        this.setBackground(c);
        fieldNumber=n;
        level=l;
        this.players.addAll(players);
        color=c;
        this.setOpaque(true);
        this.table = table;
    }

    public void action(Board board) {

        setColor(players.get(board.getCurrentPlayer()).getColor());
        board.setTurn(Turn.NEXT);

    }

    public void setDefault(){
        this.setBackground(color);
    }
    public void setPlayerOnField(Player p){
        this.setBackground(p.getColor());
    }

    public int getField(){
        return fieldNumber;
    }


    @Override
    public void mousePressed(MouseEvent e) {
        Board.field = this.fieldNumber;
        Board.level = this.level;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        table.setInfo("");
    }

    public void setColor(Color c){
        this.setBackground(c);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
}
