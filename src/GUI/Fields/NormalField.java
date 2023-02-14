package GUI.Fields;

import GUI.Table;
import Logic.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class NormalField extends Field {

    Table table;

    public NormalField(int x, int y, int w, int h, Integer n, int l, Color c, ArrayList<Player> players, Table table){

        super(x, y, w, h, n, l, c , players, table);

        this.setText(n.toString());
        this.setForeground(Color.BLACK);
        this.setFont(new Font("MV BOLI", Font.BOLD, 25));
        this.setOpaque(true);
        this.setHorizontalAlignment(JLabel.CENTER);

        this.table = table;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        table.setInfo("<html>"+this.level+" szint,<br/>"+this.fieldNumber+" mező<html/>");
    }

}
