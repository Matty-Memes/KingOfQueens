package javalampstudos.kingofqueens.kingOfQueens.engine.io;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by User on 02/05/2017.
 */

public class Settings {


    private SharedPreferences.Editor edit;
    private SharedPreferences pref;

    public Settings(Context context) {
        pref = PreferenceManager.getDefaultSharedPreferences(context);
        edit = pref.edit();
    }

    //increases an integer by 1, used for stats fragment
    public int increaseInt(String string) {
        int val = pref.getInt(string, 0);
        edit.putInt(string, val += 1);
        edit.commit();
        return pref.getInt(string, 1);
    }


    //gets volume level
    public int getVolume(String key) {
        return pref.getInt(key, 5);
    }

    //increases volume by 1
    public int increaseVolume(String string) {
        int val = pref.getInt(string, 5);
        edit.putInt(string, val += 1);
        edit.commit();
       return pref.getInt(string, 5);
    }

    //decreases volume by 1
    public int decreaseVolume(String string) {
        int val = pref.getInt(string, 5);
        edit.putInt(string, val = val - 1);
        edit.commit();
        return pref.getInt(string, 5);
    }

    //mutes volume
    public int muteVolume(String string) {
        int val = pref.getInt(string, 5);
        edit.putInt(string, val = val - val);
        edit.commit();
        return pref.getInt(string, 5);
    }

    //Returns an Int
    public int getInt(String string) {
        return pref.getInt(string, 0);
    }



    //resets int to zero
    public void resetInt(String key) {
        edit.putInt(key, 0);
        edit.commit();
    }

}