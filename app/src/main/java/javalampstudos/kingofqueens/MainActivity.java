package javalampstudos.kingofqueens;

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

public class MainActivity extends Activity {

    // music variables
    private MediaPlayer music;

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

        if(savedInstanceState==null) {
            getFragmentManager().beginTransaction().add(R.id.container, new LoadingFragment()).commit();
        }

        hideNav();

    }

    public void hideNav() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LOW_PROFILE);
        }
    }

    // deal with music

    public void music() {

        /*
        boolean isGame = getFragmentManager().findFragmentById(R.id.container)
                .getTag().equals("game_fragment");

               */

        // load in the correct asset
        // music = AssetLoader.loadMusic(getAssets(), "music/StarShips - Nicki Minaj (Lyrics).mp3");

        // start the music
        // music.start();

        // need to control what plays in each menu
        // also pause the music

    }

    protected void onResume() {
        super.onResume();
        hideNav();
        // music();

    }

    protected void onPause() {

        super.onPause();
    }
}
