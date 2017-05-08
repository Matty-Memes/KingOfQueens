package javalampstudos.kingofqueens.kingOfQueens.engine.io;

import javalampstudos.kingofqueens.MainActivity;

/**
 * Created by Marc on 05/05/2017.
 */

public class Stats {
    //resets stats to zero when option is touched in stats fragment

    public static void resetStats() {

        MainActivity.setting.resetInt("monstersDestroyed");
        MainActivity.setting.resetInt("totalCoins");
        MainActivity.setting.resetInt("totalCardsPlayed");
        MainActivity.setting.resetInt("monstersPlayed");
        MainActivity.setting.resetInt("manaPlayed");
        MainActivity.setting.resetInt("buffsPlayed");
        MainActivity.setting.resetInt("numberOfWins");
        MainActivity.setting.resetInt("forfeit");
        MainActivity.setting.resetInt("gamesPlayed");


    }



    }
