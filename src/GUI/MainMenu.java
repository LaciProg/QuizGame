package GUI;

import Logic.Init;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    private boolean ready = false;

    public MainMenu(Init init) {
        this.setTitle("Főmenü");
        this.setIconImage(new ImageIcon("Resources/icon.png").getImage());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5,1));
        JButton players = new JButton("Játékosok hozzáadása");
        players.addActionListener(e -> init.addPlayer());
        JButton questions = new JButton("Kérdések hozzáadása");
        questions.addActionListener(e -> init.addQuestion());
        JButton pictures = new JButton("Képek hozzáadása");
        pictures.addActionListener(e -> init.addPictures());
        JButton startGame = new JButton("Játék indítása");
        startGame.addActionListener(e -> {
            if(init.checkPlayer() && init.checkQuestions()){
                ready = true;
                this.dispose();
            }
            else {
                JOptionPane.showMessageDialog(this, "Nem adtál meg elég játékost, képet vagy kérdést!");
            }
        });
        JButton exit = new JButton("Kilépés");
        exit.addActionListener(e -> System.exit(0));

        mainPanel.add(players);
        mainPanel.add(questions);
        mainPanel.add(pictures);
        mainPanel.add(startGame);
        mainPanel.add(exit);
        this.add(mainPanel);
        this.pack();
    }

    public boolean isReady(){
        return ready;
    }


}





