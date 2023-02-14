import GUI.MainMenu;
import Logic.Board;
import Logic.Init;
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Init init = new Init();
        MainMenu mainMenu = new MainMenu(init);

        while(!mainMenu.isReady()){
            Thread.sleep(100);
        }
        Board board =new Board(init.getPlayerList(), init.getQuestions(), init.getPictures());
        while (!board.getEndGame()){
            board.move();
        }



    }
}