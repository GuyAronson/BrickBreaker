import arkanoid.level.Level1;
import arkanoid.level.Level2;
import arkanoid.level.Level3;
import arkanoid.level.Level4;
import arkanoid.level.LevelInformation;
import arkanoid.logics.GameFlow;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to run the game.
*/

public class Ass6Game {
/**
     * main function runs the game.
     * @param args  - arguments given from the command line.
*/

    public static void main(String[] args) {
        List<LevelInformation> levels = new ArrayList<>();
         if (args.length > 0) {
             for (String arg : args) {
                 switch (arg) {
                     case "1" :
                      levels.add(new Level1());
                      break;
                     case "2":
                      levels.add(new Level2());
                      break;
                     case "3":
                      levels.add(new Level3());
                      break;
                     case "4":
                      levels.add(new Level4());
                      break;
                     default:
                        break;
                 }
             }
         }
         if (args.length == 0 || levels.size() == 0) {
             levels.add(new Level1());
             levels.add(new Level2());
             levels.add(new Level3());
             levels.add(new Level4());
         }
        GameFlow game = new GameFlow();
        game.runLevels(levels);
    }

}