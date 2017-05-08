package javalampstudos.kingofqueens;


import javalampstudos.kingofqueens.GameLoop.GameState;

// Android Imports

import android.app.Activity;
import android.app.FragmentManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

// Local Imports

import javalampstudos.kingofqueens.GameViewFragment;
import javalampstudos.kingofqueens.kingOfQueens.Menu.LoadingFragment;
import javalampstudos.kingofqueens.kingOfQueens.engine.io.AssetLoader;
import javalampstudos.kingofqueens.kingOfQueens.Menu.MainMenuFragment;
import javalampstudos.kingofqueens.kingOfQueens.engine.io.Settings;

// Based on Blasto by mtstudios
// Andrew - 40083349
public class MainActivity extends Activity {

    // music variables
    private MediaPlayer music;
    public static float musicVolume = 0;
    public static Settings setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        /*
        Window window= getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        */
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null)
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new MainMenuFragment(),
                            "main_menu_fragment").commit();

        setting = new Settings(getApplicationContext());

        hideNav();

    }

    // Andrew - 40083349
    public void hideNav() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LOW_PROFILE);
        }
    }


    public void music() {

        musicVolume = setting.getVolume("musicValue") / 10.0f;


        if (musicVolume != 0) {
            if (music == null) {
                music = AssetLoader.loadMusic(getAssets(), "music/MainMenuTheme.mp3");
                music.start();
            }
            music.setVolume(musicVolume, musicVolume);

        }
    }
}