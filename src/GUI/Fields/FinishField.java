package GUI.Fields;

import GUI.Table;
import Logic.Board;
import Logic.LevelComparator;
import Logic.Player;
import Logic.Turn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FinishField extends Field{

    Table table;

    public FinishField(int x, int y, int w, int h, Integer n, int l, Color c, ArrayList<Player> players, Table table){

        super(x, y, w, h, n, l, c , players, table);
        this.setText("FINISH");
        this.setForeground(Color.BLACK);
        this.setFont(new Font("MV BOLI", Font.BOLD, 10));
        this.setHorizontalAlignment(JLabel.CENTER);

        this.table = table;

    }

    @Override
    public void action(Board board){

        setColor(players.get(board.getCurrentPlayer()).getColor());
        if(players.get(board.getCurrentPlayer()).isKeyObtained()){
            board.getFinishedPlayers().add(players.get(board.getCurrentPlayer()));
            board.getPlayerList().get(board.getCurrentPlayer()).setFinished(true);
            JOptionPane.showMessageDialog(null, "Gratulálok, beértél a célba!");
        }

        board.setTurn(Turn.NEXT);


        if(board.getFinishedPlayers().size() == players.size() || board.getFinishedPlayers().size() == 3){
            board.setEndGame(true);
            board.setTurn(Turn.END);

            Comparator<Player> comparator = new LevelComparator();
            ArrayList<Player> sortedPlayers = new ArrayList<>(players);
            sortedPlayers.sort(comparator);
            Collections.reverse(sortedPlayers);
            if(sortedPlayers.size() > 3){
                for(int i=3; i!=sortedPlayers.size(); i++){
                    board.getFinishedPlayers().add(sortedPlayers.get(i));
                }
            }

            String[][] data = new String[board.getFinishedPlayers().size()][11];
            for(int i=0; i!=board.getFinishedPlayers().size(); i++){
                int sum= board.getFinishedPlayers().get(i).getAnswerPoints().get(0)+board.getFinishedPlayers().get(i).getAnswerPoints().get(1)+board.getFinishedPlayers().get(i).getAnswerPoints().get(2)+board.getFinishedPlayers().get(i).getAnswerPoints().get(3);
                data[i][0] = board.getFinishedPlayers().get(i).getName();
                data[i][1] = ""+(i+1);
                data[i][2] = ""+board.getFinishedPlayers().get(i).getQuestionPoints().get(0);
                data[i][3] = ""+board.getFinishedPlayers().get(i).getAnswerPoints().get(0);
                data[i][4] = ""+board.getFinishedPlayers().get(i).getQuestionPoints().get(1);
                data[i][5] = ""+board.getFinishedPlayers().get(i).getAnswerPoints().get(1);
                data[i][6] = ""+board.getFinishedPlayers().get(i).getQuestionPoints().get(2);
                data[i][7] = ""+board.getFinishedPlayers().get(i).getAnswerPoints().get(2);
                data[i][8] = ""+board.getFinishedPlayers().get(i).getQuestionPoints().get(3);
                data[i][9] = ""+board.getFinishedPlayers().get(i).getAnswerPoints().get(3);
                data[i][10] = ""+sum;
            }
            String[] columnNames = {"Név", "Hely", "Könnyű", "Pont", "Közepes", "Pont", "Nehéz", "Pont", "Képek", "Pont", "Összesen"};
            JTable table = new JTable(data, columnNames);
            JFrame frame = new JFrame("Játék vége!");
            table.setEnabled(false);
            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            try {
                saveData(board);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Sorted players: " + sortedPlayers);
            System.out.println("Finished players: " + board.getFinishedPlayers());
            System.out.println("Players: " + players);
        }

    }

    private void saveData(Board board) throws IOException {
        FileWriter fileWriter = new FileWriter("Save.txt");
        fileWriter.write("Amennyiben több, mint 3 játékos játszott, a harmadik is célba ért, egyébként mindenki beért a célba.\n");
        fileWriter.write("Játékosok száma: " + board.getFinishedPlayers().size()+"\n");
        for (int i = 0; i != board.getFinishedPlayers().size(); i++) {
            fileWriter.write(board.getFinishedPlayers().get(i).toString());
        }
        fileWriter.close();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        table.setInfo("Játék vége!");
    }

}
