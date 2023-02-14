package GUI.Fields;

import GUI.Table;
import Logic.Board;
import Logic.Player;
import Logic.Turn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PictureField extends Field {

    ImageIcon camera = new ImageIcon("Resources/camera.png");
    Table table;

    public PictureField(int x, int y, int w, int h, Integer n, int l,  Color c, ArrayList<Player> players, Table table){

        super(x, y, w, h, n, l, c , players, table);
        this.setIcon(camera);

        this.setText(n.toString());
        this.setForeground(Color.BLACK);
        this.setFont(new Font("MV BOLI", Font.BOLD, 25));
        this.setHorizontalTextPosition(JLabel.CENTER);

        this.setHorizontalAlignment(JLabel.CENTER);


        this.table = table;
    }

    @Override
    public void action(Board board) {
        this.setIcon(null);
        setColor(players.get(board.getCurrentPlayer()).getColor());
        JFrame frame = new JFrame("Kép");
        //frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        JLabel label = new JLabel();

        if(board.getPictures().isEmpty()){
            board.reshufflePictures();
        }

        label.setIcon(new ImageIcon(board.getPictures().get(0).getAbsolutePath()));
        frame.add(label);
        frame.pack();
        board.addUsedPicture(board.getPictures().get(0));
        board.getPictures().remove(0);

        int given = -1;

        while(given == -1) {
            given=
                    JOptionPane.showOptionDialog(
                            frame.getContentPane(),
                            "Válaszoljon a felette kérdésre",
                            "Kép",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            new String[]{"Helyes válasz", "Helytelen válasz"},
                            null);
        }
        board.getPlayerList().get(board.getCurrentPlayer()).addQuestionPoint(4);
        frame.dispose();

        if(given==0) {
            JOptionPane.showMessageDialog(null, "Helyes válasz! Lépj előre egy mezőt!");
            board.getPlayerList().get(board.getCurrentPlayer()).addAnswerPoint(4, 1);
            board.setTurn(Turn.STAY);
            board.setRoll(1);
            board.setExtraMove(true);
        }
        else {
            JOptionPane.showMessageDialog(null, "Rossz válasz! Nem léphetsz előre!");
            board.setTurn(Turn.NEXT);
        }


    }

    public void setDefault(){
        this.setIcon(camera);
    }

    @Override
    public void setPlayerOnField(Player p){
        this.setIcon(null);
        this.setColor(p.getColor());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        table.setInfo("Képek");
    }

}
