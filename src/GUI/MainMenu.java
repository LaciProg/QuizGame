package GUI;

import Logic.Init;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    private boolean ready = false;

    public MainMenu(Init init) {
        frameSetters();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5,1));

        setButtons(init, mainPanel);

        this.add(mainPanel);
        this.pack();
    }

    private void setButtons(Init init, JPanel mainPanel) {
        setPlayers(init, mainPanel);
        setQuestions(init, mainPanel);
        setPictures(init, mainPanel);
        setStartGame(init, mainPanel);
        setExit(mainPanel);
    }

    private static void setExit(JPanel mainPanel) {
        JButton exit = new JButton("Kilépés");
        exit.addActionListener(e -> System.exit(0));
        mainPanel.add(exit);
    }

    private void setStartGame(Init init, JPanel mainPanel) {
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
        mainPanel.add(startGame);
    }

    private static void setPictures(Init init, JPanel mainPanel) {
        JButton pictures = new JButton("Képek hozzáadása");
        pictures.addActionListener(e -> init.addPictures());
        mainPanel.add(pictures);
    }

    private static void setQuestions(Init init, JPanel mainPanel) {
        JButton questions = new JButton("Kérdések hozzáadása");
        questions.addActionListener(e -> init.addQuestion());
        mainPanel.add(questions);
    }

    private static void setPlayers(Init init, JPanel mainPanel) {
        JButton players = new JButton("Játékosok hozzáadása");
        players.addActionListener(e -> init.addPlayer());
        mainPanel.add(players);
    }

    private void frameSetters() {
        this.setTitle("Főmenü");
        this.setIconImage(new ImageIcon("Resources/icon.png").getImage());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public boolean isReady(){
        return ready;
    }


}





