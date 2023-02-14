package GUI.Fields;

import GUI.Table;
import Logic.Board;
import Logic.Player;
import Logic.Turn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class KeyField extends Field {

    ImageIcon key = new ImageIcon("Resources/key.png");
    ArrayList<Player> players = new ArrayList<>();
    ArrayList<Integer> keys = new ArrayList<>();

    private final Table table;
    private volatile boolean visited = false;
    public KeyField(int x, int y, int w, int h, Integer n, int l, Color c, ArrayList<Player> players, Table table){

        super(x, y, w, h, n, l, c , players, table);
        this.setIcon(key);

        this.setText(n.toString());
        this.setForeground(Color.BLACK);
        this.setFont(new Font("MV BOLI", Font.BOLD, 25));
        this.setHorizontalTextPosition(JLabel.CENTER);

        this.setHorizontalAlignment(JLabel.CENTER);
        this.players.addAll(players);

        this.table = table;
    }

    public void setKeys(int keys){
        this.keys.add(keys);
    }

    @Override
    public void action(Board board){
        visited= true;
        this.setIcon(null);
        setColor(players.get(board.getCurrentPlayer()).getColor());
        if(keys.contains(board.getCurrentPlayer())){
            if(!players.get(board.getCurrentPlayer()).isKeyObtained()){
                JOptionPane.showMessageDialog(null, "A kulcsot megszerezted a szinthez!");
            }
            players.get(board.getCurrentPlayer()).keyObtained(true);

        }
        board.setTurn(Turn.NEXT);
    }

    @Override
    public void setDefault(){
        this.setIcon(key);
    }

    @Override
    public void setPlayerOnField(Player p){
        this.setIcon(null);
        this.setBackground(p.getColor());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        StringBuilder s = new StringBuilder();
        if(visited) {
            s.append("<html> Kulcsok:" + "<br/>");
            for (Integer integer : keys) {
                s.append(players.get(integer).getName()).append("<br/>");
            }

        }
        else{
            s.append("<html> Kulcsok:" + "<br/>Ismeretlen");
            s.append("<html/>");

        }
        table.setInfo(s.toString());
    }

}
