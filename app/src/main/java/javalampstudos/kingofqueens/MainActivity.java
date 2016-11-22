package javalampstudos.kingofqueens;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window= getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_main);

        FragmentManager fm = getFragmentManager();
        GameViewFragment gameViewFragment = (GameViewFragment)fm.findFragmentById(R.id.activity_main_id);

        if(gameViewFragment==null) {
            gameViewFragment = new GameViewFragment();
            fm.beginTransaction().add(
                    R.id.activity_main_id,gameViewFragment).commit();
        }

    }
}
