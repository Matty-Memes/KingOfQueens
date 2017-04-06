package javalampstudos.kingofqueens;


import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.Matrix;
import android.view.WindowManager;

// Local imports

import javalampstudos.kingofqueens.kingOfQueens.Menu.MainMenuFragment;
import javalampstudos.kingofqueens.kingOfQueens.Menu.PauseFragment;
import javalampstudos.kingofqueens.kingOfQueens.engine.SFX.SoundFX;
import javalampstudos.kingofqueens.kingOfQueens.engine.graphics.CanvasFragment;
import javalampstudos.kingofqueens.kingOfQueens.engine.io.AssetLoader;
import javalampstudos.kingofqueens.kingOfQueens.engine.SFX.SoundFX;

import android.graphics.Matrix;
import android.view.WindowManager;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameViewFragment extends CanvasFragment {

    // instantiate a loop
    protected GameLoop loop;

    private SoundFX SFX;

    // image stuff that the whole class can see
    private Paint mPaint;
    // these are the bitmaps themselves which map to the stuff below
    private Bitmap mImage;
    private Bitmap mImage2;
    private Bitmap mImage3;

    private Bitmap image;


    private int width, height;

    // Needs these for collision detection
    private Rect mLittleManBound;
    private Rect cloudyBackgroundBound;
    private Rect KofQBound;
    private Rect mLittleManBound2;

    private Rect dataAdminBound;

    // what's this for??
    private Matrix matrix = new Matrix();

    // Empty GameViewFragment constructor

    public GameViewFragment ()

    {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        ((MainActivity) getActivity()).hideNav();

        WindowManager wm = ((WindowManager) getActivity().getSystemService(
                Context.WINDOW_SERVICE));
        Display display = wm.getDefaultDisplay();
        Point point = new Point();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            display.getRealSize(point);
        else
            display.getSize(point);

        width = point.x;


        height = point.y;

        /*

        System.out.println("The width is " + width);
        System.out.println("The height is " + height);

        */

        // instantiate the loop and start things running
        loop = new GameLoop(this, width, height);
        return loop.canvasRenderer;


    }


    // SETUP AND DRAW METHODS


    // The render thread calls this - see Canvas Renderer

    public void doSetup() {


        mPaint = new Paint();

        // create an AssetManager
        AssetManager assetManager = getActivity().getAssets();

        // mImage = AssetLoader.loadBitmap(assetManager, "img/Nathan/trimLittleMan.png");
        mImage2 = AssetLoader.loadBitmap(assetManager, "img/Nathan/cloudyBackground.png");
        // mImage3 = AssetLoader.loadBitmap(assetManager, "img/Nathan/KofQ.png");


        // deal with just the background
        // instantiate a rectangle that relates to the background
        cloudyBackgroundBound = new Rect(0, 0, width, height);

        // Mess around with bounds

    }

    /**
     * Method that will be called by the render thread when the canvas needs to
     * be redrawn
     *
     * @param canvas
     *            Canvas to be redrawn
     */

    /*

    private Rect mLittleManBound;
    private Rect cloudyBackgroundBound;
    private Rect KofQBound;
    private Rect mLittleManBound2;

    */


    public void doDraw(Canvas canvas) {

        canvas.drawBitmap(mImage2, null, cloudyBackgroundBound, null);

        drawCard(canvas);

        // drawMonsterCards(canvas);

        drawHand(canvas);


    }

    public void onPause() {

        loop.pause();
        super.onPause();
    }

    public void onResume()

    {
        super.onResume();
        loop.resume();


    }

    // draw the current card

    private void drawCard (Canvas canvas)

    {
        if (loop.Geologist.destroyed == false)

        {
            loop.Geologist.draw(canvas);

        }

        else

        {
            System.out.println("Geologist was destroyed");

        }

        // draw temp cards for positioning

        // hands
        loop.handCard1.draw(canvas);
        loop.handCard2.draw(canvas);
        loop.handCard3.draw(canvas);
        loop.handCard4.draw(canvas);
        loop.handCard5.draw(canvas);

        // monsters
        loop.monsterCard1.draw(canvas);
        loop.monsterCard2.draw(canvas);
        loop.monsterCard3.draw(canvas);

        // draw opponent card
        loop.opponentCard1.draw(canvas);
        loop.opponentCard2.draw(canvas);
        loop.opponentCard3.draw(canvas);

        // deck and graveyard
        loop.graveYard.draw(canvas);
        loop.deck.draw(canvas);
    }


    // DRAW SETS OF CARDS

    private void drawHand (Canvas canvas)

    {
        for (int i = 0; i < loop.handCards.size(); i++)

        {
            loop.handCards.get(i).draw(canvas);

        }


    }

    private void drawMonsterCards (Canvas canvas)

    {
        for (int i = 0; i < loop.monstersInPlay.size(); i++)

        {

            loop.monstersInPlay.get(i).draw(canvas);

        }

    }

}
