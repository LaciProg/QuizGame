package GUI;

import GUI.Fields.Field;
import GUI.Levels.BottomLeft;
import GUI.Levels.BottomRight;
import GUI.Levels.TopLeft;
import GUI.Levels.TopRight;
import Logic.Board;
import Logic.Player;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Table extends JFrame implements Serializable {
    private final JButton rollDice;
    private final JLabel instruction;
    private final JLabel info;
    private final JLabel currentPlayer;
    public void defaultInstruction(){
        instruction.setText("Dobott szám: ");
    }
    public void rollEnabled(){
        rollDice.setEnabled(true);
    }

    public void setInfo(String s){
        info.setText("<html> Inforrmáció    :"+"<br/>" +s+"</html>");
    }
    public void setCurrentPlayer(String s){
        currentPlayer.setText("Játékos: "+s+" köre");
    }

    public Table( ArrayList<ArrayList<Field>> fields, ArrayList<Player> players, Board board) {
        this.setTitle("Bord game");
        this.setIconImage(new ImageIcon("Resources/icon.png").getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 2));
        mainPanel.add(new TopLeft(fields, players, this),BorderLayout.CENTER);
        mainPanel.add(new TopRight(fields, players, this), BorderLayout.CENTER);
        BottomRight bottomRight = new BottomRight(fields, players, this);
        mainPanel.add(new BottomLeft(fields, players, this), BorderLayout.CENTER);
        mainPanel.add(bottomRight, BorderLayout.CENTER);


        mainPanel.setBackground(Color.BLACK);
        mainPanel.setOpaque(true);
        this.add(mainPanel);


        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 5));
        topPanel.add(new JLabel(""));
        currentPlayer = new JLabel("Játékos: "+players.get(0).getName()+" köre");
        currentPlayer.setVerticalAlignment(JLabel.CENTER);
        topPanel.add(currentPlayer);
        rollDice = new JButton("Dobás");
        rollDice.setEnabled(false);
        instruction = new JLabel("Dobott szám: ");
        rollDice.setEnabled(true);
        rollDice.addActionListener(e -> {
            instruction.setText("Dobott szám: "+((Integer)board.roll()));
            rollDice.setEnabled(false);
        });
        topPanel.add(rollDice);
        topPanel.add(new JLabel(""));
        topPanel.add(instruction);
        topPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        this.add(topPanel, BorderLayout.NORTH);

        info = new JLabel("Információ:    ");
        info.setBackground(Color.orange);
        info.setOpaque(true);
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new FlowLayout());
        westPanel.add(info);

        info.setHorizontalAlignment(JLabel.LEADING);
        this.add(westPanel, BorderLayout.WEST);


        this.pack();
        this.setSize(1600, 1000);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
