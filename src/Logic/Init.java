package Logic;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Init {

    private final ArrayList<Player> playerList = new ArrayList<>();
    private final ArrayList<ArrayList<String>> questions = new ArrayList<>();


    ArrayList<File> pictures = new ArrayList<>();
    {
        for (int i = 0; i < 3; i++) {
            questions.add(new ArrayList<>());
        }
    }

    public void addPlayer(){
        String name = JOptionPane.showInputDialog("Játékos neve:");
        if(name == null) return;
        Color color = JColorChooser.showDialog(null, "Válassz színt!", Color.WHITE);
        if(color == null) return;
        playerList.add(new Player(name, color));
    }

    public void addQuestion() {
        int count = 0;
        File file;
        JFileChooser fileChooser = new JFileChooser();
        if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            file = new File(fileChooser.getSelectedFile().getAbsolutePath());
        }
        else return;

        try {
            FileReader fileReader = new FileReader(file);
            Scanner scanner = new Scanner(fileReader);

            while (scanner.hasNextLine()){
                count = readIn(scanner, count);

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private int readIn(Scanner scanner, int count) {
        String line;
        line = scanner.nextLine();
        if(line.contains("#")){
            Collections.shuffle(questions.get(count));
            count++;
        }
        else{
            questions.get(count).add(line);
        }
        return count;
    }

    public void addPictures(){
        String folder = JOptionPane.showInputDialog("<html>Kérem a képek mappájának elérési útját!: <br> Az elválasztójel: / <br> (pl.: C:/Users/Dell/Desktop/KépekTeszt)</html>");
        File directory = new File(folder);
        File[] contents = directory.listFiles();
        if (contents != null && folder.contains("/")) {
            Collections.addAll(pictures, contents);
            Collections.shuffle(pictures);
        }
        else{
            JOptionPane.showMessageDialog(null, "Nem található ilyen mappa!", "Hiba!", JOptionPane.ERROR_MESSAGE);
        }

    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public boolean checkPlayer(){
       return (!playerList.isEmpty());
    }

    public boolean checkQuestions(){
        return (questions.size() == 3 && !questions.get(0).isEmpty() && !questions.get(1).isEmpty() && !questions.get(2).isEmpty() && !pictures.isEmpty());
    }

    public ArrayList<ArrayList<String>> getQuestions() {
        return questions;
    }

    public ArrayList<File> getPictures() {
        return pictures;
    }

}
