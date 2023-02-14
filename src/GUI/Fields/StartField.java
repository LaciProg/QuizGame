package GUI.Fields;

import GUI.Table;
import Logic.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class StartField extends Field {

    Table table;

    public StartField(int x, int y, int w, int h, int n, int l, Color c, ArrayList<Player> players, Table table){

        super(x, y, w, h, n, l, c , players, table);


        this.setText("START");
        this.setForeground(Color.BLACK);
        this.setBackground(c);
        this.setOpaque(true);
        this.setFont(new Font("MV BOLI", Font.BOLD, 25));
        this.setHorizontalAlignment(JLabel.CENTER);

        this.table = table;

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        table.setInfo("Kezdő mező");
    }

}
