package Logic;

import Logic.Player;

import java.util.Comparator;

public class LevelComparator implements Comparator<Player> {

    @Override
    public int compare(Player p1, Player p2) {
        if (p1.getLevel()==p2.getLevel()) return Integer.compare(p1.getField(), p2.getField());
        else return Integer.compare(p1.getLevel(), p2.getLevel());
    }
}
