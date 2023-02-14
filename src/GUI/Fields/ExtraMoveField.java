package GUI.Fields;

import GUI.Table;
import Logic.Board;
import Logic.Player;
import Logic.Turn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ExtraMoveField extends Field{

    Table table;
    public ExtraMoveField(int x, int y, int w, int h, Integer n, int l, Color c, ArrayList<Player> players, Table table){

        super(x, y, w, h, n, l, c , players, table);
        this.setText("+-2");
        this.setForeground(Color.BLACK);
        this.setFont(new Font("MV BOLI", Font.BOLD, 20));
        this.setHorizontalAlignment(JLabel.CENTER);

        this.table = table;

    }

    @Override
    public void action(Board board){

        this.setColor(players.get(board.getCurrentPlayer()).getColor());
        board.setExtraMovePlus(true);
        board.setExtraMove(true);
        board.setTurn(Turn.STAY);
        board.setRoll(2);

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        table.setInfo("+2 lépés");
    }

}
