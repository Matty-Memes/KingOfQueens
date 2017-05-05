package javalampstudos.kingofqueens.kingOfQueens.engine.io;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by User on 02/05/2017.
 */

public class Settings {
    // Declaring Variables
    private SharedPreferences.Editor edit;
    private SharedPreferences pref;

    /**
     * Declares the Preference Manager and the Editor
     *
     * @param context
     */
    public Settings(Context context) {
        pref = PreferenceManager.getDefaultSharedPreferences(context);
        edit = pref.edit();
    }

    /**
     * Increases the Value of an integer by 1
     *
     * @param string
     * @return int based on it's assigned name
     */
    public int increaseInt(String string) {
        int value = pref.getInt(string, 0);
        edit.putInt(string, value += 1);
        edit.commit();
        return pref.getInt(string, 1);
    }

    /**
     * Decreases the Value of an integer by 1
     *
     * @param string
     * @return int based on it's assigned name
     */
    public int decreaseInt(String string) {
        int value = pref.getInt(string, 0);
        edit.putInt(string, value = value - 1);
        edit.commit();
        return pref.getInt(string, 1);
    }

    /**
     * Gets the value of music/sfx volume
     *
     * @param key
     * @return The value of volume value, it's default value is 5
     */
    public int getVolume(String key) {
        return pref.getInt(key, 5);
    }

    /**
     * Increases the value of music/sfx volume
     *
     * @param string
     * @return The value of volume value, it's default value is 5
     */
    public int increaseVolume(String string) {
        int value = pref.getInt(string, 5);
        edit.putInt(string, value += 1);
        edit.commit();
        return pref.getInt(string, 5);
    }

    /**
     * Decreases the value of music/sfx volume
     *
     * @param string
     * @return The value of volume value, it's default value is 5
     */
    public int decreaseVolume(String string) {
        int value = pref.getInt(string, 5);
        edit.putInt(string, value = value - 1);
        edit.commit();
        return pref.getInt(string, 5);
    }

    public int muteVolume(String string) {
        int value = pref.getInt(string, 5);
        edit.putInt(string, value = value - value);
        edit.commit();
        return pref.getInt(string, 5);
    }


    /**
     * Returns an Integer value based on the Assigned name of the string passed
     * through
     *
     * @param string
     * @return the integer
     */
    public int getInt(String string) {
        return pref.getInt(string, 0);
    }

    /**
     * This adds a specific value onto an int value
     *
     * @param key   : Name of the variable to be added to
     * @param value : The amount to be added to the current value
     */
    public void addToInt(String key, int value) {
        edit.putInt(key, value + pref.getInt(key, 0));
        edit.commit();
    }

    /**
     * This sets a specific value onto an int value
     *
     * @param key   : Name of the variable to be set
     * @param value : The amount to be set
     */
    public void setInt(String key, int value) {
        edit.putInt(key, value);
        edit.commit();
    }

    /**
     * This resets an integer to zero
     *
     * @param key
     */
    public void resetInt(String key) {
        edit.putInt(key, 0);
        edit.commit();
    }

    /**
     * Toggles the value of a boolean
     *
     * @param string
     * @return boolean
     */
    public boolean toggleBoolean(String string) {
        if (pref.getBoolean(string, false)) {
            edit.putBoolean(string, false);
        } else {
            edit.putBoolean(string, true);
        }
        edit.commit();
        return pref.getBoolean(string, true);
    }

    /**
     * Returns the value of a boolean
     *
     * @param string
     * @return
     */
    public boolean getBoolean(String string) {
        return pref.getBoolean(string, true);
    }

    /**
     * returns the value of the currently selected language It's set to "en" by
     * default
     *
     * @return string
     */
    public String getLanguage() {
        return pref.getString("languageString", "en");
    }

    /**
     * Sets the value of languageString
     *
     * @param value
     * @return string
     */
    public String setLanguage(String value) {
        edit.putString("languageString", value);
        edit.commit();
        return pref.getString("languageString", "en");
    }

    /**
     * Sets the value of a string value by taking the string value that has been
     * passed through the method
     *
     * @param key
     * @param value
     * @return the value of the string
     */
    public String setString(String key, String value) {
        edit.putString(key, value);
        edit.commit();
        return pref.getString(key, value);
    }

    /**
     * Returns the value of the requested string
     *
     * @param key
     * @param defValue
     * @return requested string
     */
    public String getString(String key, String defValue) {
        return pref.getString(key, defValue);
    }


}


